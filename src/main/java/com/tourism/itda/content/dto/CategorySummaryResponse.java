package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentCategory;

public record CategorySummaryResponse(
        @JsonProperty("category_id") Long categoryId,
        String type,
        String name
) {
    // TODO: Category 도메인 완성 후 category_id로 실제 카테고리 type/name 채우기
    // 실제 category_id ↔ 공공데이터 ID 매핑이 확정되면 이 placeholder 로직을 교체
    public static CategorySummaryResponse from(ContentCategory contentCategory) {
        return new CategorySummaryResponse(contentCategory.getId().getCategoryId(), "정보 준비중", "정보 준비중");
    }
}
