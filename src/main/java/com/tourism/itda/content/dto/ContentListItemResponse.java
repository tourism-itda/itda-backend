package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.Content;

public record ContentListItemResponse(
        @JsonProperty("content_id") Long contentId,
        String title,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        MediaSummaryResponse media,
        ContentCategoryBriefResponse category,
        @JsonProperty("view_count") Long viewCount,
        String summary
) {
    public static ContentListItemResponse of(
            Content content,
            MediaSummaryResponse media,
            ContentCategoryBriefResponse category
    ) {
        return new ContentListItemResponse(
                content.getId(),
                content.getTitle(),
                content.getThumbnailUrl(),
                media,
                category,
                content.getViewCount(),
                content.getSummary()
        );
    }
}
