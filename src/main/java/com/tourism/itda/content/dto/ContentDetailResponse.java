package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.Content;

import java.util.List;

public record ContentDetailResponse(
        @JsonProperty("content_id") Long contentId,
        String title,
        String summary,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        @JsonProperty("story_intro") String storyIntro,
        @JsonProperty("story_body") String storyBody,
        MediaSummaryResponse media,
        List<CategorySummaryResponse> categories,
        List<CharacterResponse> characters,
        @JsonProperty("story_sections") List<StorySectionResponse> storySections,
        @JsonProperty("fact_checks") List<FactCheckResponse> factChecks,
        @JsonProperty("related_places") List<RelatedPlaceResponse> relatedPlaces
) {
    public static ContentDetailResponse of(
            Content content,
            MediaSummaryResponse media,
            List<CategorySummaryResponse> categories,
            List<CharacterResponse> characters,
            List<StorySectionResponse> storySections,
            List<FactCheckResponse> factChecks,
            List<RelatedPlaceResponse> relatedPlaces
    ) {
        return new ContentDetailResponse(
                content.getId(),
                content.getTitle(),
                content.getSummary(),
                content.getThumbnailUrl(),
                content.getStoryIntro(),
                content.getStoryBody(),
                media,
                categories,
                characters,
                storySections,
                factChecks,
                relatedPlaces
        );
    }
}
