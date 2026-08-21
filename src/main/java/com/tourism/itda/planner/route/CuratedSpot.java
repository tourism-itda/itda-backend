package com.tourism.itda.planner.route;

/**
 * 최종적으로 채워 넣기로 한 촬영지.
 *
 * @param spot   고른 촬영지
 * @param reason 왜 이 곳인지 한 줄 설명. LLM 이 붙여준 것이고, 폴백했으면 null.
 * @param byLlm  LLM 이 골랐는가. false 면 점수 1등으로 폴백한 것.
 */
public record CuratedSpot(ContentSpot spot, String reason, boolean byLlm) {

    static CuratedSpot fallback(ContentSpot spot) {
        return new CuratedSpot(spot, null, false);
    }
}
