package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.place.entity.Place;

public record RelatedPlaceResponse(
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        String region
) {
    public static RelatedPlaceResponse from(ContentPlace contentPlace, Place place) {
        if (place == null) {
            return new RelatedPlaceResponse(contentPlace.getId().getPlaceId(), null, null, null);
        }
        return new RelatedPlaceResponse(place.getId(), place.getName(), place.getCategory(), place.getRegion());
    }
}
