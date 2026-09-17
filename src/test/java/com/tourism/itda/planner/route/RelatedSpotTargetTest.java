package com.tourism.itda.planner.route;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 사용자가 고른 관련 명소 개수를 그대로 지키는지 본다.
 *
 * <p>회귀 방지용이다: 예전에는 목표치가 {@code min(2, 앵커수)} 로 고정돼 있어서,
 * 앵커가 2곳인 작품(덕혜옹주·말모이·택시운전사)에서 사용자가 1곳만 골라도
 * 나머지 1곳이 자동으로 따라 들어갔다.
 */
@DisplayName("관련 명소 목표 개수")
class RelatedSpotTargetTest {

    @Test
    @DisplayName("앵커 2곳인 작품에서 1곳만 고르면 1곳만 쓴다")
    void respectsSinglePickWhenTwoAnchorsExist() {
        // 덕혜옹주: 앵커 2곳. 1곳을 골랐으면 남은 두 칸은 일반 명소 몫이다.
        assertThat(RoutePlanner.relatedSpotTarget(1, 2)).isEqualTo(1);
    }

    @Test
    @DisplayName("앵커가 많아도 고른 개수를 넘지 않는다")
    void neverExceedsUserPickCount() {
        assertThat(RoutePlanner.relatedSpotTarget(1, 3)).isEqualTo(1);
        assertThat(RoutePlanner.relatedSpotTarget(1, 10)).isEqualTo(1);
        assertThat(RoutePlanner.relatedSpotTarget(2, 10)).isEqualTo(2);
    }

    @Test
    @DisplayName("아무것도 안 고르면 자동추천 — 상한까지 채운다")
    void fillsUpToMaxWhenNothingPicked() {
        assertThat(RoutePlanner.relatedSpotTarget(0, 3)).isEqualTo(RoutePlanner.MAX_RELATED_SPOTS);
        assertThat(RoutePlanner.relatedSpotTarget(0, 10)).isEqualTo(RoutePlanner.MAX_RELATED_SPOTS);
    }

    @Test
    @DisplayName("앵커가 상한보다 적으면 있는 만큼만")
    void limitedByAnchorCount() {
        // 앵커 1곳인 작품이 26편 중 20편이다. 여기서 2를 목표로 잡으면 매번 못 채운다.
        assertThat(RoutePlanner.relatedSpotTarget(0, 1)).isEqualTo(1);
    }
}
