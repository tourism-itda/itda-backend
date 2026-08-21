package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentCategory;

public record ContentCategoryBriefResponse(
        @JsonProperty("category_id") Long categoryId,
        String name
) {
    public static ContentCategoryBriefResponse from(ContentCategory contentCategory) {
        var category = contentCategory.getCategory();
        return new ContentCategoryBriefResponse(category.getCategoryId(), category.getName());
    }
}
