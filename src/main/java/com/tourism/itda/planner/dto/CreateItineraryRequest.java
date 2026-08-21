package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

/** No.28 POST /itineraries 요청 바디. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreateItineraryRequest(
        Long contentId,
        @NotBlank String title,
        LocalDate travelDate,
        String region,
        String durationLabel,
        @NotEmpty @Valid List<PlaceItemRequest> places
) {
}
