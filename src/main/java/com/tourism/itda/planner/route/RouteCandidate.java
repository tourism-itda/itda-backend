package com.tourism.itda.planner.route;

import com.tourism.itda.global.tourapi.TourApiPlace;

/**
 * 한 구간에 넣을 수 있는 식당/카페 후보 하나.
 *
 * @param place        관광API 에서 가져온 장소
 * @param detourMeters 이 곳을 들르면 동선이 늘어나는 거리(m).
 *                     뒤쪽 앵커가 없는 꼬리 구간에서는 앵커로부터의 직선거리다.
 * @param detourKnown  우회거리를 실제로 계산했는가. false 면 {@code detourMeters} 는
 *                     앵커로부터의 거리이므로 "동선 +N m" 로 표시하면 안 된다.
 */
public record RouteCandidate(TourApiPlace place, long detourMeters, boolean detourKnown) {
}
