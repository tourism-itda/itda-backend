package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentPlace;

public record RelatedPlaceResponse(
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        String region
) {
    // TODO: Place 도메인 완성 후 place_id로 실제 장소 정보 채우기
    public static RelatedPlaceResponse from(ContentPlace contentPlace) {
        return new RelatedPlaceResponse(contentPlace.getId().getPlaceId(), null, null, null);
    }
}
