package com.tourism.itda.auth.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email){
        super(email+"은(는) 이미 사용중인 이메일입니다");
    }
}
