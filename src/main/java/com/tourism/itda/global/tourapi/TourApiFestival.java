package com.tourism.itda.global.tourapi;

import java.time.LocalDate;

/**
 * 관광API searchFestival2(축제공연행사) 응답 한 건을 우리가 쓰는 필드만 추린 것.
 *
 * @param eventStartDate eventstartdate(yyyyMMdd) 파싱 결과. 파싱 실패 시 null.
 * @param eventEndDate   eventenddate(yyyyMMdd) 파싱 결과. 파싱 실패 시 null.
 */
public record TourApiFestival(
        String contentId,
        String title,
        String address,
        String imageUrl,
        LocalDate eventStartDate,
        LocalDate eventEndDate) {
}
