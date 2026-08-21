package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;

/**
 * 관광API locationBasedList2 에 넘길 원형 검색 범위.
 *
 * <p>타원(우회거리 조건)은 관광API 가 지원하지 않으므로, 타원을 완전히 덮는 원으로
 * 넓게 긁어온 뒤 코드에서 타원 조건으로 걸러낸다. 이 레코드는 그 '넓게 긁는 원'이다.
 */
public record SearchArea(Coord center, long radiusMeters) {
}
