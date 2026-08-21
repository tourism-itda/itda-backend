package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import com.itda.common.exception.NotFoundException;
import com.itda.content.domain.Content;
import com.itda.content.domain.ContentPlace;
import com.itda.content.repository.ContentPlaceRepository;
import com.itda.content.repository.ContentRepository;
import com.itda.itinerary.dto.*;
import com.itda.place.domain.Place;
import com.itda.place.domain.PlaceImage;
import com.itda.place.domain.PlaceType;
import com.itda.place.repository.PlaceImageRepository;
import com.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 하루 루트를 조립한다. DB 에 아무것도 쓰지 않는 미리보기다.
 *
 * <p>템플릿은 {@code 장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) → 장소}.
 * 촬영지 칸만 채우고 식당·카페 칸은 비워 둔다 — 취향 편차가 큰 영역이라
 * 사용자가 지도에서 직접 고르게 하는 것이 팀 결정이다.
 *
 * <p>촬영지가 3곳에 못 미치면 {@link SpotScorer}(동선·순위·야간운영) 로 후보를 좁히고
 * {@link SpotCurator}(Claude) 가 그중 하나를 고른다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutePlanner {

    private final ContentRepository contentRepository;
    private final ContentPlaceRepository contentPlaceRepository;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final DetourFilter detourFilter;
    private final TimelineEstimator timelineEstimator;
    private final SpotScorer spotScorer;
    private final SpotCurator spotCurator;
    private final RouteProperties properties;

    public RoutePlanResponse plan(RoutePlanRequest request) {
        Content content = contentRepository.findById(request.contentId())
                .orElseThrow(() -> new NotFoundException("콘텐츠를 찾을 수 없습니다."));

        List<ContentSpot> allSpots = loadSpots(request.contentId());
        if (allSpots.isEmpty()) {
            throw new NotFoundException("이 콘텐츠에 등록된 촬영지가 없습니다.");
        }

        long allowance = properties.clampAllowance(request.allowanceMeters());
        List<Long> requestedIds = request.spotPlaceIdsOrEmpty();

        Selection selection = selectSpots(allSpots, requestedIds, content.getTitle(), allowance);
        List<ContentSpot> spots = selection.spots();

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
                List.copyOf(slotViews),
                buildSegmentViews(segments, spots, allowance));
    }

    // ── 촬영지 선택 ──────────────────────────────────────────────────────────

    /**
     * 사용자가 고른 촬영지를 확정하고, 3곳에 모자라면 채운다.
     *
     * <p>사용자가 아무것도 안 고르면 자동추천 모드로, 전부 서버가 고른다.
     * 3곳을 넘겨 보내면 앞에서부터 3곳만 쓴다 — UI 에서도 막지만 서버가 최종 방어.
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
            if (selectedIds.add(id) && selected.size() < DayTemplate.MAX_SPOTS) {
                selected.add(spot);
            }
        }
        selected.sort(Comparator.comparingInt(ContentSpot::recommendOrder));

        Map<Long, SlotFilledBy> filledBy = new HashMap<>();
        Map<Long, String> reasons = new HashMap<>();
        selected.forEach(spot -> filledBy.put(spot.placeId(), SlotFilledBy.USER));

        int target = Math.min(DayTemplate.MAX_SPOTS, allSpots.size());
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
            selected.sort(Comparator.comparingInt(ContentSpot::recommendOrder));

            filledBy.put(pick.spot().placeId(), pick.byLlm() ? SlotFilledBy.CURATED : SlotFilledBy.SCORED);
            if (pick.reason() != null) {
                reasons.put(pick.spot().placeId(), pick.reason());
            }
        }

        return new Selection(List.copyOf(selected), filledBy, reasons);
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

    /** 콘텐츠에 매핑된 촬영지 전체를 recommend_order 순으로. 직접선택 UI 에도 쓰인다. */
    public List<ContentSpot> loadSpots(Long contentId) {
        List<ContentPlace> mappings = contentPlaceRepository.findByContentIdOrderByRecommendOrderAsc(contentId);
        if (mappings.isEmpty()) {
            return List.of();
        }

        List<Long> placeIds = mappings.stream().map(ContentPlace::getPlaceId).toList();
        Map<Long, Place> places = placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, place -> place, (a, b) -> a));

        List<ContentSpot> spots = new ArrayList<>(mappings.size());
        for (ContentPlace mapping : mappings) {
            Place place = places.get(mapping.getPlaceId());
            if (place != null) {
                spots.add(new ContentSpot(place, mapping.getRecommendOrder()));
            }
        }
        return List.copyOf(spots);
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
