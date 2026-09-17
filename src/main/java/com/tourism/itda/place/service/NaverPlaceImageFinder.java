package com.tourism.itda.place.service;

import com.tourism.itda.global.naver.NaverImage;
import com.tourism.itda.global.naver.NaverSearchClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 상호·주소로 가게 사진 한 장을 찾는다. TourAPI 에 없는 카카오 식당·카페의 마지막 수단이다.
 *
 * <p>네이버 이미지 검색은 블로그·카페 이미지를 유사도순으로 줄 뿐이라 <b>그 가게 실사진이라는
 * 보장이 없다</b>. 상호가 흔하면("설화", "욜로") 전혀 다른 가게 사진이 1등으로 온다.
 * 그래서 두 가지로 거른다:
 *
 * <ol>
 *   <li><b>지역을 검색어에 붙인다.</b> "욜로" 가 아니라 "수원시 욜로" 로 묻는다.</li>
 *   <li><b>결과 제목에 상호가 들어간 것만 채택한다.</b> 제목에 상호가 없으면 그 가게를 다룬
 *       글의 이미지가 아닐 가능성이 크다. 하나도 못 고르면 <b>빈 값을 돌려준다</b> —
 *       엉뚱한 사진을 붙이느니 사진 없는 편이 낫다.</li>
 * </ol>
 *
 * <p>지점명("본점", "○○점")은 검색어에서 떼고도 한 번 더 물어본다. 블로그 글 제목은
 * 지점명을 잘 안 붙이기 때문이다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NaverPlaceImageFinder {

    /** 카드 썸네일로 쓰기에 너무 작은 사진은 버린다. */
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    /** 제목 대조에 쓸 상호의 최소 길이. 1글자 상호는 아무 제목에나 걸려서 신뢰할 수 없다. */
    private static final int MIN_CORE_LENGTH = 2;

    /** "본점", "영화점", "북문점" 처럼 뒤에 붙는 지점명. */
    private static final Pattern BRANCH_SUFFIX = Pattern.compile("\\s*\\S{0,6}점$");

    /** 주소 토큰 중 지역으로 쓸 만한 것. */
    private static final Pattern REGION_TOKEN = Pattern.compile(".+[시군구]$");

    private static final Pattern NON_WORD = Pattern.compile("[^0-9A-Za-z가-힣]");

    private final NaverSearchClient naverSearchClient;

    /**
     * 사진 한 장을 찾는다.
     *
     * @param name    상호 (예: "동북교자관 본점")
     * @param address 주소. 지역을 검색어에 붙이는 데만 쓴다. null 이어도 동작한다.
     * @return 믿을 만한 결과가 없으면 {@link Optional#empty()}
     */
    public Optional<String> find(String name, String address) {
        if (!naverSearchClient.isConfigured() || name == null || name.isBlank()) {
            return Optional.empty();
        }

        String core = coreNameOf(name);
        if (compact(core).length() < MIN_CORE_LENGTH) {
            return Optional.empty();   // 제목 대조를 못 하므로 시도 자체를 접는다.
        }

        for (String query : queriesFor(name, core, address)) {
            Optional<String> found = firstMatching(naverSearchClient.searchImages(query), core);
            if (found.isPresent()) {
                log.debug("네이버 이미지 채택 — query='{}' url={}", query, found.get());
                return found;
            }
        }

        log.debug("네이버 이미지에서 '{}' 에 맞는 사진을 찾지 못했습니다.", name);
        return Optional.empty();
    }

    // ── 내부 ────────────────────────────────────────────────────────────────

    /**
     * 시도할 검색어를 좁은 것부터 넓은 것 순으로 만든다.
     * 중복(지점명이 없어 core 와 name 이 같은 경우 등)은 제거한다.
     */
    private static List<String> queriesFor(String name, String core, String address) {
        String region = regionOf(address);
        Set<String> queries = new LinkedHashSet<>();
        if (region != null) {
            queries.add(region + " " + name);
            queries.add(region + " " + core);
        }
        queries.add(name);
        queries.add(core);
        return new ArrayList<>(queries);
    }

    /** 제목에 상호가 들어가고 충분히 큰 첫 이미지. */
    private static Optional<String> firstMatching(List<NaverImage> images, String core) {
        String compactCore = compact(core);
        for (NaverImage image : images) {
            if (!image.isLargeEnough(MIN_WIDTH, MIN_HEIGHT)) {
                continue;
            }
            if (compact(image.title()).contains(compactCore)) {
                return Optional.of(image.imageUrl());
            }
        }
        return Optional.empty();
    }

    /** "청하양꼬치 영화점" → "청하양꼬치". 떼고 나서 너무 짧아지면 원래 이름을 쓴다. */
    private static String coreNameOf(String name) {
        String stripped = BRANCH_SUFFIX.matcher(name.trim()).replaceFirst("").trim();
        return compact(stripped).length() >= MIN_CORE_LENGTH ? stripped : name.trim();
    }

    /** "경기 수원시 장안구 팔달로247번길 4" → "수원시". 시/군이 없으면 구, 그것도 없으면 첫 토큰. */
    private static String regionOf(String address) {
        if (address == null || address.isBlank()) {
            return null;
        }
        String[] tokens = address.trim().split("\\s+");
        String fallback = null;
        for (String token : tokens) {
            if (!REGION_TOKEN.matcher(token).matches()) {
                continue;
            }
            if (token.endsWith("시") || token.endsWith("군")) {
                return token;
            }
            if (fallback == null) {
                fallback = token;   // 구 단위 — 시/군을 끝까지 못 찾으면 이걸 쓴다.
            }
        }
        return (fallback != null) ? fallback : tokens[0];
    }

    /** 띄어쓰기·기호를 지우고 소문자로. 제목 대조는 표기 흔들림에 걸리기 쉽다. */
    private static String compact(String s) {
        return (s == null) ? "" : NON_WORD.matcher(s).replaceAll("").toLowerCase();
    }
}
