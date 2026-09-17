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
        Set<Long> exclude = excludeWithoutUserPicks(request.excludePlaceIdsOrEmpty(), requestedIds);

        Selection selection = selectSpots(
                anchorsAfterExclusion(allSpots, exclude), requestedIds, content.getTitle(), allowance);
        selection = fillWithGeneralSpots(selection, allSpots, exclude);

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

    /**
     * 제외 목록에서 사용자가 직접 고른 촬영지를 빼낸다.
     *
     * <p>"루트 변경하기"는 직전 루트의 place_id 를 그대로 제외로 보내는 방식이라, 사용자가 고른
     * 촬영지도 그 목록에 딸려 들어온다. 그걸 그대로 적용하면 <b>고른 곳이 루트에서 사라진다</b> —
     * 광해에서 창덕궁을 고르고 변경하기를 누르면 창덕궁이 빠지고 종묘가 대신 들어왔다.
     *
     * <p>{@code spot_place_ids} 는 "꼭 가고 싶다"는 뜻이므로 제외보다 우선한다. 프론트가 직전 루트를
     * 통째로 제외에 넣어도 안전하도록 서버에서 걸러 낸다.
     */
    static Set<Long> excludeWithoutUserPicks(List<Long> excludeIds, List<Long> requestedIds) {
        if (excludeIds.isEmpty() || requestedIds.isEmpty()) {
            return Set.copyOf(excludeIds);
        }
        Set<Long> exclude = new LinkedHashSet<>(excludeIds);
        exclude.removeAll(requestedIds);
        return Set.copyOf(exclude);
    }

    // ── 촬영지 선택 ──────────────────────────────────────────────────────────

    /**
     * 사용자가 고른 작품 관련 명소를 확정한다. 최대 {@link #MAX_RELATED_SPOTS} 곳까지만 쓴다.
     *
     * <p>사용자가 아무것도 안 고르면 자동추천 모드로, 전부 서버가 고른다.
     * 상한을 넘겨 보내면 앞에서부터 상한까지만 쓴다 — UI 에서도 막지만 서버가 최종 방어.
     *
     * <p><b>사용자가 골랐으면 그 개수를 그대로 지킨다</b>({@link #relatedSpotTarget}).
     * 예전에는 목표치를 {@code min(2, 앵커수)} 로 고정해서, 앵커가 2곳인 작품(덕혜옹주 등)에서
     * 사용자가 1곳만 골라도 나머지 1곳이 자동으로 따라 들어갔다. 고른 대로 나오지 않으니
     * 선택 UI 자체가 무의미해진다.
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

        int target = relatedSpotTarget(selected.size(), allSpots.size());
        while (selected.size() < target) {
            List<ContentSpot> remaining = allSpots.stream()
                    .filter(spot -> !selectedIds.contains(spot.placeId()))
                    .filter(spot -> farEnoughFromSelected(spot, selected))
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
            // 위 거리 필터로 후보가 모두 걸러진 경우도 여기로 온다.
            // 점수·큐레이션이 하나도 못 고르는 경우에도 앵커 하나는 남긴다.
            ContentSpot first = allSpots.get(0);
            selected.add(first);
            filledBy.put(first.placeId(), SlotFilledBy.SCORED);
            log.info("점수·큐레이션이 앵커를 고르지 못해 첫 앵커({})를 그대로 씁니다.", first.place().getName());
        }

        return new Selection(List.copyOf(selected), filledBy, reasons);
    }

    /**
     * 이번 루트에 넣을 작품 관련 명소의 목표 개수.
     *
     * <p>사용자가 골랐으면 <b>그 개수 그대로</b>다. 1곳을 골랐으면 1곳만 들어가고 남은 두 칸은
     * 일반 명소가 채운다 — 고른 것 외에 관련 명소를 더 끼워 넣으면 선택이 무시된 것으로 보인다.
     *
     * <p>아무것도 안 골랐을 때만 서버가 {@link #MAX_RELATED_SPOTS} 까지 채운다. 앵커가 3곳 이상
     * 있어도 2곳까지만 쓴다 — 나머지 한 칸은 일반 명소 몫이라는 것이 팀 결정이다.
     *
     * @param userPickedCount 사용자가 고른 것 중 <b>이 작품의 앵커가 맞는</b> 개수.
     *                        0 이면 자동추천 모드다 (엉뚱한 id 만 보낸 경우도 여기로 온다).
     * @param anchorCount     이 작품이 가진 앵커 수
     */
    static int relatedSpotTarget(int userPickedCount, int anchorCount) {
        if (userPickedCount > 0) {
            return userPickedCount;
        }
        return Math.min(MAX_RELATED_SPOTS, anchorCount);
    }

    /**
     * "다른 코스 보기"로 제외 요청이 온 앵커를 걸러낸다.
     *
     * <p>다만 <b>전부 걸러지면 제외를 통째로 무시한다.</b> 작품 관련 명소가 최소 1곳은 있어야
     * 그 작품의 루트라고 할 수 있는데, 앵커가 1곳뿐인 작품이 20편이라 제외를 그대로 적용하면
     * 재생성 한 번에 루트가 사라진다. 그런 작품은 관련 명소를 유지하고 일반 명소만 바뀐다.
     */
    private List<ContentSpot> anchorsAfterExclusion(List<ContentSpot> allSpots, Set<Long> exclude) {
        if (exclude.isEmpty()) {
            return allSpots;
        }
        List<ContentSpot> kept = allSpots.stream()
                .filter(spot -> !exclude.contains(spot.placeId()))
                .toList();
        if (kept.isEmpty()) {
            log.info("제외 요청을 적용하면 작품 관련 명소가 0곳이 되어 제외를 무시합니다. 앵커 {}곳",
                    allSpots.size());
            return allSpots;
        }
        return kept;
    }

    /**
     * 이미 고른 명소들과 충분히 떨어져 있는가.
     *
     * <p>동선 점수만 보면 바로 옆 장소가 항상 이긴다. 실제로 수원 화성과 화성행궁(460m)이
     * 나란히 뽑혀 하루 코스가 한 자리에 머무는 결과가 나왔다. 사용자가 직접 고른 곳에는
     * 적용하지 않는다 — 붙어 있어도 본인이 원한 것이기 때문이다.
     */
    private boolean farEnoughFromSelected(ContentSpot candidate, List<ContentSpot> selected) {
        long minimum = properties.minSpotSeparationMeters();
        return selected.stream()
                .allMatch(chosen -> detourFilter.distance(chosen.coord(), candidate.coord()) >= minimum);
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
     *
     * <p>{@code exclude} 는 "다른 코스 보기"로 들어온 제외 목록이다. 앵커가 1곳뿐인 작품에서는
     * 여기서 바뀌는 일반 명소가 재생성의 유일한 변화이므로, 제외한 수만큼 후보를 더 받아 둔다.
     */
    private Selection fillWithGeneralSpots(Selection selection, List<ContentSpot> allSpots, Set<Long> exclude) {
        int missing = DayTemplate.MAX_SPOTS - selection.spots().size();
        if (missing <= 0) {
            return selection;
        }

        Set<Long> takenIds = selection.spots().stream().map(ContentSpot::placeId).collect(Collectors.toSet());
        // 사용자가 아무 앵커도 못 고른 극단적인 경우(큐레이터가 하나도 못 고른 경우)를 대비해,
        // 확정된 명소가 없으면 콘텐츠의 앵커 전체를 검색 중심으로 쓴다.
        List<ContentSpot> center = selection.spots().isEmpty() ? allSpots : selection.spots();
        List<Coord> anchorCoords = center.stream().map(ContentSpot::coord).toList();

        // 제외 대상이 후보 앞쪽을 차지하고 있을 수 있으니 그만큼 더 받는다.
        // 이미 저장된 장소는 importPlace 가 DB 조회로 끝내므로 추가 API 호출이 늘지 않는다.
        List<NearbySpot> nearby = generalSpotFinder.find(anchorCoords, missing + exclude.size());

        List<ContentSpot> merged = new ArrayList<>(selection.spots());
        Map<Long, SlotFilledBy> filledBy = new HashMap<>(selection.filledBy());
        Map<Long, String> reasons = new HashMap<>(selection.reasons());

        int[] order = {merged.size() + 1};
        appendGeneralSpots(nearby, merged, filledBy, takenIds, exclude, order);

        // 제외를 지키느라 3곳을 못 채웠다면 제외를 풀고 다시 채운다.
        // 재생성을 여러 번 누르면 주변 후보가 고갈되는데, 그때 코스가 2곳으로 짧아지는 것보다
        // 이전에 봤던 곳이 다시 나오는 편이 낫다.
        if (merged.size() < DayTemplate.MAX_SPOTS && !exclude.isEmpty()) {
            log.info("제외를 지키면 {}곳뿐이라 제외를 풀고 채웁니다.", merged.size());
            appendGeneralSpots(nearby, merged, filledBy, takenIds, Set.of(), order);
        }

        if (merged.size() < DayTemplate.MAX_SPOTS) {
            log.info("일반 관광명소로도 {}곳을 채우지 못해 {}곳으로 진행합니다.",
                    DayTemplate.MAX_SPOTS, merged.size());
        }

        return new Selection(List.copyOf(merged), filledBy, reasons);
    }

    /** 후보를 훑어 빈 자리를 채운다. 제외 목록을 바꿔 두 번 호출할 수 있도록 분리했다. */
    private void appendGeneralSpots(List<NearbySpot> nearby,
                                    List<ContentSpot> merged,
                                    Map<Long, SlotFilledBy> filledBy,
                                    Set<Long> takenIds,
                                    Set<Long> exclude,
                                    int[] order) {
        for (NearbySpot spot : nearby) {
            if (merged.size() >= DayTemplate.MAX_SPOTS) {
                return;
            }
            Place place = tourApiPlaceImporter.importPlace(spot.externalId(), PlaceType.SPOT);
            if (exclude.contains(place.getId())) {
                continue;   // 직전 루트에 나왔던 곳 — 재생성이니 다른 곳을 준다.
            }
            if (!takenIds.add(place.getId())) {
                continue;   // 이미 이 루트에 들어간 place 다.
            }
            merged.add(new ContentSpot(place, order[0]++));
            filledBy.put(place.getId(), SlotFilledBy.GENERAL);
        }
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
