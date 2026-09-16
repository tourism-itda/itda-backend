package com.tourism.itda.planner.discovery;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

/**
 * Claude 구조화 출력의 최상위 컨테이너.
 *
 * <p>Anthropic SDK 의 구조화 출력({@code outputConfig})은 최상위가 객체여야 해서
 * 배열 {@link DiscoveredPlace} 를 바로 스키마로 쓸 수 없다 — 그래서 한 겹 감싼다.
 */
public record DiscoveryResult(

        @JsonPropertyDescription("작품과 관련된 실제 장소 후보 목록. 확신이 없으면 빈 목록을 돌려줘도 된다. 최대 4개.")
        List<DiscoveredPlace> places) {
}
