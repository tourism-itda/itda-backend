package com.tourism.itda.community.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * No.41 커뮤니티 상세의 stops[] 항목.
 *
 * <p>{@code itineraryPlaceId} 와 {@code placeId} 는 <b>다른 ID</b>다. 앞은 이 일정 안에서의 한 칸,
 * 뒤는 장소 자체({@code place.place_id})다. 북마크·장소상세는 {@code placeId} 를 써야 한다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommunityStopView(
        Long itineraryPlaceId,
        Long placeId,
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
