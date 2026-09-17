package com.tourism.itda.place.service;

/**
 * 주소에서 {@code place.region} 값을 뽑는다.
 *
 * <p>TourAPI 임포트와 카카오 임포트가 같은 규칙을 써야 일정 목록의 지역 표기가 갈리지 않아
 * 한 곳에 모아 둔다. region 은 표시에만 쓰여서 주소 앞머리(시/도)면 충분하다.
 */
final class AddressRegion {

    /** {@code place.region} 컬럼 길이에 맞춘 상한. */
    private static final int MAX_LENGTH = 50;

    private AddressRegion() {
    }

    static String of(String address) {
        if (address == null || address.isBlank()) {
            return null;
        }
        String first = address.trim().split("\\s+")[0];
        return first.length() > MAX_LENGTH ? first.substring(0, MAX_LENGTH) : first;
    }
}
