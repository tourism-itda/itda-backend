package com.tourism.itda.planner.route;

import java.util.List;

/**
 * 촬영지와 촬영지 사이의 '채워 넣을 구간'. 지도에 그리는 타원 하나가 이 구간 하나에 대응한다.
 *
 * <p>템플릿이 {@code 장소 → 식당 → 카페 → 장소 → 식당 → 장소} 라면 구간은 2개다:
 * <ul>
 *   <li>구간 0: 촬영지0 → 촬영지1 사이에 [식당, 카페]</li>
 *   <li>구간 1: 촬영지1 → 촬영지2 사이에 [식당]</li>
 * </ul>
 *
 * @param index          구간 번호 (0-based)
 * @param startSpotIndex 앞쪽 앵커가 몇 번째 촬영지인지
 * @param endSpotIndex   뒤쪽 앵커. {@code null} 이면 하루 마지막이라 뒤 앵커가 없다는 뜻
 *                       (우회거리 대신 앵커 주변 가까운 순으로 고른다)
 * @param slots          이 구간에서 채워야 할 슬롯들, 순서대로
 */
public record FillSegment(int index,
                          int startSpotIndex,
                          Integer endSpotIndex,
                          List<TemplateSlot> slots) {

    /** 뒤쪽 앵커가 없는 구간인가 (= 하루의 마지막 꼬리). */
    public boolean isTrailing() {
        return endSpotIndex == null;
    }
}
