package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentCategory;

public record CategorySummaryResponse(
        @JsonProperty("category_id") Long categoryId,
        String type,
        String name
) {
    public static CategorySummaryResponse from(ContentCategory contentCategory) {
        var category = contentCategory.getCategory();
        return new CategorySummaryResponse(category.getCategoryId(), category.getType(), category.getName());
    }
}
