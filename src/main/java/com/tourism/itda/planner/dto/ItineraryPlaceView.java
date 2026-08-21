package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * No.27 일정 상세의 places[] 항목 (v2).
 * to_next_* 는 같은 day 안 다음 장소까지의 값(마지막이면 null).
 * v2: address/region 없음, opening_hours 포함.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItineraryPlaceView(
        Long itineraryPlaceId,
        Long placeId,
        int dayNumber,
        int visitOrder,
        String status,
        Long toNextDistanceM,
        Long toNextDurationMin,
        String memo,
        String name,
        String category,
        String description,
        String imageUrl,
        String openingHours,
        double latitude,
        double longitude
) {
}
