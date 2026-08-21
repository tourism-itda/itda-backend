package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.place.entity.Place;

public record ContentPlaceListItemResponse(
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        @JsonProperty("recommend_order") Integer recommendOrder,
        @JsonProperty("image_url") String imageUrl,
        String description,
        @JsonProperty("is_bookmarked") Boolean isBookmarked
) {
    public static ContentPlaceListItemResponse of(ContentPlace contentPlace, Place place, String imageUrl, boolean isBookmarked) {
        if (place == null) {
            return new ContentPlaceListItemResponse(
                    contentPlace.getId().getPlaceId(), null, null, contentPlace.getRecommendOrder(), null, null, isBookmarked
            );
        }
        return new ContentPlaceListItemResponse(
                place.getId(),
                place.getName(),
                place.getCategory(),
                contentPlace.getRecommendOrder(),
                imageUrl,
                place.getDescription(),
                isBookmarked
        );
    }
}
