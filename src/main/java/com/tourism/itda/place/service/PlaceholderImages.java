package com.tourism.itda.place.service;

import com.tourism.itda.place.entity.PlaceType;

/**
 * 사진을 못 구한 장소에 붙일 기본 이미지.
 *
 * <p>카카오 로컬 API 에는 사진 필드가 아예 없고, 관광API 도 장소에 따라 {@code firstimage} 를
 * 주지 않는다. 그런 장소를 빈 칸으로 두면 루트 카드가 깨져 보이므로 분류에 맞는 기본 이미지를 준다.
 *
 * <p><b>네이버 이미지 검색을 쓰지 않는 이유:</b> 2026-07-31 자로 네이버 개발자센터에서 검색 API
 * 신규 발급이 끊겨 NAVER API Hub 로만 받을 수 있고, 2026-09-07 시행 특약이 검색 API 를
 * "네이버 검색 결과를 제공하기 위한 목적"으로 한정한다. 남의 블로그 사진을 우리 가게 카드에
 * 박는 것은 그 목적이 아니고, 사진의 저작권도 네이버가 아니라 글쓴이에게 있다.
 *
 * <p>이미지는 {@code src/main/resources/static/images/place-types/} 에 있는 SVG 다.
 * 경로 표기는 인물 이미지({@code /images/persons/...})와 같은 루트 상대 경로를 쓴다.
 *
 * <p><b>DB 에 저장하지 않는다.</b> 응답을 만들 때만 끼워 넣는다. 저장해 버리면
 * {@code place_image} 가 진짜 사진인지 기본 이미지인지 구분이 안 되고, 나중에 관광API 가
 * 그 장소 사진을 갖게 돼도 백필이 대상에서 빼 버린다.
 */
public final class PlaceholderImages {

    private static final String BASE = "/images/place-types/";

    private static final String SPOT = BASE + "spot.svg";
    private static final String CAFE = BASE + "cafe.svg";
    private static final String RESTAURANT = BASE + "restaurant.svg";
    private static final String KOREAN = BASE + "restaurant-korean.svg";
    private static final String CHINESE = BASE + "restaurant-chinese.svg";
    private static final String JAPANESE = BASE + "restaurant-japanese.svg";
    private static final String WESTERN = BASE + "restaurant-western.svg";

    private PlaceholderImages() {
    }

    /**
     * 이 URL 이 기본 이미지인가. 프론트가 "사진 있음" 배지를 잘못 붙이지 않도록 응답에 같이 실어 준다.
     */
    public static boolean isPlaceholder(String imageUrl) {
        return imageUrl != null && imageUrl.startsWith(BASE);
    }

    /**
     * 분류에 맞는 기본 이미지를 고른다.
     *
     * <p>{@code category} 는 출처마다 생김새가 다르다 — 카카오는
     * {@code "음식점 > 중식 > 중국요리"} 처럼 경로 문자열이고, 관광API 는
     * {@code "한식"} 같은 한국어 분류명이다. 그래서 정확히 비교하지 않고 조각을 찾는다.
     *
     * @param placeType 장소 성격. 분류로 못 가릴 때의 기준이 된다.
     * @param category  {@code place.category}. null 이어도 된다.
     */
    public static String forPlace(PlaceType placeType, String category) {
        if (placeType == PlaceType.SPOT) {
            return SPOT;
        }
        if (placeType == PlaceType.CAFE) {
            return CAFE;
        }

        String c = (category == null) ? "" : category;
        if (contains(c, "중식", "중국")) {
            return CHINESE;
        }
        if (contains(c, "일식", "일본", "초밥", "돈까스", "라멘", "우동")) {
            return JAPANESE;
        }
        if (contains(c, "양식", "이탈리", "프랑스", "스테이크", "피자", "파스타", "햄버거")) {
            return WESTERN;
        }
        if (contains(c, "한식", "분식", "국밥", "백반", "고기", "찌개", "칼국수")) {
            return KOREAN;
        }
        return RESTAURANT;
    }

    private static boolean contains(String source, String... fragments) {
        for (String fragment : fragments) {
            if (source.contains(fragment)) {
                return true;
            }
        }
        return false;
    }
}
