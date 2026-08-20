package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.Media;

public record MediaSummaryResponse(
        String type,
        @JsonProperty("release_year") Integer releaseYear
) {
    public static MediaSummaryResponse from(Media media) {
        return new MediaSummaryResponse(media.getType(), media.getReleaseYear());
    }
}
