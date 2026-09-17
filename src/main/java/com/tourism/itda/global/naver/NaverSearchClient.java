package com.tourism.itda.global.naver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * 네이버 검색 API(이미지) 호출기.
 *
 * <p>카카오 로컬 API 에 사진 필드가 없어서 쓰는 보완재다. TourAPI 에 있는 장소는
 * {@code firstimage} 가 1순위이고, 여기까지 내려오는 것은 TourAPI 가 모르는 가게들이다.
 *
 * <p><b>이 API 는 "그 가게의 실사진"을 보장하지 않는다.</b> 블로그·카페에 올라온 이미지를
 * 검색어 유사도로 돌려줄 뿐이라, 상호가 흔하면 엉뚱한 사진이 섞인다. 그래서 호출부
 * ({@link com.tourism.itda.place.service.NaverPlaceImageFinder})가 제목에 상호가
 * 들어간 결과만 채택하는 식으로 한 번 더 거른다.
 *
 * <p>사진을 못 찾는 것은 정상 상황이므로 예외를 던지지 않고 빈 리스트를 돌려준다.
 */
@Slf4j
@Component
public class NaverSearchClient {

    private static final String IMAGE_SEARCH_URL = "https://openapi.naver.com/v1/search/image";

    private final NaverSearchProperties properties;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public NaverSearchClient(NaverSearchProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder()
                .requestFactory(requestFactory())
                .build();
    }

    public boolean isConfigured() {
        return properties.isConfigured();
    }

    /**
     * 이미지를 유사도순으로 검색한다.
     *
     * @return 키 미설정·호출 실패·결과 없음 모두 빈 리스트
     */
    public List<NaverImage> searchImages(String query) {
        if (!properties.isConfigured()) {
            log.warn("네이버 검색 API 키가 설정되지 않아 이미지 조회를 건너뜁니다. "
                    + "itda.naver-search.client-id / client-secret 을 확인하세요.");
            return List.of();
        }
        if (query == null || query.isBlank()) {
            return List.of();
        }

        URI uri = UriComponentsBuilder.fromUriString(IMAGE_SEARCH_URL)
                .queryParam("query", query)
                .queryParam("display", properties.display())
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .build()
                .encode()
                .toUri();

        try {
            String body = restClient.get()
                    .uri(uri)
                    .header("X-Naver-Client-Id", properties.clientId())
                    .header("X-Naver-Client-Secret", properties.clientSecret())
                    .retrieve()
                    .body(String.class);

            if (body == null || body.isBlank()) {
                return List.of();
            }

            JsonNode items = objectMapper.readTree(body).path("items");
            if (!items.isArray()) {
                return List.of();
            }

            List<NaverImage> result = new ArrayList<>();
            for (JsonNode item : items) {
                String link = text(item, "link");
                if (link == null) {
                    continue;
                }
                result.add(new NaverImage(
                        stripHtml(text(item, "title")),
                        link,
                        text(item, "thumbnail"),
                        parseInt(text(item, "sizewidth")),
                        parseInt(text(item, "sizeheight"))));
            }
            return result;

        } catch (Exception e) {
            log.warn("네이버 이미지 검색 실패 (query={}): {}", query, e.toString());
            return List.of();
        }
    }

    // ── 내부 ────────────────────────────────────────────────────────────────

    /** 네이버는 검색어와 일치하는 부분을 {@code <b>} 로 감싸서 준다. */
    private static String stripHtml(String s) {
        if (s == null) {
            return null;
        }
        String cleaned = s.replaceAll("<[^>]*>", " ")
                .replace("&nbsp;", " ")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replaceAll("\\s+", " ")
                .trim();
        return cleaned.isEmpty() ? null : cleaned;
    }

    private static SimpleClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(5));
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

    private static int parseInt(String s) {
        if (s == null) {
            return 0;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
