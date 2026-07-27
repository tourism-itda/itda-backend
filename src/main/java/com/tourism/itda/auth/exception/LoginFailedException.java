package com.tourism.itda.auth.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super ("아이디 또는 비밀번호를 잘못 입력하였습니다.");
    }
}
