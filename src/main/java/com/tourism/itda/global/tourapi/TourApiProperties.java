package com.tourism.itda.global.tourapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 한국관광공사 TourAPI(KorService2) 접속 설정.
 *
 * <p>{@code apiKey} 는 공공데이터포털의 서비스키다. 포털이 인코딩/디코딩 두 형태를 모두
 * 보여주는데, 어느 쪽을 넣어도 동작하도록 {@link TourApiClient} 에서 한 번만 인코딩한다.
 */
@ConfigurationProperties(prefix = "itda.tour-api")
public record TourApiProperties(
        String baseUrl,
        String apiKey,
        int numOfRows) {

    public TourApiProperties {
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = "https://apis.data.go.kr/B551011/KorService2/";
        }
        if (numOfRows <= 0) {
            numOfRows = 30;
        }
    }

    /** 키가 비어 있으면 관광API 호출을 건너뛰고 빈 후보를 돌려준다 (로컬 개발 편의). */
    public boolean isConfigured() {
        return apiKey != null && !apiKey.isBlank();
    }
}
