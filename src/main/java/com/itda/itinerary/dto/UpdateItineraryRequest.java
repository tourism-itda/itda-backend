package com.itda.itinerary.dto;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

/**
 * No.31 PATCH /itineraries/:id 요청 바디. 모든 필드 optional(null=미변경).
 * ⚠️ places 가 전달되면 해당 일정의 itinerary_place 전체 교체.
 */
public record UpdateItineraryRequest(
        String title,
        LocalDate travelDate,
        String region,
        String durationLabel,
        @Valid List<PlaceItemRequest> places
) {
}
