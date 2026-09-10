package com.tourism.itda.global.tourapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 주소 문자열을 연관관광지(TarRlteTar)가 요구하는 <b>법정동 시군구코드 5자리</b>로 옮긴다.
 *
 * <p>왜 필요한가: {@code TarRlteTarService1/searchKeyword1} 은 {@code areaCd}·{@code signguCd}·
 * {@code baseYm} 이 <b>전부 필수</b>이고, {@code signguCd} 는 <b>구(區) 단위까지 정확</b>해야 한다.
 * 실제로 "수원화성"을 {@code signguCd=41110}(수원시)로 물으면 0건, {@code 41115}(팔달구)로 물으면
 * 50건이 나온다. 시(市) 코드로는 절대 안 나온다. (2026-08-26 실호출 확인)
 *
 * <p>주의: 이 코드는 KorService2 의 {@code areaCode}(서울=1)와 <b>다른 체계</b>다.
 * 법정동 기준이라 서울은 11, 경기는 41이다.
 *
 * <p>테이블은 최초 1회만 받아 메모리에 든다(시도 1회 + 시도별 1회 ≈ 18콜). 관광API 응답을
 * 저장하는 게 아니라 코드표를 캐시하는 것이라 "실시간 연동" 원칙과 충돌하지 않는다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LdongCodeResolver {

    private final TourApiClient tourApiClient;

    /** 시도명 → 시도코드. "경기도" → "41" */
    private volatile Map<String, String> sidoByName;

    /** 시도코드 → (시군구명 → 시군구코드). "41" → {"수원시 팔달구" → "115"} */
    private volatile Map<String, Map<String, String>> sigunguBySido;

    /**
     * 주소에서 시군구코드 5자리를 뽑는다.
     *
     * <p>예: {@code "경기도 수원시 팔달구 정조로 910"} → {@code "41115"}
     *
     * <p>같은 시(市) 아래 구(區)가 여럿이면 <b>가장 긴 이름이 먼저 맞는 것</b>을 고른다.
     * "수원시"와 "수원시 팔달구"가 둘 다 후보일 때 구까지 붙은 쪽을 잡아야 하기 때문이다.
     *
     * @return 못 찾으면 {@link Optional#empty()}. 연관관광지는 없어도 되는 보조 데이터라
     *         호출부는 이걸 실패가 아니라 "이번엔 못 씀"으로 다뤄야 한다
     */
    public Optional<String> resolveSignguCd(String address) {
        if (address == null || address.isBlank()) {
            return Optional.empty();
        }
        ensureLoaded();

        String normalized = address.trim();
        for (Map.Entry<String, String> sido : sidoByName.entrySet()) {
            if (!normalized.startsWith(sido.getKey())) {
                continue;
            }
            String sidoCode = sido.getValue();
            String rest = normalized.substring(sido.getKey().length()).trim();

            String best = null;
            Map<String, String> sigungu = sigunguBySido.getOrDefault(sidoCode, Map.of());
            for (String name : sigungu.keySet()) {
                if (rest.startsWith(name) && (best == null || name.length() > best.length())) {
                    best = name;
                }
            }
            if (best != null) {
                return Optional.of(sidoCode + sigungu.get(best));
            }
            log.debug("시도({})는 찾았지만 시군구를 못 찾았습니다: {}", sido.getKey(), address);
            return Optional.empty();
        }

        log.debug("주소에서 시도를 찾지 못했습니다: {}", address);
        return Optional.empty();
    }

    /** 주소에서 시도코드 2자리만 뽑는다 ({@code areaCd} 용). */
    public Optional<String> resolveAreaCd(String address) {
        return resolveSignguCd(address).map(code -> code.substring(0, 2));
    }

    private void ensureLoaded() {
        if (sidoByName != null) {
            return;
        }
        synchronized (this) {
            if (sidoByName != null) {
                return;
            }
            Map<String, String> sido = new LinkedHashMap<>();
            Map<String, Map<String, String>> sigungu = new LinkedHashMap<>();

            List<LdongCode> sidoCodes = tourApiClient.findLdongCodes(null);
            if (sidoCodes.isEmpty()) {
                log.warn("법정동 시도 코드를 받지 못했습니다. 연관관광지 조회는 이번 기동에서 건너뜁니다.");
            }

            // 긴 이름이 먼저 매칭되도록 정렬해 둔다 ("전남광주통합특별시" vs "전라남도" 같은 경우).
            List<LdongCode> sorted = new ArrayList<>(sidoCodes);
            sorted.sort((a, b) -> b.name().length() - a.name().length());

            for (LdongCode code : sorted) {
                sido.put(code.name(), code.code());
                Map<String, String> children = new LinkedHashMap<>();
                for (LdongCode child : tourApiClient.findLdongCodes(code.code())) {
                    children.put(child.name(), child.code());
                }
                sigungu.put(code.code(), children);
            }

            this.sigunguBySido = sigungu;
            this.sidoByName = sido;   // 마지막에 대입해야 다른 스레드가 반쯤 찬 맵을 안 본다
            log.info("법정동 코드표 적재 완료 — 시도 {}개", sido.size());
        }
    }
}
