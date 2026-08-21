package com.tourism.itda.place.entity;

/**
 * 장소의 성격. 하루 일정 템플릿의 슬롯 타입과 1:1 대응한다.
 *
 * <p>SPOT 은 콘텐츠 촬영지(우리 시드 데이터), RESTAURANT/CAFE 는 관광API 에서
 * 온디맨드로 가져와 place 에 upsert 되는 장소다. 숙소는 하루 일정이라 제외 — 팀 결정.
 */
public enum PlaceType {

    /** 촬영지 — content_place 로 콘텐츠와 연결된다. */
    SPOT,

    /** 식당 — 관광API contentTypeId=39 중 카페/전통찻집(cat3=A05020900) 이 아닌 것. */
    RESTAURANT,

    /** 카페 — 관광API contentTypeId=39, cat3=A05020900. */
    CAFE
}
