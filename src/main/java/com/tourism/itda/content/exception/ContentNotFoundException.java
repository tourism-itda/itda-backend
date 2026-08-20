package com.tourism.itda.content.exception;

public class ContentNotFoundException extends RuntimeException {

    public ContentNotFoundException(Long contentId) {
        super("존재하지 않는 콘텐츠입니다. contentId=" + contentId);
    }
}
