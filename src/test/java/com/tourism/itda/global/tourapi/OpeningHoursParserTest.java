package com.tourism.itda.global.tourapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class OpeningHoursParserTest {

    @Test
    @DisplayName("가장 흔한 형태를 읽는다")
    void parsesPlainRange() {
        OpeningHours hours = OpeningHoursParser.parse("09:00~18:00");

        assertThat(hours.openTime()).isEqualTo(LocalTime.of(9, 0));
        assertThat(hours.closeTime()).isEqualTo(LocalTime.of(18, 0));
        assertThat(hours.nightOpen()).isFalse();
    }

    @Test
    @DisplayName("관광API 가 섞어 보내는 HTML 태그를 걷어낸다")
    void stripsHtml() {
        OpeningHours hours = OpeningHoursParser.parse("<b>이용시간</b><br>10:00 - 22:00");

        assertThat(hours.openTime()).isEqualTo(LocalTime.of(10, 0));
        assertThat(hours.closeTime()).isEqualTo(LocalTime.of(22, 0));
        assertThat(hours.nightOpen()).isTrue();
    }

    @Test
    @DisplayName("'매일' 같은 수식어가 앞에 붙어도 읽는다")
    void ignoresLeadingWords() {
        OpeningHours hours = OpeningHoursParser.parse("매일 11:00 ~ 21:00");

        assertThat(hours.openTime()).isEqualTo(LocalTime.of(11, 0));
        assertThat(hours.closeTime()).isEqualTo(LocalTime.of(21, 0));
    }

    @Test
    @DisplayName("'09시~18시' 처럼 분이 없는 표기도 읽는다")
    void parsesHourOnlyNotation() {
        OpeningHours hours = OpeningHoursParser.parse("09시~18시");

        assertThat(hours.openTime()).isEqualTo(LocalTime.of(9, 0));
        assertThat(hours.closeTime()).isEqualTo(LocalTime.of(18, 0));
    }

    @Test
    @DisplayName("여러 기간이 섞여 있으면 첫 구간을 쓴다")
    void usesFirstRangeWhenSeveralAreListed() {
        OpeningHours hours = OpeningHoursParser.parse("하절기 09:00~19:00 / 동절기 09:00~17:00");

        assertThat(hours.openTime()).isEqualTo(LocalTime.of(9, 0));
        assertThat(hours.closeTime()).isEqualTo(LocalTime.of(19, 0));
    }

    @Test
    @DisplayName("20시 이후까지 열면 야간 방문 가능")
    void closingAfterThresholdIsNightOpen() {
        assertThat(OpeningHoursParser.parse("10:00~20:00").nightOpen()).isTrue();
        assertThat(OpeningHoursParser.parse("10:00~19:59").nightOpen()).isFalse();
    }

    @Test
    @DisplayName("자정을 넘겨 운영하면 야간 방문 가능")
    void overnightIsNightOpen() {
        OpeningHours hours = OpeningHoursParser.parse("18:00~02:00");

        assertThat(hours.nightOpen()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"상시개방", "24시간", "24 시간 운영", "상시 운영"})
    @DisplayName("상시 개방·24시간은 야간 방문 가능으로 본다")
    void alwaysOpenIsNightOpen(String raw) {
        assertThat(OpeningHoursParser.parse(raw).nightOpen()).isTrue();
    }

    @Test
    @DisplayName("'연중무휴'는 쉬는 날이 없다는 뜻일 뿐 24시간 운영이 아니다")
    void yearRoundIsNotAlwaysOpen() {
        OpeningHours hours = OpeningHoursParser.parse("연중무휴");

        assertThat(hours.nightOpen()).isFalse();
        assertThat(hours.openTime()).isNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"별도 문의", "홈페이지 참조", "033-123-4567"})
    @DisplayName("시각을 못 찾으면 모른다고 답한다 — 애매하면 밤에 안 넣는다")
    void unparseableStaysUnknown(String raw) {
        OpeningHours hours = OpeningHoursParser.parse(raw);

        assertThat(hours.isKnown()).isFalse();
        assertThat(hours.nightOpen()).isFalse();
    }

    @Test
    @DisplayName("여는 시각만 알아내면 야간 여부는 단정하지 않는다")
    void openOnlyDoesNotClaimNightOpen() {
        OpeningHours hours = OpeningHoursParser.parse("09:00 오픈");

        assertThat(hours.openTime()).isEqualTo(LocalTime.of(9, 0));
        assertThat(hours.closeTime()).isNull();
        assertThat(hours.nightOpen()).isFalse();
    }

    @Test
    @DisplayName("야간 기준 시각은 조정할 수 있다")
    void nightThresholdIsConfigurable() {
        assertThat(OpeningHoursParser.parse("10:00~18:00", LocalTime.of(17, 0)).nightOpen()).isTrue();
        assertThat(OpeningHoursParser.parse("10:00~18:00", LocalTime.of(19, 0)).nightOpen()).isFalse();
    }
}
