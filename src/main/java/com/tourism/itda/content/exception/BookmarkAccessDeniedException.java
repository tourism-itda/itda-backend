package com.tourism.itda.content.exception;

public class BookmarkAccessDeniedException extends RuntimeException {

    public BookmarkAccessDeniedException() {
        super("본인의 북마크만 삭제할 수 있습니다.");
    }
}
