package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecommendSlotResponse(
        @JsonProperty("visit_order") int visitOrder,
        RecommendPlaceResponse place
) {
}
