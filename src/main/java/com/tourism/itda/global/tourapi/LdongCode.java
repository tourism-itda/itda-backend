package com.tourism.itda.global.tourapi;

/**
 * 법정동 코드 한 건 (ldongCode2 응답).
 *
 * <p>시도는 2자리({@code 41} 경기도), 시군구는 3자리({@code 115} 수원시 팔달구)로 내려온다.
 * 연관관광지(TarRlteTar)가 요구하는 {@code signguCd} 는 이 둘을 이어 붙인 5자리다 → {@code 41115}.
 *
 * @param code 코드. 시도 2자리 / 시군구 3자리
 * @param name 이름. 시군구는 {@code "수원시 팔달구"} 처럼 상위 시(市)를 포함해 내려온다
 */
public record LdongCode(String code, String name) {
}
