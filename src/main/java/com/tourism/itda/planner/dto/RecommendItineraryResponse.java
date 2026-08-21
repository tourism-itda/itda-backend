package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * No.24 GET /itineraries/recommend 응답 (v2, 저장 전 미리보기, DB 미저장).
 * { content_id, content_title, region, slots:[...] }
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecommendItineraryResponse(
        Long contentId,
        String contentTitle,
        String region,
        List<RecommendSlot> slots
) {
}
