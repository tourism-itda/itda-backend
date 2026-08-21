package com.tourism.itda.planner.route;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Claude 가 돌려줄 구조화 응답. 스키마는 이 레코드에서 자동 생성된다.
 *
 * <p>좌표나 거리는 일부러 넣지 않았다. 동선 판정은 코드가 이미 끝냈고,
 * LLM 에게 수치 계산을 시키면 값을 지어내기 때문이다.
 */
public record SpotPick(

        @JsonPropertyDescription("고른 촬영지의 place_id. 반드시 후보 목록에 제시된 값 중 하나여야 한다.")
        long placeId,

        @JsonPropertyDescription("이 촬영지를 고른 이유. 한국어 한 문장, 60자 이내.")
        String reason) {
}
