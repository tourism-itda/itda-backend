package com.tourism.itda.planner.route;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * "루트 변경하기"가 사용자가 고른 촬영지를 지워 버리지 않는지 본다.
 *
 * <p>회귀 방지용이다: 프론트는 직전 루트의 place_id 를 통째로 {@code exclude_place_ids} 로 보내는데,
 * 거기에는 사용자가 {@code spot_place_ids} 로 고른 촬영지도 들어 있다. 예전에는 그걸 그대로 적용해서
 * 광해에서 창덕궁(30)을 고르고 변경하기를 누르면 창덕궁이 빠지고 종묘(31)가 대신 들어왔다.
 */
@DisplayName("루트 변경하기 — 고른 촬영지 보호")
class ExcludeWithUserPicksTest {

    @Test
    @DisplayName("고른 촬영지는 제외 목록에 있어도 빠지지 않는다")
    void userPickSurvivesExclusion() {
        // 광해 재현: 창덕궁(30)을 고른 채로 직전 루트 전체(30, 122, 29)를 제외로 보냄
        var exclude = RoutePlanner.excludeWithoutUserPicks(List.of(30L, 122L, 29L), List.of(30L));

        assertThat(exclude).doesNotContain(30L);
        assertThat(exclude).containsExactlyInAnyOrder(122L, 29L);
    }

    @Test
    @DisplayName("고른 게 없으면 제외 목록을 그대로 쓴다")
    void keepsExclusionWhenNothingPicked() {
        var exclude = RoutePlanner.excludeWithoutUserPicks(List.of(30L, 122L), List.of());

        assertThat(exclude).containsExactlyInAnyOrder(30L, 122L);
    }

    @Test
    @DisplayName("제외가 비어 있으면 빈 집합")
    void emptyExclusionStaysEmpty() {
        assertThat(RoutePlanner.excludeWithoutUserPicks(List.of(), List.of(30L))).isEmpty();
        assertThat(RoutePlanner.excludeWithoutUserPicks(List.of(), List.of())).isEmpty();
    }

    @Test
    @DisplayName("고른 것이 제외 전체와 겹치면 제외가 사라진다")
    void allExcludedAreUserPicks() {
        assertThat(RoutePlanner.excludeWithoutUserPicks(List.of(30L, 29L), List.of(30L, 29L))).isEmpty();
    }
}
