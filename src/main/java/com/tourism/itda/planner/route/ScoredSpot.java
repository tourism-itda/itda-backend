package com.tourism.itda.planner.route;

import java.time.LocalTime;

/**
 * 채워 넣을 촬영지 후보와 그 점수.
 *
 * @param spot                후보 촬영지
 * @param addedDetourMeters   이 촬영지를 넣으면 동선이 늘어나는 거리(m)
 * @param wouldBeLast         넣었을 때 하루의 마지막 촬영지가 되는가
 * @param estimatedLastArrival 하루 마지막 칸의 예상 도착 시각
 * @param nightBonusApplied   야간 운영 가산점이 적용됐는가. 마지막 자리이고, 늦게까지 열고,
 *                            도착 예상이 실제로 늦은 시각일 때만 true.
 * @param closedOnArrival     <b>문 닫은 뒤 도착하게 되는가.</b> 마지막 자리인데 도착이 늦고
 *                            늦게까지 열지도 않는 경우다. 점수와 무관하게 뒤로 밀린다 —
 *                            "동선이 200m 짧다"가 "문이 닫혀 있다"를 이길 수는 없다.
 * @param score               0~1 정규화 점수. 높을수록 좋다.
 */
public record ScoredSpot(ContentSpot spot,
                         long addedDetourMeters,
                         boolean wouldBeLast,
                         LocalTime estimatedLastArrival,
                         boolean nightBonusApplied,
                         boolean closedOnArrival,
                         double score) {
}
