package com.tourism.itda.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 도메인 예외 공통 부모. 상태코드와 에러코드를 함께 들고 다닌다.
 */
@Getter
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    protected BusinessException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
