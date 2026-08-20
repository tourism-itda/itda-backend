package com.tourism.itda.planner.service;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.planner.dto.RecommendItineraryResponse;
import com.tourism.itda.planner.dto.RecommendPlaceResponse;
import com.tourism.itda.planner.dto.RecommendSlotResponse;
import com.tourism.itda.planner.entity.ContentSpot;
import com.tourism.itda.planner.entity.Spot;
import com.tourism.itda.planner.repository.ContentSpotRepository;
import com.tourism.itda.planner.repository.SpotRepository;
import com.tourism.itda.planner.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlannerService {

    private final ContentRepository contentRepository;
    private final ContentSpotRepository contentSpotRepository;
    private final SpotRepository spotRepository;

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

        List<Long> spotIds = mappings.stream().map(ContentSpot::getSpotId).toList();
        Map<Long, Spot> spots = spotRepository.findAllById(spotIds).stream()
                .collect(Collectors.toMap(Spot::getId, s -> s, (a, b) -> a));

        String region = null;
        List<RecommendSlotResponse> slots = new ArrayList<>();
        for (int i = 0; i < mappings.size(); i++) {
            Spot spot = spots.get(mappings.get(i).getSpotId());
            if (spot == null) {
                continue;
            }
            if (region == null) {
                region = spot.getRegion();
            }

            Long distanceM = null;
            Long durationMin = null;
            if (i + 1 < mappings.size()) {
                Spot next = spots.get(mappings.get(i + 1).getSpotId());
                if (next != null) {
                    distanceM = DistanceCalculator.distanceMeters(
                            spot.getLatitude(), spot.getLongitude(), next.getLatitude(), next.getLongitude());
                    durationMin = DistanceCalculator.durationMinutes(
                            spot.getLatitude(), spot.getLongitude(), next.getLatitude(), next.getLongitude());
                }
            }

            slots.add(new RecommendSlotResponse(
                    mappings.get(i).getRecommendOrder(),
                    RecommendPlaceResponse.of(spot, distanceM, durationMin)));
        }

        return new RecommendItineraryResponse(contentId, content.getTitle(), region, slots);
    }
}
