package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.Bookmark;
import com.tourism.itda.place.entity.Place;

import java.time.LocalDateTime;

public record BookmarkListItemResponse(
        @JsonProperty("bookmark_id") Long bookmarkId,
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        String region,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("created_at") LocalDateTime createdAt
) {
    public static BookmarkListItemResponse from(Bookmark bookmark, Place place, String imageUrl) {
        if (place == null) {
            return new BookmarkListItemResponse(
                    bookmark.getId(), bookmark.getPlaceId(), null, null, null, null, bookmark.getCreatedAt()
            );
        }
        return new BookmarkListItemResponse(
                bookmark.getId(),
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getRegion(),
                imageUrl,
                bookmark.getCreatedAt()
        );
    }
}
