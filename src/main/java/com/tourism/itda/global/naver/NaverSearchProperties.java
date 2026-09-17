package com.tourism.itda.global.naver;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 네이버 검색 API(이미지 검색) 설정.
 *
 * <p>카카오 로컬 API 는 사진 필드를 아예 주지 않는다({@code place_name/category_name/x/y/place_url} 뿐).
 * 그래서 카카오로 보충한 식당·카페 사진은 네이버 이미지 검색으로 채운다.
 *
 * <p>키는 <a href="https://developers.naver.com">네이버 개발자센터</a>에서 애플리케이션을 만들고
 * "검색" API 를 추가하면 즉시 발급된다(무료, 하루 25,000건).
 *
 * @param clientId     없으면 이미지 조회를 건너뛴다. 앱 기동은 막지 않는다.
 * @param clientSecret 위와 같음
 * @param display      한 번에 받을 건수. 네이버 상한은 100 이지만 후보를 훑어 고르는 용도라 10 이면 넉넉하다.
 */
@ConfigurationProperties(prefix = "itda.naver-search")
public record NaverSearchProperties(
        String clientId,
        String clientSecret,
        int display) {

    /** 네이버 검색 API 가 한 페이지에 허용하는 최대 건수. */
    public static final int MAX_DISPLAY = 100;

    public NaverSearchProperties {
        if (display <= 0 || display > MAX_DISPLAY) {
            display = 10;
        }
    }

    public boolean isConfigured() {
        return clientId != null && !clientId.isBlank()
                && clientSecret != null && !clientSecret.isBlank();
    }
}
