package com.tourism.itda.content.exception;

public class BookmarkAlreadyExistsException extends RuntimeException {

    public BookmarkAlreadyExistsException(Long placeId) {
        super("이미 북마크한 장소입니다. placeId=" + placeId);
    }
}
