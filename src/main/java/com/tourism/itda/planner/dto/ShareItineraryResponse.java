package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/** No.38 POST / No.39 DELETE 공통 응답: { "itinerary_id": ..., "is_shared": ... } */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ShareItineraryResponse(
        Long itineraryId,
        boolean isShared
) {
}
