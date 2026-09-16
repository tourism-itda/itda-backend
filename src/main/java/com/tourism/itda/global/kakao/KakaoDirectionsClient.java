package com.tourism.itda.global.kakao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.global.distance.Coord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.Optional;

/**
 * 카카오모빌리티 자동차 길찾기.
 *
 * <p>직선거리로는 하루 코스가 되는지 알 수 없어서 쓴다. 실측해 보면 도로거리가 직선의
 * 1.3~1.9배까지 벌어진다. 직선 25km 를 차량 47km 로 안내하는 조합이 실제로 나온다.
 *
 * <p>로컬 API 와 호스트가 다르다({@code apis-navi.kakaomobility.com}). 인증 키는
 * 같은 앱의 REST 키를 쓰므로 {@link KakaoLocalProperties} 를 그대로 재사용한다.
 *
 * <p>자동차는 <b>방향별로 경로가 다르다</b>. A→B 와 B→A 를 같은 값으로 취급하면 안 되므로
 * 이 클래스는 한 방향만 계산하고, 순서 비교는 호출부가 방향별로 따로 질의한다.
 *
 * <p>일간 무료 쿼터가 10,000건이다. 후보 조합마다 전부 부르면 금방 소진되므로
 * 호출부에서 직선거리로 후보를 좁힌 뒤 최종 조합만 검증해야 한다.
 */
@Slf4j
@Component
public class KakaoDirectionsClient {

    private static final String DIRECTIONS_URL = "https://apis-navi.kakaomobility.com/v1/directions";

    private final KakaoLocalProperties properties;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public KakaoDirectionsClient(KakaoLocalProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder()
                .requestFactory(requestFactory())
                .build();
    }

    /** 한 구간의 실제 도로 거리·소요시간. 실패하면 비어 있다 — 호출부가 직선 추정으로 폴백한다. */
    public Optional<RoadLeg> car(Coord origin, Coord destination) {
        if (!properties.isConfigured()) {
            log.warn("카카오 REST 키가 없어 길찾기를 건너뜁니다. itda.kakao-local.rest-api-key 를 확인하세요.");
            return Optional.empty();
        }

        // 카카오는 "경도,위도" 순서다. Coord 는 (위도, 경도) 순이라 뒤집어 넣는다.
        String url = UriComponentsBuilder.fromUriString(DIRECTIONS_URL)
                .queryParam("origin", origin.longitude() + "," + origin.latitude())
                .queryParam("destination", destination.longitude() + "," + destination.latitude())
                .queryParam("summary", "true")
                .build(true)
                .toUriString();

        try {
            String body = restClient.get()
                    .uri(url)
                    .header("Authorization", "KakaoAK " + properties.restApiKey())
                    .retrieve()
                    .body(String.class);

            return parse(body);

        } catch (Exception e) {
            log.warn("카카오 길찾기 호출 실패 — 직선 추정으로 폴백합니다: {}", e.toString());
            return Optional.empty();
        }
    }

    /**
     * 경로를 못 찾아도 HTTP 200 이고 {@code result_code} 로만 알 수 있다.
     * 0 이 아니면 섬·군사지역처럼 차로 갈 수 없는 조합이므로 빈 값으로 돌려준다.
     */
    private Optional<RoadLeg> parse(String body) throws Exception {
        if (body == null || body.isBlank()) {
            return Optional.empty();
        }
        JsonNode routes = objectMapper.readTree(body).path("routes");
        if (!routes.isArray() || routes.isEmpty()) {
            return Optional.empty();
        }

        JsonNode route = routes.get(0);
        int resultCode = route.path("result_code").asInt(-1);
        if (resultCode != 0) {
            log.info("카카오 길찾기가 경로를 찾지 못했습니다. result_code={} msg={}",
                    resultCode, route.path("result_msg").asText(""));
            return Optional.empty();
        }

        JsonNode summary = route.path("summary");
        if (summary.isMissingNode()) {
            return Optional.empty();
        }
        return Optional.of(new RoadLeg(
                summary.path("distance").asLong(0),
                summary.path("duration").asLong(0)));
    }

    private static SimpleClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(5));
        factory.setReadTimeout(Duration.ofSeconds(10));
        return factory;
    }

    /** 실제 도로 기준 한 구간. 직선거리와 구분하려고 이름에 Road 를 붙였다. */
    public record RoadLeg(long distanceMeters, long durationSeconds) {

        public long durationMinutes() {
            return durationSeconds / 60;
        }
    }
}
