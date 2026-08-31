package com.tourism.itda.global.tourapi;

import java.time.LocalTime;

/**
 * 영업시간 원문을 파싱한 결과.
 *
 * <p>파싱에 실패하면 {@link #unknown()} — 시각은 null 이고 {@code nightOpen} 은 false 다.
 * 애매하면 밤 슬롯에 넣지 않는 쪽이 안전하기 때문에 의도적으로 보수적이다.
 */
public record OpeningHours(LocalTime openTime, LocalTime closeTime, boolean nightOpen) {

    public static OpeningHours unknown() {
        return new OpeningHours(null, null, false);
    }

    public boolean isKnown() {
        return openTime != null || closeTime != null || nightOpen;
    }
}
