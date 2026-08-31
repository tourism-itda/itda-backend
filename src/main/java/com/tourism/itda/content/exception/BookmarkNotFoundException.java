package com.tourism.itda.content.exception;

public class BookmarkNotFoundException extends RuntimeException {

    public BookmarkNotFoundException(Long bookmarkId) {
        super("존재하지 않는 북마크입니다. bookmarkId=" + bookmarkId);
    }
}
