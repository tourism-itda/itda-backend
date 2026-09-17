package com.tourism.itda.community.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/** No.41 GET /community/posts/:itinerary_id 응답. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CommunityPostDetailResponse(
        Long itineraryId,
        String title,
        String description,
        AuthorView author,
        Double rating,
        long reviewCount,
        long placeCount,
        String region,
        String durationLabel,
        List<String> tags,
        String thumbnailUrl,
        List<CommunityStopView> stops
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record AuthorView(String nickname, String profileUrl) {
    }
}
