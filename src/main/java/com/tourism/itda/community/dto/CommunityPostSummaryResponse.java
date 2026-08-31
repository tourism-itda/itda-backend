package com.tourism.itda.community.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/** No.40 GET /community/posts 목록 항목. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommunityPostSummaryResponse(
        Long itineraryId,
        String title,
        String authorNickname,
        String authorProfileUrl,
        Double rating,
        long reviewCount,
        long placeCount,
        String region,
        String durationLabel,
        String thumbnailUrl,
        List<String> tags
) {
}
