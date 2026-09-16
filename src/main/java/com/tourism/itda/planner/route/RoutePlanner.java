package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.planner.dto.*;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.entity.PlaceType;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.service.TourApiPlaceImporter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 하루 루트를 조립한다.
 *
 * <p>일정 자체는 저장하지 않는 미리보기다 — 사용자가 저장(No.28)을 눌러야 진짜 저장된다.
 * 다만 일반 관광명소를 후보로 쓰려면 place 행이 있어야 하므로(구간 후보 조회가 place_id 를
 * 받는다) {@link GeneralSpotFinder} 가 찾은 장소는 {@link TourApiPlaceImporter} 를 통해
 * place 테이블에 저장된다. 그래서 이 클래스는 더 이상 읽기 전용 트랜잭션이 아니다.
 *
 * <p>템플릿은 {@code 장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) → 장소}.
 * 촬영지 칸만 채우고 식당·카페 칸은 비워 둔다 — 취향 편차가 큰 영역이라
 * 사용자가 지도에서 직접 고르게 하는 것이 팀 결정이다.
 *
 * <p>구성 규칙은 <b>관련 1 + 일반 2</b> 또는 <b>관련 2 + 일반 1</b> 이다
 * ({@link #MAX_RELATED_SPOTS}). 관련 명소를 먼저 최대 2곳까지 고르고
 * ({@link SpotScorer}+{@link SpotCurator}), 남은 칸을 {@link GeneralSpotFinder} 가
 * 주변 일반 관광명소로 채운다. 관련 명소가 0곳인 작품은 루트를 만들지 않는다.
 *
 * <p>세 곳이 모두 정해지면 {@link VisitOrderOptimizer} 가 동선 기준으로 방문 순서를 정하고,
 * 그 순서 그대로 템플릿 칸에 배치한다. 관련/일반 여부는 순서에 영향을 주지 않는다 —
 * recommend_order 도 더 이상 순서 결정 기준이 아니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RoutePlanner {

    /**
     * 한 루트에 넣는 작품 관련 명소의 최대 개수.
     *
     * <p>총 3곳을 <b>관련 1 + 일반 2</b> 또는 <b>관련 2 + 일반 1</b> 로 구성한다는 것이 팀 결정이다.
     * 앵커가 3곳 넘게 있어도 2곳까지만 쓰고 나머지 한 칸은 주변 일반 관광명소에 내준다 —
     * 작품 관련 명소만 늘어놓으면 하루 코스로서의 재미가 없다는 판단이다.
     *
     * <p>반대로 <b>관련 명소가 0곳이면 루트를 만들지 않는다</b>. 일반 명소 3곳으로 대체하면
     * 그건 더 이상 그 작품의 루트가 아니기 때문이다. 그 판정은 {@link ContentAnchorResolver} 가 한다.
     */
    public static final int MAX_RELATED_SPOTS = 2;

    private final ContentRepository contentRepository;
    private final ContentAnchorResolver anchorResolver;
    private final PlaceImageRepository placeImageRepository;
    private final DetourFilter detourFilter;
    private final TimelineEstimator timelineEstimator;
    private final SpotScorer spotScorer;
    private final SpotCurator spotCurator;
    private final GeneralSpotFinder generalSpotFinder;
    private final TourApiPlaceImporter tourApiPlaceImporter;
    private final VisitOrderOptimizer visitOrderOptimizer;
    private final RouteProperties properties;

    public RoutePlanResponse plan(RoutePlanRequest request) {
        Content content = contentRepository.findById(request.contentId())
                .orElseThrow(() -> new NotFoundException("콘텐츠를 찾을 수 없습니다."));

        ContentAnchorResolver.Anchors anchors = anchorResolver.resolve(request.contentId());
        if (anchors.isEmpty()) {
            // 수집 미완료와 구분하지 않고 404 로 내보낸다. 작품 숨김 판정은 배치가 따로 한다.
            throw new NotFoundException("이 작품과 연결된 명소가 없습니다.");
        }
        List<ContentSpot> allSpots = anchors.spots();

        long allowance = properties.clampAllowance(request.allowanceMeters());
        List<Long> requestedIds = request.spotPlaceIdsOrEmpty();

        Selection selection = selectSpots(allSpots, requestedIds, content.getTitle(), allowance);
        selection = fillWithGeneralSpots(selection, allSpots);

        VisitOrderOptimizer.OrderedRoute ordered = visitOrderOptimizer.optimize(selection.spots());
        List<ContentSpot> spots = ordered.spots();

        List<TemplateSlot> template = DayTemplate.forSpotCount(spots.size());
        List<FillSegment> segments = DayTemplate.segmentsOf(template);
        List<LocalTime> times = timelineEstimator.estimate(
                template, spots.stream().map(ContentSpot::coord).toList(), allowance);

        Map<Long, String> images = primaryImages(spots);
        Map<Integer, Integer> slotToSegment = mapSlotsToSegments(segments);

        List<RouteSlotView> slotViews = new ArrayList<>(template.size());
        int spotCursor = 0;
        for (TemplateSlot slot : template) {
            if (slot.type() == PlaceType.SPOT) {
                ContentSpot spot = spots.get(spotCursor++);
                Place place = spot.place();
                slotViews.add(RouteSlotView.filled(
                        slot.index(), slot.type(), slot.label(), times.get(slot.index()),
                        RoutePlaceView.of(place, images.get(place.getId())),
                        selection.filledByOf(place.getId()),
                        selection.reasonOf(place.getId())));
            } else {
                slotViews.add(RouteSlotView.empty(
                        slot.index(), slot.type(), slot.label(), times.get(slot.index()),
                        slotToSegment.getOrDefault(slot.index(), 0)));
            }
        }

        return new RoutePlanResponse(
                content.getId(),
                content.getTitle(),
                spots.get(0).place().getRegion(),
                spots.size(),
                allowance,
                anchors.source(),
                List.copyOf(slotViews),
                buildSegmentViews(segments, spots, allowance),
                ordered.roadVerified() ? ordered.totalDistanceMeters() : null,
                ordered.roadVerified() ? ordered.totalDurationSeconds() : null,
                ordered.roadVerified(),
                ordered.withinLimits());
    }

    // ── 촬영지 선택 ──────────────────────────────────────────────────────────

    /**
     * 사용자가 고른 작품 관련 명소를 확정한다. 최대 {@link #MAX_RELATED_SPOTS} 곳까지만 쓴다.
     *
     * <p>사용자가 아무것도 안 고르면 자동추천 모드로, 전부 서버가 고른다.
     * 상한을 넘겨 보내면 앞에서부터 상한까지만 쓴다 — UI 에서도 막지만 서버가 최종 방어.
     */
    private Selection selectSpots(List<ContentSpot> allSpots, List<Long> requestedIds,
                                  String contentTitle, long allowanceMeters) {
        Map<Long, ContentSpot> byId = allSpots.stream()
                .collect(Collectors.toMap(ContentSpot::placeId, spot -> spot, (a, b) -> a, LinkedHashMap::new));

        List<ContentSpot> selected = new ArrayList<>();
        Set<Long> selectedIds = new LinkedHashSet<>();
        for (Long id : requestedIds) {
            ContentSpot spot = byId.get(id);
            if (spot == null) {
                continue;   // 이 콘텐츠의 촬영지가 아닌 id 는 조용히 무시한다.
            }
            if (selectedIds.add(id) && selected.size() < MAX_RELATED_SPOTS) {
                selected.add(spot);
            }
        }
        // recommend_order 로 정렬하지 않는다 — 방문 순서는 VisitOrderOptimizer 가 동선 기준으로 정한다.

        Map<Long, SlotFilledBy> filledBy = new HashMap<>();
        Map<Long, String> reasons = new HashMap<>();
        selected.forEach(spot -> filledBy.put(spot.placeId(), SlotFilledBy.USER));

        // 앵커가 3곳 이상 있어도 2곳까지만 쓴다. 나머지 한 칸은 일반 명소 몫이다.
        int target = Math.min(MAX_RELATED_SPOTS, allSpots.size());
        while (selected.size() < target) {
            List<ContentSpot> remaining = allSpots.stream()
                    .filter(spot -> !selectedIds.contains(spot.placeId()))
                    .toList();

            ScoringContext context = new ScoringContext(allSpots.size(), target, allowanceMeters);
            List<ScoredSpot> ranked = spotScorer.rank(selected, remaining, context);
            List<ScoredSpot> shortlist = ranked.size() <= properties.curatorShortlistSize()
                    ? ranked
                    : ranked.subList(0, properties.curatorShortlistSize());

            Optional<CuratedSpot> curated = spotCurator.choose(shortlist, contentTitle, selected);
            if (curated.isEmpty()) {
                break;   // 더 넣을 촬영지가 없다.
            }

            CuratedSpot pick = curated.get();
            selected.add(pick.spot());
            selectedIds.add(pick.spot().placeId());

            filledBy.put(pick.spot().placeId(), pick.byLlm() ? SlotFilledBy.CURATED : SlotFilledBy.SCORED);
            if (pick.reason() != null) {
                reasons.put(pick.spot().placeId(), pick.reason());
            }
        }

        if (selected.isEmpty()) {
            // 작품 관련 명소는 반드시 1곳 이상이어야 한다는 것이 이 서비스의 원칙이다.
            // 점수·큐레이션이 하나도 못 고르는 경우에도 앵커 하나는 남긴다.
            ContentSpot first = allSpots.get(0);
            selected.add(first);
            filledBy.put(first.placeId(), SlotFilledBy.SCORED);
            log.info("점수·큐레이션이 앵커를 고르지 못해 첫 앵커({})를 그대로 씁니다.", first.place().getName());
        }

        return new Selection(List.copyOf(selected), filledBy, reasons);
    }

    /**
     * 앵커(작품 관련 명소)만으로 3곳을 못 채우면 주변 일반 관광명소로 나머지를 채운다.
     *
     * <p>일반명소는 앵커가 아니므로 {@link SlotFilledBy#GENERAL} 로 표시해 프론트가
     * 작품 관련 문구를 붙이지 않게 한다. 여기서 처음으로 DB 에 쓰기가 발생한다
     * ({@link TourApiPlaceImporter#importPlace} — place 행이 있어야 구간 후보 조회가
     * place_id 를 참조할 수 있다).
     *
     * <p>일반명소를 목표만큼 못 찾아도 예외를 던지지 않는다 — {@link DayTemplate#forSpotCount}
     * 가 2곳·1곳도 처리하므로 찾은 만큼으로 진행한다.
     */
    private Selection fillWithGeneralSpots(Selection selection, List<ContentSpot> allSpots) {
        int missing = DayTemplate.MAX_SPOTS - selection.spots().size();
        if (missing <= 0) {
            return selection;
        }

        Set<Long> takenIds = selection.spots().stream().map(ContentSpot::placeId).collect(Collectors.toSet());
        // 사용자가 아무 앵커도 못 고른 극단적인 경우(큐레이터가 하나도 못 고른 경우)를 대비해,
        // 확정된 명소가 없으면 콘텐츠의 앵커 전체를 검색 중심으로 쓴다.
        List<ContentSpot> center = selection.spots().isEmpty() ? allSpots : selection.spots();
        List<Coord> anchorCoords = center.stream().map(ContentSpot::coord).toList();

        List<NearbySpot> nearby = generalSpotFinder.find(anchorCoords, missing);

        List<ContentSpot> merged = new ArrayList<>(selection.spots());
        Map<Long, SlotFilledBy> filledBy = new HashMap<>(selection.filledBy());
        Map<Long, String> reasons = new HashMap<>(selection.reasons());

        int order = merged.size() + 1;
        for (NearbySpot spot : nearby) {
            if (merged.size() >= DayTemplate.MAX_SPOTS) {
                break;
            }
            Place place = tourApiPlaceImporter.importPlace(spot.externalId(), PlaceType.SPOT);
            if (!takenIds.add(place.getId())) {
                continue;   // 이미 앵커로 들어간 place 다.
            }
            merged.add(new ContentSpot(place, order++));
            filledBy.put(place.getId(), SlotFilledBy.GENERAL);
        }

        if (merged.size() < DayTemplate.MAX_SPOTS) {
            log.info("일반 관광명소로도 {}곳을 채우지 못해 {}곳으로 진행합니다.",
                    DayTemplate.MAX_SPOTS, merged.size());
        }

        return new Selection(List.copyOf(merged), filledBy, reasons);
    }

    // ── 구간 ────────────────────────────────────────────────────────────────

    private List<RouteSegmentView> buildSegmentViews(List<FillSegment> segments,
                                                     List<ContentSpot> spots,
                                                     long allowance) {
        List<RouteSegmentView> views = new ArrayList<>(segments.size());
        for (FillSegment segment : segments) {
            ContentSpot start = spots.get(segment.startSpotIndex());
            ContentSpot end = segment.isTrailing() ? null : spots.get(segment.endSpotIndex());

            long directDistance = (end == null) ? 0L : detourFilter.distance(start.coord(), end.coord());
            boolean partial = end != null && detourFilter.isPartialCoverage(start.coord(), end.coord(), allowance);

            views.add(new RouteSegmentView(
                    segment.index(),
                    start.placeId(),
                    end == null ? null : end.placeId(),
                    start.coord().latitude(),
                    start.coord().longitude(),
                    end == null ? null : end.coord().latitude(),
                    end == null ? null : end.coord().longitude(),
                    directDistance,
                    allowance,
                    partial,
                    segment.slots().stream().map(TemplateSlot::index).toList()));
        }
        return List.copyOf(views);
    }

    private static Map<Integer, Integer> mapSlotsToSegments(List<FillSegment> segments) {
        Map<Integer, Integer> map = new HashMap<>();
        for (FillSegment segment : segments) {
            for (TemplateSlot slot : segment.slots()) {
                map.put(slot.index(), segment.index());
            }
        }
        return map;
    }

    // ── 조회 ────────────────────────────────────────────────────────────────

    /**
     * 작품 관련 명소 전체. 직접선택 UI 에도 쓰인다.
     * 승인 매핑이 없으면 인물 체인으로 넘어가므로 {@link ContentAnchorResolver} 에 위임한다.
     */
    public List<ContentSpot> loadSpots(Long contentId) {
        return anchorResolver.resolve(contentId).spots();
    }

    public Map<Long, String> primaryImages(List<ContentSpot> spots) {
        List<Long> ids = spots.stream().map(ContentSpot::placeId).filter(Objects::nonNull).toList();
        if (ids.isEmpty()) {
            return Map.of();
        }
        return placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(ids).stream()
                .collect(Collectors.toMap(PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));
    }

    /** 촬영지 선택 결과와 그 출처. */
    private record Selection(List<ContentSpot> spots,
                             Map<Long, SlotFilledBy> filledBy,
                             Map<Long, String> reasons) {

        SlotFilledBy filledByOf(Long placeId) {
            return filledBy.getOrDefault(placeId, SlotFilledBy.SCORED);
        }

        String reasonOf(Long placeId) {
            return reasons.get(placeId);
        }
    }
}
