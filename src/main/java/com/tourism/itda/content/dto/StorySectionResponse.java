package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentStorySection;

public record StorySectionResponse(
        @JsonProperty("content_story_section_id") Long contentStorySectionId,
        String keyword,
        String body,
        @JsonProperty("sort_order") Integer sortOrder
) {
    public static StorySectionResponse from(ContentStorySection section) {
        return new StorySectionResponse(
                section.getId(),
                section.getKeyword(),
                section.getBody(),
                section.getSortOrder()
        );
    }
}
