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
        // 이 루트가 만들어진 작품. 작품 없이 만든 일정은 셋 다 null.
        Long contentId,
        String contentTitle,
        String contentThumbnailUrl,
        List<String> tags,
        String thumbnailUrl,
        List<CommunityStopView> stops
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record AuthorView(String nickname, String profileUrl) {
    }
}
