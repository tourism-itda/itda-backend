package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

/** No.26 내 플래너 목록의 항목 (v2). */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItinerarySummaryResponse(
        Long itineraryId,
        String title,
        String contentTitle,   // content.title 조인
        LocalDate travelDate,
        String region,
        String durationLabel,
        long placeCount,       // itinerary_place 건수(계산)
        String thumbnailUrl    // content.thumbnail_url 조인
) {
}
