package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItineraryIdResponse(
        @JsonProperty("itinerary_id") Long itineraryId
) {
}
