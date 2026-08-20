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
    public static ContentPlaceListItemResponse of(ContentPlace contentPlace, boolean isBookmarked) {
        return new ContentPlaceListItemResponse(
                contentPlace.getId().getPlaceId(),
                null,
                null,
                contentPlace.getRecommendOrder(),
                null,
                null,
                isBookmarked
        );
    }
}
