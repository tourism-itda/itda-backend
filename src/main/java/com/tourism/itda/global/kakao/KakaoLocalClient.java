package com.tourism.itda.global.kakao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.global.distance.Coord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 카카오 로컬 API(키워드·카테고리 검색) 호출기.
 *
 * <p>관광API 와 달리 실패해도 HTTP 상태코드로 알 수 있고, 결과 0건이면 그냥
 * {@code documents} 가 빈 배열로 온다 — 그래도 JsonNode 로 방어적으로 읽는 원칙은 동일하게 따른다.
 *
 * <p><b>x=경도, y=위도.</b> {@link Coord} 는 (위도, 경도) 순서라 카카오와 정반대이므로
 * 파라미터를 만들 때·응답을 파싱할 때 매번 순서를 헷갈리지 않도록 주의한다.
 *
 * <p>후보를 못 찾는 것은 정상 상황이므로 예외를 던지지 않고 빈 리스트를 돌려준다.
 */
@Slf4j
@Component
public class KakaoLocalClient {

    private static final String KEYWORD_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private static final String CATEGORY_URL = "https://dapi.kakao.com/v2/local/search/category.json";

    private final KakaoLocalProperties properties;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public KakaoLocalClient(KakaoLocalProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder()
                .requestFactory(requestFactory())
                .build();
    }

    /** 키워드 검색. center 가 null 이면 전국 검색, 있으면 center 기준 radiusMeters 안에서. */
    public List<KakaoPlace> searchKeyword(String query, Coord center, Integer radiusMeters) {
        if (!properties.isConfigured() || query == null || query.isBlank()) {
            log.warn("카카오 로컬 API 키가 설정되지 않아 키워드 검색을 건너뜁니다. itda.kakao-local.rest-api-key 를 확인하세요.");
            return List.of();
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(KEYWORD_URL)
                .queryParam("query", query)
                .queryParam("size", properties.size());
        if (center != null) {
            builder.queryParam("x", center.longitude())
                    .queryParam("y", center.latitude())
                    .queryParam("radius", clampRadius(radiusMeters));
        }

        return callForPlaces(builder.build().encode().toUri());
    }

    /** 카테고리 검색. categoryGroupCode 예: AT4(관광명소) FD6(음식점) CE7(카페) CT1(문화시설). */
    public List<KakaoPlace> searchCategory(String categoryGroupCode, Coord center, int radiusMeters, boolean sortByDistance) {
        if (!properties.isConfigured() || categoryGroupCode == null || categoryGroupCode.isBlank() || center == null) {
            log.warn("카카오 로컬 API 키가 설정되지 않아 카테고리 검색을 건너뜁니다. itda.kakao-local.rest-api-key 를 확인하세요.");
            return List.of();
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CATEGORY_URL)
                .queryParam("category_group_code", categoryGroupCode)
                .queryParam("x", center.longitude())
                .queryParam("y", center.latitude())
                .queryParam("radius", clampRadius(radiusMeters))
                .queryParam("size", properties.size());
        if (sortByDistance) {
            builder.queryParam("sort", "distance");
        }

        return callForPlaces(builder.build().encode().toUri());
    }

    // ── 내부 ────────────────────────────────────────────────────────────────

    private List<KakaoPlace> callForPlaces(URI uri) {
        try {
            String body = restClient.get()
                    .uri(uri)
                    .header("Authorization", "KakaoAK " + properties.restApiKey())
                    .retrieve()
                    .body(String.class);

            if (body == null || body.isBlank()) {
                return List.of();
            }

            JsonNode root = objectMapper.readTree(body);
            JsonNode documents = root.path("documents");
            if (!documents.isArray()) {
                return List.of();
            }

            List<KakaoPlace> result = new ArrayList<>();
            for (JsonNode doc : documents) {
                KakaoPlace place = toPlace(doc);
                if (place != null) {
                    result.add(place);
                }
            }
            return result;

        } catch (Exception e) {
            log.warn("카카오 로컬 API 호출 실패: {}", e.toString());
            return List.of();
        }
    }

    private KakaoPlace toPlace(JsonNode doc) {
        Double lng = parseDouble(text(doc, "x"));
        Double lat = parseDouble(text(doc, "y"));
        if (lat == null || lng == null) {
            return null;   // 좌표 없는 항목은 동선 계산에 쓸 수 없다.
        }

        String id = text(doc, "id");
        if (id == null) {
            return null;
        }

        return new KakaoPlace(
                id,
                text(doc, "place_name"),
                text(doc, "category_name"),
                text(doc, "category_group_code"),
                text(doc, "address_name"),
                text(doc, "road_address_name"),
                text(doc, "phone"),
                text(doc, "place_url"),
                new Coord(lat, lng));
    }

    private static int clampRadius(Integer radiusMeters) {
        int radius = (radiusMeters == null) ? KakaoLocalProperties.MAX_RADIUS_M : radiusMeters;
        return Math.min(KakaoLocalProperties.MAX_RADIUS_M, Math.max(1, radius));
    }

    private static SimpleClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(10));
        return factory;
    }

    private static String text(JsonNode node, String field) {
        JsonNode value = node.path(field);
        if (value.isMissingNode() || value.isNull()) {
            return null;
        }
        String s = value.asText().trim();
        return s.isEmpty() ? null : s;
    }

    private static Double parseDouble(String s) {
        if (s == null) {
            return null;
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
