package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentCategory;

public record CategorySummaryResponse(
        @JsonProperty("category_id") Long categoryId,
        String type,
        String name
) {
    // TODO: Category 도메인 완성 후 category_id로 실제 카테고리 type/name 채우기
    public static CategorySummaryResponse from(ContentCategory contentCategory) {
        return new CategorySummaryResponse(contentCategory.getId().getCategoryId(), null, null);
    }
}
