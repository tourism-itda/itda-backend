package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.place.entity.Place;

/** 콘텐츠에 매핑된 촬영지 하나 — 장소 + 그 콘텐츠 안에서의 추천 순위. */
public record ContentSpot(Place place, int recommendOrder) {

    public Coord coord() {
        return new Coord(place.getLatitude(), place.getLongitude());
    }

    public Long placeId() {
        return place.getId();
    }
}
