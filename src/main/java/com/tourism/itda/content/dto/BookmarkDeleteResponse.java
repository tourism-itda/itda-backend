package com.tourism.itda.content.dto;

public record BookmarkDeleteResponse(boolean success) {

    public static BookmarkDeleteResponse ok() {
        return new BookmarkDeleteResponse(true);
    }
}
