package com.tourism.itda.global.kakao;

import com.tourism.itda.global.distance.Coord;

/**
 * 카카오 로컬 API 가 돌려준 장소 1건.
 *
 * <p>카카오는 경도를 {@code x}, 위도를 {@code y} 로 준다 — 위경도와 순서가 반대다.
 * 이 레코드로 변환한 뒤에는 {@link Coord}(위도, 경도) 순서만 쓴다.
 *
 * @param id           카카오 place id. {@code place.kakao_place_id} 에 저장해 중복 적재를 막는다.
 * @param categoryName {@code "여행 > 관광,명소 > 문화유적 > 릉,묘,총"} 같은 전체 경로 문자열
 */
public record KakaoPlace(
        String id,
        String name,
        String categoryName,
        String categoryGroupCode,
        String address,
        String roadAddress,
        String phone,
        String placeUrl,
        Coord coord) {

    /** 카테고리 경로에 이 조각들이 하나라도 있으면 관광지로 본다. */
    public boolean hasAnyCategory(String... fragments) {
        if (categoryName == null) {
            return false;
        }
        for (String fragment : fragments) {
            if (categoryName.contains(fragment)) {
                return true;
            }
        }
        return false;
    }

    /** 주차장·매표소·관리사무소처럼 본 장소가 아닌 부속 시설인가. */
    public boolean looksLikeSubFacility() {
        return hasAnyCategory("주차장", "관리,운영", "교통,수송")
                || nameContainsAny("주차장", "매표소", "화장실", "대여소", "관리사무소", "안내소");
    }

    private boolean nameContainsAny(String... fragments) {
        if (name == null) {
            return false;
        }
        for (String fragment : fragments) {
            if (name.contains(fragment)) {
                return true;
            }
        }
        return false;
    }
}
