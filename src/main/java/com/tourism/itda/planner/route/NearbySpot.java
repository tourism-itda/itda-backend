package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.kakao.KakaoPlace;
import com.tourism.itda.global.tourapi.TourApiPlace;
import com.tourism.itda.place.entity.PlaceSource;

/**
 * TourAPI·카카오 어느 쪽에서 왔든 한 형태로 다루기 위한 통합 장소.
 *
 * <p>이 프로젝트는 한국관광공사 TourAPI 활용을 전제로 하므로, 일반 관광명소는
 * {@link GeneralSpotFinder} 가 TourAPI 로만 채운다. 식당·카페 후보({@link CandidateFinder})는
 * 카카오 로컬 API 로 찾는다. {@code source} 필드로 어느 쪽에서 왔는지 남겨 두면
 * 나중에 관광공사 데이터 활용 비중을 로그·응답에서 확인할 수 있다.
 */
public record NearbySpot(
        PlaceSource source,
        String externalId,
        String name,
        String category,
        Coord coord,
        String address,
        String imageUrl) {

    public static NearbySpot of(TourApiPlace place) {
        return new NearbySpot(
                PlaceSource.TOUR_API,
                place.contentId(),
                place.title(),
                place.category(),
                place.coord(),
                place.address(),
                place.imageUrl());
    }

    public static NearbySpot of(KakaoPlace place) {
        String address = (place.roadAddress() != null) ? place.roadAddress() : place.address();
        return new NearbySpot(
                PlaceSource.KAKAO,
                place.id(),
                place.name(),
                place.categoryName(),
                place.coord(),
                address,
                null);
    }

    /** 주차장·매표소·관리사무소처럼 본 장소가 아닌 부속 시설인가. */
    public boolean looksLikeSubFacility() {
        boolean categoryHit = category != null
                && (category.contains("주차장") || category.contains("관리,운영") || category.contains("교통,수송"));
        boolean nameHit = name != null
                && (name.contains("주차장") || name.contains("매표소") || name.contains("화장실")
                    || name.contains("대여소") || name.contains("관리사무소") || name.contains("안내소"));
        return categoryHit || nameHit;
    }
}
