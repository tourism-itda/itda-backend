package com.tourism.itda.global.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 컨트롤러 파라미터에 로그인 userId(Long) 를 주입한다.
 * required=true (기본): 토큰 없으면 401.
 * required=false: 비로그인 허용 — 없으면 null 주입 (예: 인증 '선택' API).
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    boolean required() default true;
}
