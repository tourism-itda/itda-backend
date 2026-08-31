package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentCategory;

public record ContentCategoryBriefResponse(
        @JsonProperty("category_id") Long categoryId,
        String name
) {
    // TODO: Category 도메인 완성 후 category_id로 실제 카테고리 name 채우기
    // 실제 category_id ↔ 공공데이터 ID 매핑이 확정되면 이 placeholder 로직을 교체
    public static ContentCategoryBriefResponse from(ContentCategory contentCategory) {
        return new ContentCategoryBriefResponse(contentCategory.getId().getCategoryId(), "미분류");
    }
}
