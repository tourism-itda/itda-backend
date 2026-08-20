package com.tourism.itda.planner.service;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import com.tourism.itda.planner.dto.CreateItineraryRequest;
import com.tourism.itda.planner.dto.ItineraryIdResponse;
import com.tourism.itda.planner.dto.PlaceItemRequest;
import com.tourism.itda.planner.dto.RecommendItineraryResponse;
import com.tourism.itda.planner.dto.RecommendPlaceResponse;
import com.tourism.itda.planner.dto.RecommendSlotResponse;
import com.tourism.itda.planner.entity.ContentSpot;
import com.tourism.itda.planner.entity.Itinerary;
import com.tourism.itda.planner.entity.ItineraryPlace;
import com.tourism.itda.planner.entity.ItineraryPlaceStatus;
import com.tourism.itda.planner.repository.ContentSpotRepository;
import com.tourism.itda.planner.repository.ItineraryRepository;
import com.tourism.itda.planner.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlannerService {

    private final ContentRepository contentRepository;
    private final ContentSpotRepository contentSpotRepository;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final ItineraryRepository itineraryRepository;

    /**
     * 콘텐츠 기반 추천 일정 미리보기. DB에 아무것도 쓰지 않는다.
     *
     * <p>content_id 는 이미 {@code GET /api/contents/{id}} 로 조회/저장된 콘텐츠라고 가정한다
     * (TMDB 재조회는 여기서 하지 않는다 — content 도메인 책임).
     */
    public RecommendItineraryResponse recommend(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new NotFoundException("콘텐츠를 찾을 수 없습니다."));

        List<ContentSpot> mappings = contentSpotRepository.findByContentIdOrderByRecommendOrderAsc(contentId);
        if (mappings.isEmpty()) {
            throw new NotFoundException("추천할 장소가 없습니다.");
        }

        List<Long> placeIds = mappings.stream().map(ContentSpot::getPlaceId).toList();
        Map<Long, Place> places = placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, p -> p, (a, b) -> a));
        Map<Long, String> images = placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(placeIds).stream()
                .collect(Collectors.toMap(PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));

        String region = null;
        List<RecommendSlotResponse> slots = new ArrayList<>();
        for (int i = 0; i < mappings.size(); i++) {
            Place place = places.get(mappings.get(i).getPlaceId());
            if (place == null) {
                continue;
            }
            if (region == null) {
                region = place.getRegion();
            }

            Long distanceM = null;
            Long durationMin = null;
            if (i + 1 < mappings.size()) {
                Place next = places.get(mappings.get(i + 1).getPlaceId());
                if (next != null) {
                    distanceM = DistanceCalculator.distanceMeters(
                            place.getLatitude(), place.getLongitude(), next.getLatitude(), next.getLongitude());
                    durationMin = DistanceCalculator.durationMinutes(
                            place.getLatitude(), place.getLongitude(), next.getLatitude(), next.getLongitude());
                }
            }

            slots.add(new RecommendSlotResponse(
                    mappings.get(i).getRecommendOrder(),
                    RecommendPlaceResponse.of(place, images.get(place.getId()), distanceM, durationMin)));
        }

        return new RecommendItineraryResponse(contentId, content.getTitle(), region, slots);
    }

    /** POST /api/itineraries — 일정 저장. */
    @Transactional
    public ItineraryIdResponse create(Long userId, CreateItineraryRequest request) {
        validatePlacesExist(request.places());

        Itinerary itinerary = new Itinerary(
                userId, request.contentId(), request.title(),
                request.travelDate(), request.region(), request.durationLabel());

        for (PlaceItemRequest item : request.places()) {
            itinerary.addPlace(new ItineraryPlace(
                    item.placeId(), item.dayNumber(), item.visitOrder(),
                    parseStatus(item.status()), item.memo()));
        }

        Itinerary saved = itineraryRepository.save(itinerary);
        return new ItineraryIdResponse(saved.getId());
    }

    private void validatePlacesExist(List<PlaceItemRequest> items) {
        Set<Long> distinct = new HashSet<>();
        items.forEach(item -> distinct.add(item.placeId()));
        long found = placeRepository.findAllById(distinct).size();
        if (found != distinct.size()) {
            throw new IllegalArgumentException("존재하지 않는 place_id 가 포함되어 있습니다.");
        }
    }

    private ItineraryPlaceStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return ItineraryPlaceStatus.PENDING;
        }
        try {
            return ItineraryPlaceStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("status 값이 올바르지 않습니다: " + status);
        }
    }
}
