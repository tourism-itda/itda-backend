package com.itda.place.dto;

import com.itda.place.domain.PlaceImage;

public record PlaceImageDto(
        Long placeImageId,
        String imageUrl,
        boolean isPrimary
) {
    public static PlaceImageDto from(PlaceImage image) {
        return new PlaceImageDto(image.getId(), image.getImageUrl(), image.isPrimary());
    }
}
