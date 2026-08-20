package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.place.entity.PlaceImage;

public record PlaceImageResponse(
        @JsonProperty("place_image_id") Long placeImageId,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("is_primary") boolean isPrimary
) {
    public static PlaceImageResponse from(PlaceImage image) {
        return new PlaceImageResponse(image.getId(), image.getImageUrl(), image.isPrimary());
    }
}
