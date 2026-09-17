package com.tourism.itda.place.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 사진 백필의 2단계(좌표로 훑고 이름으로 거르기)가 서 있는 기준이라 실측값을 그대로 박아 둔다.
 *
 * <p>여기 쓰인 이름 쌍은 전부 관광API 실호출에서 나온 것이다. 임계값을 건드리면
 * 엉뚱한 사진이 붙거나(낮추면) 멀쩡한 곳이 비거나(올리면) 한다.
 */
class PlaceNameSimilarityTest {

    @Test
    @DisplayName("표기만 다른 같은 곳은 채택한다")
    void acceptsSamePlaceWithDifferentSpelling() {
        // 우리 DB 는 '거제', 관광API 는 '거제도' — 부분일치로는 서로를 못 품는다.
        assertThat(PlaceNameSimilarity.matches("거제포로수용소유적공원", "거제도 포로수용소 유적공원")).isTrue();

        // 띄어쓰기만 다른 경우는 포함 관계라 1.0 이다.
        assertThat(PlaceNameSimilarity.score("장사상륙작전 전승기념관", "장사상륙작전 전승기념관")).isEqualTo(1d);
        assertThat(PlaceNameSimilarity.matches("학도의용군 전승기념관", "학도의용군전승기념관")).isTrue();
    }

    @Test
    @DisplayName("좌표는 겹치지만 다른 곳은 버린다")
    void rejectsNearbyButDifferentPlace() {
        // 거제 포로수용소 유적공원에서 5m 옆에 있는 별개 시설. 좌표만 보면 통과해 버린다.
        assertThat(PlaceNameSimilarity.matches("거제포로수용소유적공원", "포로수용소유적박물관")).isFalse();

        // 좌표 검색이 실제로 물어온 오답들.
        assertThat(PlaceNameSimilarity.matches("국립한글박물관", "국립중앙박물관 전통염료식물원")).isFalse();
        assertThat(PlaceNameSimilarity.matches("학도의용군 전승기념관", "죽림사(포항)")).isFalse();
        assertThat(PlaceNameSimilarity.matches("5.18기념공원", "광주일신여상")).isFalse();
        assertThat(PlaceNameSimilarity.matches("조선어학회 한말글 수호기념탑", "세종로공원")).isFalse();
    }

    @Test
    @DisplayName("임계값은 실측 오답(0.462)과 실측 정답(0.75) 사이에 있다")
    void thresholdSitsBetweenMeasuredScores() {
        double wrong = PlaceNameSimilarity.score("거제포로수용소유적공원", "포로수용소유적박물관");
        double right = PlaceNameSimilarity.score("거제포로수용소유적공원", "거제도 포로수용소 유적공원");

        assertThat(wrong).isLessThan(PlaceNameSimilarity.THRESHOLD);
        assertThat(right).isGreaterThanOrEqualTo(PlaceNameSimilarity.THRESHOLD);
    }

    @Test
    @DisplayName("기호 표기 흔들림을 흡수한다")
    void ignoresPunctuation() {
        assertThat(PlaceNameSimilarity.matches("5.18기념공원", "5·18 기념공원")).isTrue();
    }

    @Test
    @DisplayName("빈 이름은 매칭하지 않는다")
    void emptyNamesNeverMatch() {
        assertThat(PlaceNameSimilarity.matches(null, "국립한글박물관")).isFalse();
        assertThat(PlaceNameSimilarity.matches("", "국립한글박물관")).isFalse();
        assertThat(PlaceNameSimilarity.matches("   ", "국립한글박물관")).isFalse();
    }
}
