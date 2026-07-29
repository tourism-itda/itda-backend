package com.itda.itinerary.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

/** No.28 POST /itineraries 요청 바디. */
public record CreateItineraryRequest(
        Long contentId,
        @NotBlank String title,
        LocalDate travelDate,
        String region,
        String durationLabel,
        @NotEmpty @Valid List<PlaceItemRequest> places
) {
}
