package com.itda.common.tourapi;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 관광API 의 영업시간 원문을 구조화된 시각으로 바꾼다.
 *
 * <p>원문은 자유 텍스트라 형태가 제각각이다. 실제로 이런 것들이 온다:
 * <pre>
 *   "09:00~18:00"
 *   "매일 11:00 ~ 21:00"
 *   "&lt;b&gt;이용시간&lt;/b&gt;&lt;br&gt;10:00 - 22:00"
 *   "상시개방"
 *   "하절기 09:00~19:00 / 동절기 09:00~17:00"
 * </pre>
 *
 * <p>전략: HTML 을 걷어내고 앞에서부터 {@code HH:MM} 두 개를 찾아 여는 시각/닫는 시각으로 본다.
 * 여러 기간이 섞여 있으면 <b>첫 구간</b>을 쓴다 — 하절기/동절기를 구분해봐야
 * 여행 날짜별로 다시 판단해야 해서 실익이 없다.
 *
 * <p>파싱에 실패하면 {@link OpeningHours#unknown()} 을 돌려준다. 이 경우 {@code nightOpen}
 * 은 false 이고, 그러면 저녁 이후 슬롯 후보에서 밀려난다 — 모르면 밤에 안 넣는다.
 */
public final class OpeningHoursParser {

    /** 이 시각 이후에도 열려 있으면 '야간 방문 가능'으로 본다. 템플릿 마지막 촬영지가 이 시간대다. */
    public static final LocalTime DEFAULT_NIGHT_THRESHOLD = LocalTime.of(20, 0);

    private static final Pattern HTML_TAG = Pattern.compile("<[^>]*>");
    private static final Pattern TIME = Pattern.compile("(\\d{1,2})\\s*[:시]\\s*(\\d{2})?");

    /** 24시간 운영을 뜻하는 표현들. '연중무휴'는 쉬는 날이 없다는 뜻일 뿐이라 제외한다. */
    private static final Pattern ALWAYS_OPEN =
            Pattern.compile("상시\\s*(개방|운영)|24\\s*시간|종일\\s*개방|연중\\s*개방");

    private OpeningHoursParser() {
    }

    public static OpeningHours parse(String raw) {
        return parse(raw, DEFAULT_NIGHT_THRESHOLD);
    }

    public static OpeningHours parse(String raw, LocalTime nightThreshold) {
        if (raw == null || raw.isBlank()) {
            return OpeningHours.unknown();
        }

        String text = HTML_TAG.matcher(raw)
                .replaceAll(" ")
                .replace("&nbsp;", " ")
                .replace("&lt;", "<")
                .replace("&gt;", ">");

        if (ALWAYS_OPEN.matcher(text).find()) {
            return new OpeningHours(LocalTime.MIDNIGHT, LocalTime.MAX, true);
        }

        Matcher matcher = TIME.matcher(text);
        LocalTime open = nextTime(matcher);
        if (open == null) {
            return OpeningHours.unknown();
        }
        LocalTime close = nextTime(matcher);
        if (close == null) {
            // 여는 시각만 알아낸 경우. 닫는 시각을 모르니 야간 가능 여부도 단정하지 않는다.
            return new OpeningHours(open, null, false);
        }

        return new OpeningHours(open, close, isNightOpen(open, close, nightThreshold));
    }

    /**
     * 야간 방문 가능 판정.
     * 닫는 시각이 여는 시각보다 이르면 자정을 넘겨 운영하는 것으로 본다 (예: 18:00~02:00).
     */
    private static boolean isNightOpen(LocalTime open, LocalTime close, LocalTime threshold) {
        if (close.isBefore(open)) {
            return true;
        }
        return !close.isBefore(threshold);
    }

    private static LocalTime nextTime(Matcher matcher) {
        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            String minuteGroup = matcher.group(2);
            int minute = (minuteGroup == null) ? 0 : Integer.parseInt(minuteGroup);

            // "24:00" 은 자정을 뜻하는 흔한 표기다.
            if (hour == 24 && minute == 0) {
                return LocalTime.MAX;
            }
            if (hour <= 23 && minute <= 59) {
                return LocalTime.of(hour, minute);
            }
            // 날짜("2024")나 전화번호 조각이 걸린 경우 — 건너뛰고 다음 후보를 본다.
        }
        return null;
    }
}
