package com.tourism.itda.auth.exception;

public class DuplicateNicknameException extends RuntimeException{
    public DuplicateNicknameException(String nickname) {
        super(nickname+"은(는) 이미 사용중인 닉네임입니다");
    }
}
