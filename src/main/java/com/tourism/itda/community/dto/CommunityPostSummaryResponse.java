package com.tourism.itda.community.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/** No.40 GET /community/posts 목록 항목. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
        // 이 루트가 만들어진 작품. 작품 없이 만든 일정은 셋 다 null.
        Long contentId,
        String contentTitle,
        String contentThumbnailUrl,
        String thumbnailUrl,
        List<String> tags
) {
}
