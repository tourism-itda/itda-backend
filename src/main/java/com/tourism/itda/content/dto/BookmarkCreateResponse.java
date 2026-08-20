package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookmarkCreateResponse(
        @JsonProperty("bookmark_id") Long bookmarkId
) {
}
