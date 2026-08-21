package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.PlaceImage;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlaceImageDto(
        Long placeImageId,
        String imageUrl,
        boolean isPrimary
) {
    public static PlaceImageDto from(PlaceImage image) {
        return new PlaceImageDto(image.getId(), image.getImageUrl(), image.isPrimary());
    }
}
