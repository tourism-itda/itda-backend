package com.tourism.itda.global.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 카카오 로컬 API 설정.
 *
 * <p>REST 키는 카카오 로그인({@code kakao.client-id})과 같은 값이다.
 * 같은 앱의 키 하나로 로그인·로컬 API를 모두 쓰므로 일간 쿼터도 함께 소진된다.
 *
 * @param restApiKey 없으면 조회가 빈 목록을 돌려준다. 앱 기동은 막지 않는다.
 * @param size       한 번에 받을 건수. 카카오 상한은 15다.
 */
@ConfigurationProperties(prefix = "itda.kakao-local")
public record KakaoLocalProperties(
        String restApiKey,
        int size) {

    /** 카카오 로컬 API 가 한 페이지에 허용하는 최대 건수. */
    public static final int MAX_SIZE = 15;

    /** 카카오 로컬 API radius 상한(m). */
    public static final int MAX_RADIUS_M = 20_000;

    public KakaoLocalProperties {
        if (size <= 0 || size > MAX_SIZE) {
            size = MAX_SIZE;
        }
    }

    public boolean isConfigured() {
        return restApiKey != null && !restApiKey.isBlank();
    }
}
