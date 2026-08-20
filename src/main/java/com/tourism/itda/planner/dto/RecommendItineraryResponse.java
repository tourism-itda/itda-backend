package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * GET /api/itineraries/recommend 응답. 저장 전 미리보기 — DB에 아무것도 쓰지 않는다.
 */
public record RecommendItineraryResponse(
        @JsonProperty("content_id") Long contentId,
        @JsonProperty("content_title") String contentTitle,
        String region,
        List<RecommendSlotResponse> slots
) {
}
