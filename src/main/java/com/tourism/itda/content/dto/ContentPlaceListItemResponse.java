package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentPlace;

public record ContentPlaceListItemResponse(
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        @JsonProperty("recommend_order") Integer recommendOrder,
        @JsonProperty("image_url") String imageUrl,
        String description,
        @JsonProperty("is_bookmarked") Boolean isBookmarked
) {
    // TODO: Place 도메인 완성 후 실제 장소 정보 채우기
    // 실제 place_id ↔ 공공데이터 ID 매핑이 확정되면 이 placeholder 로직을 교체
    public static ContentPlaceListItemResponse of(ContentPlace contentPlace, boolean isBookmarked) {
        return new ContentPlaceListItemResponse(
                contentPlace.getId().getPlaceId(),
                "정보 준비중",
                "미분류",
                contentPlace.getRecommendOrder(),
                null,
                null,
                isBookmarked
        );
    }
}
