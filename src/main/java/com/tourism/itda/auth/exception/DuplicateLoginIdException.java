package com.tourism.itda.auth.exception;

public class DuplicateLoginIdException extends RuntimeException {
    public DuplicateLoginIdException(String loginId) {
        super(loginId + "은(는) 이미 사용중인 아이디입니다");
    }
}
