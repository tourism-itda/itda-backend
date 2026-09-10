package com.tourism.itda.global.tourapi;

/**
 * 여행코스(contentTypeId=25)를 구성하는 장소 한 곳.
 *
 * <p>관광공사가 직접 큐레이션한 코스의 경유지다. 우리가 루트를 처음부터 만들지 않고
 * 골격으로 받아 쓸 수 있는 유일한 공식 데이터라 값이 크다.
 *
 * @param subContentId 경유지의 관광API contentId. 이 값으로 좌표·영업시간·이미지를 이어서 조회한다
 * @param name         경유지 이름. "점심식사(목화반점)" 처럼 식사 슬롯이 이름에 드러나기도 한다
 * @param overview     경유지 소개글. 없을 수 있다
 */
public record TourApiCourseStop(String subContentId, String name, String overview) {
}
