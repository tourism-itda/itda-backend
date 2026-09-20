package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.Content;

import java.util.List;

public record ContentDetailResponse(
        @JsonProperty("content_id") Long contentId,
        String title,
        String summary,
        // TMDB 원본 줄거리·태그라인. summary/story* 는 AI 생성이고 이 둘은 원본이다.
        String overview,
        // 태그라인은 없는 작품이 많고 TMDB 가 빈 문자열("")로 주기도 해서, 값이 있을 때만 응답에 포함한다.
        @JsonInclude(JsonInclude.Include.NON_EMPTY) String tagline,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        @JsonProperty("story_intro") String storyIntro,
        @JsonProperty("story_body") String storyBody,
        @JsonProperty("story_source") String storySource,
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
                content.getOverview(),
                content.getTagline(),
                content.getThumbnailUrl(),
                content.getStoryIntro(),
                content.getStoryBody(),
                content.getStorySource() != null ? content.getStorySource().name() : null,
                media,
                categories,
                characters,
                storySections,
                factChecks,
                relatedPlaces
        );
    }
}
