package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookmarkCreateRequest(
        @JsonProperty("place_id") Long placeId
) {
}
