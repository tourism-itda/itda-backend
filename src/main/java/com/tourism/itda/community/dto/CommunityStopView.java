package com.tourism.itda.community.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/** No.41 커뮤니티 상세의 stops[] 항목. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommunityStopView(
        Long itineraryPlaceId,
        int visitOrder,
        String name,
        String category,
        String imageUrl,
        String description,
        String address,
        String openingHours,
        double latitude,
        double longitude
) {
}
