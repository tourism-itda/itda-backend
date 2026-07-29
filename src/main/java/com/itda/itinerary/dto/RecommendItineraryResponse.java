package com.itda.itinerary.dto;

import java.util.List;

/**
 * No.24 GET /itineraries/recommend 응답 (v2, 저장 전 미리보기, DB 미저장).
 * { content_id, content_title, region, slots:[...] }
 */
public record RecommendItineraryResponse(
        Long contentId,
        String contentTitle,
        String region,
        List<RecommendSlot> slots
) {
}
