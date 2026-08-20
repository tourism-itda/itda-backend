package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.Bookmark;

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
    // TODO: Place 도메인 완성 후 실제 장소 정보 채우기
    public static BookmarkListItemResponse from(Bookmark bookmark) {
        return new BookmarkListItemResponse(
                bookmark.getId(),
                bookmark.getPlaceId(),
                null,
                null,
                null,
                null,
                bookmark.getCreatedAt()
        );
    }
}
