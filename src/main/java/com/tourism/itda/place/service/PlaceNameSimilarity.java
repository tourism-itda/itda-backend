package com.tourism.itda.place.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 장소 이름 두 개가 같은 곳을 가리키는지 재는 척도.
 *
 * <p>부분일치로는 부족하다. 우리가 {@code "거제포로수용소유적공원"} 으로 들고 있는 곳이
 * 관광API 에는 {@code "거제도 포로수용소 유적공원"} 으로 들어 있다 — 한 글자 차이로
 * 서로를 포함하지 않는다. 반대로 부분일치를 느슨하게 풀면 {@code "국립한글박물관"} 에
 * {@code "국립중앙박물관 전통염료식물원"} 이 걸린다.
 *
 * <p>그래서 <b>글자 2-gram 자카드 유사도</b>를 쓴다. 실측값:
 * <ul>
 *   <li>거제포로수용소유적공원 ↔ 거제도 포로수용소 유적공원 = <b>0.75</b> (같은 곳, 채택해야 함)</li>
 *   <li>거제포로수용소유적공원 ↔ 포로수용소유적박물관 = <b>0.462</b> (5m 옆의 다른 곳, 버려야 함)</li>
 *   <li>국립한글박물관 ↔ 국립중앙박물관 전통염료식물원 = <b>0.19</b></li>
 * </ul>
 * {@link #THRESHOLD} 는 이 사이를 가르도록 정했다.
 */
final class PlaceNameSimilarity {

    /** 이 값 이상이면 같은 곳으로 본다. 위 실측값(0.462 / 0.75) 사이에서 고른 값이다. */
    static final double THRESHOLD = 0.6;

    private static final Pattern NON_WORD = Pattern.compile("[^0-9A-Za-z가-힣]");

    private PlaceNameSimilarity() {
    }

    /** 같은 곳으로 볼 만한가. */
    static boolean matches(String a, String b) {
        return score(a, b) >= THRESHOLD;
    }

    /**
     * 0.0 ~ 1.0. 한쪽이 다른 쪽을 통째로 품고 있으면 1.0 이다
     * ({@code "장사상륙작전 전승기념관"} 처럼 표기만 다르고 같은 이름인 경우).
     */
    static double score(String a, String b) {
        String x = compact(a);
        String y = compact(b);
        if (x.isEmpty() || y.isEmpty()) {
            return 0d;
        }
        if (x.contains(y) || y.contains(x)) {
            return 1d;
        }

        Set<String> left = bigrams(x);
        Set<String> right = bigrams(y);
        if (left.isEmpty() || right.isEmpty()) {
            return 0d;
        }

        Set<String> intersection = new HashSet<>(left);
        intersection.retainAll(right);
        int union = left.size() + right.size() - intersection.size();
        return (double) intersection.size() / union;
    }

    private static Set<String> bigrams(String s) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < s.length() - 1; i++) {
            result.add(s.substring(i, i + 2));
        }
        return result;
    }

    /** 띄어쓰기·기호를 지우고 소문자로. 표기 흔들림("5.18" vs "5·18")을 흡수한다. */
    private static String compact(String s) {
        return (s == null) ? "" : NON_WORD.matcher(s).replaceAll("").toLowerCase();
    }
}
