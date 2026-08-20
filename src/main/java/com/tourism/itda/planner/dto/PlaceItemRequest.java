package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record PlaceItemRequest(
        @JsonProperty("place_id") @NotNull Long placeId,
        @JsonProperty("day_number") Integer dayNumber,
        @JsonProperty("visit_order") int visitOrder,
        String status,
        String memo
) {
}
