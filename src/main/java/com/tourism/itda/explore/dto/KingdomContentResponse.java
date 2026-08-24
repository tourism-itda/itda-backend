package com.tourism.itda.explore.dto;

import com.tourism.itda.content.entity.Content;

public record KingdomContentResponse(
        Long contentId,
        String title,
        Integer releaseYear,
        String mediaType,
        String posterUrl,
        String overview
) {

    public KingdomContentResponse(Content content) {
        this(
                content.getId(),
                content.getTitle(),
                content.getReleaseYear(),
                content.getMediaType(),
                content.getPosterUrl(),
                content.getOverview()
        );
    }
}