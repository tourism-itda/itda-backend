package com.itda.common.tourapi;

/**
 * detailIntro2 에서 영업시간 관련 필드만 추린 것.
 *
 * <p>후보 전체가 아니라 <b>최종 선택된 장소 1건</b>에만 호출한다.
 * 후보 20건마다 부르면 API 호출이 폭발한다.
 *
 * @param openingHours 원문. 음식점은 {@code opentimefood}, 관광지는 {@code usetime}.
 * @param restDate     쉬는 날 원문. 응답에 넣지는 않고 파싱 참고용.
 */
public record TourApiIntro(String openingHours, String restDate) {

    public static TourApiIntro empty() {
        return new TourApiIntro(null, null);
    }

    public boolean isEmpty() {
        return openingHours == null || openingHours.isBlank();
    }
}
