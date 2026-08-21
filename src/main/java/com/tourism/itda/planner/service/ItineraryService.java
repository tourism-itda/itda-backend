package com.tourism.itda.planner.service;

import com.tourism.itda.global.distance.DistanceCalculator;
import com.tourism.itda.global.exception.ForbiddenException;
import com.tourism.itda.global.exception.InvalidRequestException;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.planner.entity.Itinerary;
import com.tourism.itda.planner.entity.ItineraryPlace;
import com.tourism.itda.planner.entity.ItineraryPlaceStatus;
import com.tourism.itda.planner.dto.*;
import com.tourism.itda.planner.repository.ItineraryPlaceRepository;
import com.tourism.itda.planner.repository.ItineraryRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final ItineraryPlaceRepository itineraryPlaceRepository;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final ContentRepository contentRepository;
    private final ContentPlaceRepository contentPlaceRepository;
    private final DistanceCalculator distanceCalculator;

    // =========================================================
    // No.24 추천 일정 (비저장)
    // =========================================================
    public RecommendItineraryResponse recommend(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new NotFoundException("콘텐츠를 찾을 수 없습니다."));

        List<ContentPlace> mappings = contentPlaceRepository
                .findByIdContentIdOrderByRecommendOrderAsc(contentId);
        if (mappings.isEmpty()) {
            throw new NotFoundException("추천할 장소가 없습니다.");
        }

        List<Long> placeIds = mappings.stream().map(cp -> cp.getId().getPlaceId()).toList();
        Map<Long, Place> placeMap = placeMap(placeIds);
        Map<Long, String> imageMap = primaryImageMap(placeIds);

        String region = null;
        List<RecommendSlot> slots = new ArrayList<>();
        for (int i = 0; i < mappings.size(); i++) {
            ContentPlace cp = mappings.get(i);
            Place p = placeMap.get(cp.getId().getPlaceId());
            if (p == null) continue;
            if (region == null) region = p.getRegion();

            long[] toNext = {-1, -1};
            if (i + 1 < mappings.size()) {
                Place np = placeMap.get(mappings.get(i + 1).getId().getPlaceId());
                if (np != null) toNext = distanceBetween(p, np);
            }
            slots.add(new RecommendSlot(
                    cp.getRecommendOrder(),
                    new RecommendSlot.RecommendPlace(
                            p.getId(), p.getName(), p.getCategory(), p.getDescription(),
                            imageMap.get(p.getId()), p.getOpeningHours(),
                            toNext[0] < 0 ? null : toNext[0],
                            toNext[1] < 0 ? null : toNext[1],
                            p.getLatitude(), p.getLongitude())));
        }

        return new RecommendItineraryResponse(contentId, content.getTitle(), region, slots);
    }

    // =========================================================
    // No.25 일정 저장
    // =========================================================
    @Transactional
    public ItineraryIdResponse create(Long userId, CreateItineraryRequest req) {
        Itinerary itinerary = Itinerary.builder()
                .userId(userId)
                .contentId(req.contentId())
                .title(req.title())
                .travelDate(req.travelDate())
                .region(req.region())
                .durationLabel(req.durationLabel())
                .build();

        for (ItineraryPlace ip : toItineraryPlaces(req.places())) {
            itinerary.addPlace(ip);
        }

        Itinerary saved = itineraryRepository.save(itinerary);
        return new ItineraryIdResponse(saved.getId());
    }

    // =========================================================
    // No.26 내 플래너 목록
    // =========================================================
    public List<ItinerarySummaryResponse> getMyItineraries(Long userId) {
        List<Itinerary> itineraries =
                itineraryRepository.findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(userId);

        // content 제목/썸네일 일괄 조회
        List<Long> contentIds = itineraries.stream()
                .map(Itinerary::getContentId).filter(Objects::nonNull).distinct().toList();
        Map<Long, Content> contentMap = contentIds.isEmpty() ? Map.of()
                : contentRepository.findAllById(contentIds).stream()
                    .collect(Collectors.toMap(Content::getId, c -> c, (a, b) -> a));

        return itineraries.stream()
                .map(it -> {
                    Content c = it.getContentId() == null ? null : contentMap.get(it.getContentId());
                    return new ItinerarySummaryResponse(
                            it.getId(), it.getTitle(),
                            c == null ? null : c.getTitle(),
                            it.getTravelDate(), it.getRegion(), it.getDurationLabel(),
                            itineraryPlaceRepository.countByItineraryId(it.getId()),
                            c == null ? null : c.getThumbnailUrl());
                })
                .toList();
    }

    // =========================================================
    // No.27 저장 일정 상세
    // =========================================================
    public ItineraryDetailResponse getDetail(Long userId, Long itineraryId) {
        return buildDetail(loadOwned(userId, itineraryId));
    }

    // =========================================================
    // No.28 일정 수정 (부분 수정, places 전달 시 전체 교체)
    // =========================================================
    @Transactional
    public ItineraryIdResponse update(Long userId, Long itineraryId, UpdateItineraryRequest req) {
        Itinerary itinerary = loadOwned(userId, itineraryId);

        itinerary.updateTitle(req.title());
        itinerary.updateTravelDate(req.travelDate());
        itinerary.updateRegion(req.region());
        itinerary.updateDurationLabel(req.durationLabel());

        if (req.places() != null) {
            itinerary.replacePlaces(toItineraryPlaces(req.places()));
        }
        return new ItineraryIdResponse(itinerary.getId());
    }

    // =========================================================
    // No.29 일정 삭제 (soft delete — 커뮤니티 공유/리뷰 FK 무결성)
    // =========================================================
    @Transactional
    public void delete(Long userId, Long itineraryId) {
        loadOwned(userId, itineraryId).softDelete(LocalDateTime.now());
    }

    // =========================================================
    // 내부 헬퍼
    // =========================================================
    private Itinerary loadOwned(Long userId, Long itineraryId) {
        Itinerary itinerary = itineraryRepository.findByIdAndDeletedAtIsNull(itineraryId)
                .orElseThrow(() -> new NotFoundException("일정을 찾을 수 없습니다."));
        if (!itinerary.isOwnedBy(userId)) {
            throw new ForbiddenException("본인의 일정만 접근할 수 있습니다.");
        }
        return itinerary;
    }

    private List<ItineraryPlace> toItineraryPlaces(List<PlaceItemRequest> items) {
        List<Long> placeIds = items.stream().map(PlaceItemRequest::placeId).toList();
        validatePlacesExist(placeIds);
        return items.stream()
                .map(i -> ItineraryPlace.builder()
                        .placeId(i.placeId())
                        .dayNumber(i.dayNumber() != null ? i.dayNumber() : 1)
                        .visitOrder(i.visitOrder())
                        .status(parseStatus(i.status()))
                        .memo(i.memo())
                        .build())
                .toList();
    }

    private void validatePlacesExist(List<Long> placeIds) {
        Set<Long> distinct = new HashSet<>(placeIds);
        long found = placeRepository.findAllById(distinct).size();
        if (found != distinct.size()) {
            throw new InvalidRequestException("존재하지 않는 place_id 가 포함되어 있습니다.");
        }
    }

    private ItineraryPlaceStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return ItineraryPlaceStatus.PENDING;
        }
        try {
            return ItineraryPlaceStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("status 값이 올바르지 않습니다: " + status);
        }
    }

    private ItineraryDetailResponse buildDetail(Itinerary itinerary) {
        List<ItineraryPlace> places = itinerary.getPlaces().stream()
                .sorted(Comparator.comparingInt(ItineraryPlace::getDayNumber)
                        .thenComparingInt(ItineraryPlace::getVisitOrder))
                .toList();

        List<Long> placeIds = places.stream().map(ItineraryPlace::getPlaceId).toList();
        Map<Long, Place> placeMap = placeMap(placeIds);
        Map<Long, String> imageMap = primaryImageMap(placeIds);

        List<ItineraryPlaceView> views = new ArrayList<>();
        for (int i = 0; i < places.size(); i++) {
            ItineraryPlace cur = places.get(i);
            Place p = placeMap.get(cur.getPlaceId());

            Long distanceM = null, durationMin = null;
            if (i + 1 < places.size()) {
                ItineraryPlace next = places.get(i + 1);
                Place np = placeMap.get(next.getPlaceId());
                // 같은 day 안에서만 다음 장소까지 계산
                if (next.getDayNumber() == cur.getDayNumber() && p != null && np != null) {
                    long[] d = distanceBetween(p, np);
                    distanceM = d[0];
                    durationMin = d[1];
                }
            }

            views.add(new ItineraryPlaceView(
                    cur.getId(), cur.getPlaceId(),
                    cur.getDayNumber(), cur.getVisitOrder(),
                    cur.getStatus().name(),
                    distanceM, durationMin, cur.getMemo(),
                    p != null ? p.getName() : null,
                    p != null ? p.getCategory() : null,
                    p != null ? p.getDescription() : null,
                    imageMap.get(cur.getPlaceId()),
                    p != null ? p.getOpeningHours() : null,
                    p != null ? p.getLatitude() : 0,
                    p != null ? p.getLongitude() : 0));
        }

        String contentTitle = itinerary.getContentId() == null ? null
                : contentRepository.findById(itinerary.getContentId())
                    .map(Content::getTitle).orElse(null);

        return new ItineraryDetailResponse(
                itinerary.getId(), itinerary.getTitle(),
                itinerary.getContentId(), contentTitle,
                itinerary.getTravelDate(), itinerary.getRegion(), itinerary.getDurationLabel(),
                itinerary.isShared(), views);
    }

    private long[] distanceBetween(Place a, Place b) {
        long dist = distanceCalculator.distanceMeters(
                a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
        long dur = distanceCalculator.durationMinutes(
                a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
        return new long[]{dist, dur};
    }

    private Map<Long, Place> placeMap(List<Long> placeIds) {
        if (placeIds.isEmpty()) return Map.of();
        return placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, p -> p, (a, b) -> a));
    }

    private Map<Long, String> primaryImageMap(List<Long> placeIds) {
        if (placeIds.isEmpty()) return Map.of();
        return placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(placeIds).stream()
                .collect(Collectors.toMap(PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));
    }
}
