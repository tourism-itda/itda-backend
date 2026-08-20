package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateItineraryRequest(
        @JsonProperty("content_id") Long contentId,
        @NotBlank String title,
        @JsonProperty("travel_date") @NotNull LocalDate travelDate,
        String region,
        @JsonProperty("duration_label") String durationLabel,
        @NotEmpty List<PlaceItemRequest> places
) {
}
