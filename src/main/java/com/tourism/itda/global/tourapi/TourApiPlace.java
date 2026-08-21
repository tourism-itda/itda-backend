package com.tourism.itda.global.tourapi;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.place.entity.PlaceType;

/**
 * 관광API locationBasedList2 응답 한 건을 우리가 쓰는 필드만 추린 것.
 *
 * @param contentId 관광API 고유 ID. place.external_id 로 저장된다.
 * @param dist      관광API 가 계산해 준 검색 중심으로부터의 거리(m). 참고용이며,
 *                  실제 정렬은 우회거리로 다시 한다. 목록 조회에서만 채워진다.
 * @param overview  장소 소개글. detailCommon2 로 단건 조회했을 때만 채워진다
 *                  (목록 조회 응답에는 없다).
 */
public record TourApiPlace(
        String contentId,
        PlaceType placeType,
        String title,
        String category,
        String address,
        String imageUrl,
        Coord coord,
        Long dist,
        String overview) {

    /** 목록 조회 결과용 — overview 없이 만든다. */
    public static TourApiPlace ofListItem(String contentId, PlaceType placeType, String title, String category,
                                          String address, String imageUrl, Coord coord, Long dist) {
        return new TourApiPlace(contentId, placeType, title, category, address, imageUrl, coord, dist, null);
    }
}
