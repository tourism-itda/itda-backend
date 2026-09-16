package com.tourism.itda.global.tourapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.planner.route.SearchArea;
import com.tourism.itda.place.entity.PlaceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 한국관광공사 TourAPI(KorService2) 호출기.
 *
 * <p>관광API 응답에는 다루기 까다로운 구석이 몇 개 있어 JsonNode 로 방어적으로 읽는다:
 * <ul>
 *   <li>검색 결과가 0건이면 {@code body.items} 가 객체가 아니라 <b>빈 문자열</b>로 온다</li>
 *   <li>실패해도 HTTP 200 이고 {@code header.resultCode} 로만 알 수 있다</li>
 *   <li>{@code mapx} 가 경도, {@code mapy} 가 위도다 (x/y 순서가 위경도와 반대)</li>
 * </ul>
 *
 * <p>후보를 못 찾는 것은 정상 상황이므로 예외를 던지지 않고 빈 리스트를 돌려준다.
 * 지방·신도시에서는 반경 안에 등록 업소가 아예 없을 수 있다.
 */
@Slf4j
@Component
public class TourApiClient {

    private static final String LOCATION_BASED_LIST = "locationBasedList2";
    private static final String DETAIL_INTRO = "detailIntro2";
    private static final String DETAIL_COMMON = "detailCommon2";
    private static final String DETAIL_INFO = "detailInfo2";
    private static final String SEARCH_KEYWORD = "searchKeyword2";
    private static final String LDONG_CODE = "ldongCode2";
    private static final String SEARCH_FESTIVAL = "searchFestival2";

    /** 여행코스. detailInfo2 로 코스에 속한 장소 목록을 꺼낼 수 있다. */
    public static final String CONTENT_TYPE_COURSE = "25";

    private final TourApiProperties properties;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;
    private final String serviceKey;

    public TourApiClient(TourApiProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder()
                .requestFactory(requestFactory())
                .build();
        this.serviceKey = decodeIfEncoded(properties.apiKey());
    }

    /**
     * 검색 원 안의 장소를 슬롯 타입에 맞게 조회한다.
     *
     * @return 호출 실패·결과 없음·키 미설정 모두 빈 리스트
     */
    public List<TourApiPlace> findNearby(SearchArea area, PlaceType type) {
        if (!properties.isConfigured()) {
            log.warn("관광API 키가 설정되지 않아 후보 검색을 건너뜁니다. itda.tour-api.api-key 를 확인하세요.");
            return List.of();
        }

        Map<String, String> params = new java.util.LinkedHashMap<>(TourApiCategory.queryParamsFor(type));
        params.put("mapX", String.valueOf(area.center().longitude()));
        params.put("mapY", String.valueOf(area.center().latitude()));
        params.put("radius", String.valueOf(area.radiusMeters()));
        params.put("arrange", "E");   // 거리순 — 최종 정렬은 우회거리로 다시 한다
        params.put("numOfRows", String.valueOf(properties.numOfRows()));
        params.put("pageNo", "1");

        JsonNode items = callForItems(LOCATION_BASED_LIST, params);
        List<TourApiPlace> result = new ArrayList<>();
        for (JsonNode item : items) {
            TourApiPlace place = toPlace(item, type);
            if (place != null) {
                result.add(place);
            }
        }
        return result;
    }

    /**
     * 검색 원 안의 장소를 지정한 contentTypeId 로 바로 조회한다.
     *
     * <p>{@link #findNearby} 는 {@link PlaceType} 하나에 관광API contentTypeId 하나만 묶여 있어
     * 문화시설(14)처럼 관광지(12) 외의 타입을 보강할 때는 쓸 수 없다. 그래서 contentTypeId 를
     * 직접 받는 경로를 따로 둔다. 결과는 전부 {@link PlaceType#SPOT} 으로 취급한다
     * (일반 관광명소 보강 용도로만 쓰기 때문).
     *
     * @return 호출 실패·결과 없음·키 미설정 모두 빈 리스트
     */
    public List<TourApiPlace> findNearbyByContentType(SearchArea area, String contentTypeId) {
        if (!properties.isConfigured()) {
            log.warn("관광API 키가 설정되지 않아 후보 검색을 건너뜁니다. itda.tour-api.api-key 를 확인하세요.");
            return List.of();
        }

        Map<String, String> params = new java.util.LinkedHashMap<>();
        params.put("contentTypeId", contentTypeId);
        params.put("mapX", String.valueOf(area.center().longitude()));
        params.put("mapY", String.valueOf(area.center().latitude()));
        params.put("radius", String.valueOf(area.radiusMeters()));
        params.put("arrange", "E");
        params.put("numOfRows", String.valueOf(properties.numOfRows()));
        params.put("pageNo", "1");

        JsonNode items = callForItems(LOCATION_BASED_LIST, params);
        List<TourApiPlace> result = new ArrayList<>();
        for (JsonNode item : items) {
            TourApiPlace place = toPlace(item, PlaceType.SPOT);
            if (place != null) {
                result.add(place);
            }
        }
        return result;
    }

    /**
     * contentId 로 장소 1건을 조회한다.
     *
     * <p>사용자가 후보를 확정할 때 쓴다. 클라이언트가 보낸 이름·좌표를 그대로 저장하면
     * 임의의 place 행을 밀어 넣을 수 있으므로, 서버가 관광API 에서 다시 받아 저장한다.
     *
     * <p><b>KorService2 의 detailCommon2 는 {@code contentId} 외의 파라미터를 전부 거부한다.</b>
     * KorService1 시절의 {@code defaultYN/overviewYN/addrinfoYN/mapinfoYN/firstImageYN} 은 물론
     * {@code contentTypeId} 까지 {@code INVALID_REQUEST_PARAMETER_ERROR} 가 난다.
     * overview·좌표·주소·대표이미지는 파라미터 없이도 기본으로 내려온다.
     * (2026-08-26 실호출 확인. 이걸 붙여 보내는 동안 이 메서드는 항상 실패하고 있었다.)
     */
    public Optional<TourApiPlace> findDetail(String contentId, PlaceType type) {
        if (!properties.isConfigured() || contentId == null || contentId.isBlank()) {
            return Optional.empty();
        }

        JsonNode items = callForItems(DETAIL_COMMON, Map.of("contentId", contentId));

        for (JsonNode item : items) {
            Double lng = parseDouble(text(item, "mapx"));
            Double lat = parseDouble(text(item, "mapy"));
            if (lat == null || lng == null) {
                continue;
            }
            String cat3 = text(item, "cat3");

            return Optional.of(new TourApiPlace(
                    contentId,
                    resolvePlaceType(text(item, "contenttypeid"), cat3, type),
                    text(item, "title"),
                    cat3,
                    firstNonBlank(text(item, "addr1"), text(item, "addr2")),
                    firstNonBlank(text(item, "firstimage"), text(item, "firstimage2")),
                    new Coord(lat, lng),
                    null,
                    stripHtml(text(item, "overview"))));
        }
        return Optional.empty();
    }

    /**
     * 제목 부분일치로 장소를 찾는다. 콘텐츠-장소 매핑의 앵커 발견에 쓴다.
     *
     * <p>searchKeyword2 는 <b>제목만</b> 검색한다 (overview 는 검색 대상이 아니다).
     * 그래서 "작품명"·"인물명"·"지명"처럼 장소 이름에 실제로 박혀 있는 말로 물어야 한다.
     *
     * @param contentTypeId 관광API 콘텐츠 타입. null 이면 전체
     */
    public List<TourApiPlace> searchByTitle(String keyword, String contentTypeId, int limit) {
        if (!properties.isConfigured() || keyword == null || keyword.isBlank()) {
            return List.of();
        }

        Map<String, String> params = new java.util.LinkedHashMap<>();
        params.put("keyword", keyword);
        params.put("numOfRows", String.valueOf(limit));
        params.put("pageNo", "1");
        if (contentTypeId != null) {
            params.put("contentTypeId", contentTypeId);
        }

        List<TourApiPlace> result = new ArrayList<>();
        for (JsonNode item : callForItems(SEARCH_KEYWORD, params)) {
            String id = text(item, "contentid");
            if (id == null) {
                continue;
            }
            // 여행코스(25)·축제(15) 는 좌표가 비어 있는 경우가 흔하다. 앵커 후보로는
            // 이름만으로도 의미가 있으므로 좌표 없음을 이유로 버리지 않는다.
            Double lng = parseDouble(text(item, "mapx"));
            Double lat = parseDouble(text(item, "mapy"));
            String cat3 = text(item, "cat3");

            result.add(new TourApiPlace(
                    id,
                    resolvePlaceType(text(item, "contenttypeid"), cat3, PlaceType.SPOT),
                    text(item, "title"),
                    cat3,
                    firstNonBlank(text(item, "addr1"), text(item, "addr2")),
                    firstNonBlank(text(item, "firstimage"), text(item, "firstimage2")),
                    (lat == null || lng == null) ? null : new Coord(lat, lng),
                    null,
                    null));
        }
        return result;
    }

    /**
     * 여행코스(contentTypeId=25)에 속한 장소들을 순서대로 돌려준다.
     *
     * <p>관광공사가 이미 큐레이션해 둔 코스다. 예: "과거급제를 꿈꾸던 청년 이순신이 살던
     * 그곳에서 놀기" → 현충사 · 아산 외암리 민속마을 · 점심식사(목화반점) · 당림미술관 ...
     * {@code subcontentid} 가 실제 관광API contentId 라 좌표·영업시간까지 이어서 조회할 수 있다.
     *
     * @return 코스가 아니거나 조회 실패면 빈 리스트
     */
    public List<TourApiCourseStop> findCourseStops(String courseContentId) {
        if (!properties.isConfigured() || courseContentId == null || courseContentId.isBlank()) {
            return List.of();
        }

        JsonNode items = callForItems(DETAIL_INFO, Map.of(
                "contentId", courseContentId,
                "contentTypeId", CONTENT_TYPE_COURSE,
                "numOfRows", "50",
                "pageNo", "1"));

        List<TourApiCourseStop> stops = new ArrayList<>();
        for (JsonNode item : items) {
            String subContentId = text(item, "subcontentid");
            String name = text(item, "subname");
            if (subContentId == null || name == null) {
                continue;
            }
            stops.add(new TourApiCourseStop(
                    subContentId, name, stripHtml(text(item, "subdetailoverview"))));
        }
        return stops;
    }

    /**
     * 법정동 코드를 조회한다. 연관관광지(TarRlteTar)가 요구하는 {@code signguCd} 를 만들기 위한 것이다.
     *
     * @param regnCd null 이면 시도 목록(2자리 code), 시도코드를 주면 그 아래 시군구 목록(3자리 code)
     */
    public List<LdongCode> findLdongCodes(String regnCd) {
        if (!properties.isConfigured()) {
            return List.of();
        }

        Map<String, String> params = new java.util.LinkedHashMap<>();
        params.put("numOfRows", "100");
        params.put("pageNo", "1");
        if (regnCd != null) {
            params.put("lDongRegnCd", regnCd);
        }

        List<LdongCode> codes = new ArrayList<>();
        for (JsonNode item : callForItems(LDONG_CODE, params)) {
            String code = text(item, "code");
            String name = text(item, "name");
            if (code != null && name != null) {
                codes.add(new LdongCode(code, name));
            }
        }
        return codes;
    }

    /**
     * 관광API contentTypeId 를 우리 PlaceType 으로 옮긴다.
     *
     * <p>{@link TourApiCategory#toPlaceType} 은 12·39 외에는 예외를 던진다. 촬영지 앵커로
     * 문화시설(14)·축제(15)·여행코스(25)도 다루게 되면서 그 예외가 조회 자체를 깨뜨리므로,
     * 모르는 타입은 요청한 타입으로 되돌린다.
     */
    private static PlaceType resolvePlaceType(String contentTypeId, String cat3, PlaceType fallback) {
        if (contentTypeId == null) {
            return fallback;
        }
        try {
            return TourApiCategory.toPlaceType(contentTypeId, cat3);
        } catch (RuntimeException e) {
            return fallback;
        }
    }

    /**
     * 최종 선택된 장소 1건의 영업시간을 가져온다.
     * 실패하면 {@link TourApiIntro#empty()} — 영업시간을 못 채우는 것이 일정 생성을 막을 이유는 없다.
     */
    public TourApiIntro findIntro(String contentId, PlaceType type) {
        if (!properties.isConfigured() || contentId == null || contentId.isBlank()) {
            return TourApiIntro.empty();
        }

        String contentTypeId = (type == PlaceType.SPOT)
                ? TourApiCategory.CONTENT_TYPE_ATTRACTION
                : TourApiCategory.CONTENT_TYPE_FOOD;

        JsonNode items = callForItems(DETAIL_INTRO, Map.of(
                "contentId", contentId,
                "contentTypeId", contentTypeId));

        for (JsonNode item : items) {
            // 음식점은 opentimefood/restdatefood, 관광지는 usetime/restdate 로 필드명이 다르다.
            String hours = firstNonBlank(text(item, "opentimefood"), text(item, "usetime"));
            String rest = firstNonBlank(text(item, "restdatefood"), text(item, "restdate"));
            if (hours != null || rest != null) {
                return new TourApiIntro(hours, rest);
            }
        }
        return TourApiIntro.empty();
    }

    /**
     * 오늘(또는 지정한 날짜) 이후 시작하는 축제·공연·행사를 조회한다.
     *
     * <p>searchFestival2 는 날짜순 정렬 파라미터가 없어서, 최신순 정렬은 호출부에서 처리한다.
     *
     * @return 호출 실패·결과 없음·키 미설정 모두 빈 리스트
     */
    public List<TourApiFestival> findUpcomingFestivals(LocalDate from, int numOfRows) {
        if (!properties.isConfigured()) {
            log.warn("관광API 키가 설정되지 않아 행사 조회를 건너뜁니다. itda.tour-api.api-key 를 확인하세요.");
            return List.of();
        }

        Map<String, String> params = new java.util.LinkedHashMap<>();
        params.put("eventStartDate", from.format(DateTimeFormatter.BASIC_ISO_DATE));
        params.put("numOfRows", String.valueOf(numOfRows));
        params.put("pageNo", "1");

        JsonNode items = callForItems(SEARCH_FESTIVAL, params);
        List<TourApiFestival> result = new ArrayList<>();
        for (JsonNode item : items) {
            TourApiFestival festival = toFestival(item);
            if (festival != null) {
                result.add(festival);
            }
        }
        return result;
    }

    // ── 내부 ────────────────────────────────────────────────────────────────

    private JsonNode callForItems(String endpoint, Map<String, String> params) {
        try {
            String body = restClient.get()
                    .uri(buildUri(endpoint, params))
                    .retrieve()
                    .body(String.class);

            if (body == null || body.isBlank()) {
                return objectMapper.createArrayNode();
            }
            if (!body.trim().startsWith("{")) {
                // 키가 잘못됐거나 트래픽 초과일 때 관광API 는 XML 에러를 돌려준다.
                log.warn("관광API {} 가 JSON 이 아닌 응답을 반환했습니다: {}", endpoint, abbreviate(body));
                return objectMapper.createArrayNode();
            }

            JsonNode root = objectMapper.readTree(body);
            JsonNode header = root.path("response").path("header");
            String resultCode = header.path("resultCode").asText("");
            if (!"0000".equals(resultCode) && !"00".equals(resultCode)) {
                log.warn("관광API {} 실패 — resultCode={} resultMsg={}",
                        endpoint, resultCode, header.path("resultMsg").asText(""));
                return objectMapper.createArrayNode();
            }

            // 결과가 0건이면 items 가 빈 문자열로 온다 → path().path() 가 그냥 missing 이 되므로 안전.
            JsonNode item = root.path("response").path("body").path("items").path("item");
            if (item.isArray()) {
                return item;
            }
            if (item.isObject()) {
                return objectMapper.createArrayNode().add(item);
            }
            return objectMapper.createArrayNode();

        } catch (Exception e) {
            log.warn("관광API {} 호출 실패: {}", endpoint, e.toString());
            return objectMapper.createArrayNode();
        }
    }

    private URI buildUri(String endpoint, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(properties.baseUrl() + endpoint)
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "itda")
                .queryParam("_type", "json");
        params.forEach(builder::queryParam);
        return builder.build().encode().toUri();
    }

    private TourApiPlace toPlace(JsonNode item, PlaceType requested) {
        String cat3 = text(item, "cat3");
        if (!TourApiCategory.matches(requested, cat3)) {
            // 식당을 요청했는데 카페가 딸려온 경우 등.
            return null;
        }

        Double lng = parseDouble(text(item, "mapx"));
        Double lat = parseDouble(text(item, "mapy"));
        if (lat == null || lng == null) {
            return null;   // 좌표 없는 항목은 동선 계산에 쓸 수 없다.
        }

        String contentId = text(item, "contentid");
        if (contentId == null) {
            return null;
        }

        String contentTypeId = text(item, "contenttypeid");
        PlaceType type = (contentTypeId == null)
                ? requested
                : TourApiCategory.toPlaceType(contentTypeId, cat3);

        Double dist = parseDouble(text(item, "dist"));

        return TourApiPlace.ofListItem(
                contentId,
                type,
                text(item, "title"),
                cat3,
                firstNonBlank(text(item, "addr1"), text(item, "addr2")),
                firstNonBlank(text(item, "firstimage"), text(item, "firstimage2")),
                new Coord(lat, lng),
                dist == null ? null : Math.round(dist));
    }

    private TourApiFestival toFestival(JsonNode item) {
        String contentId = text(item, "contentid");
        String title = text(item, "title");
        if (contentId == null || title == null) {
            return null;
        }

        return new TourApiFestival(
                contentId,
                title,
                firstNonBlank(text(item, "addr1"), text(item, "addr2")),
                firstNonBlank(text(item, "firstimage"), text(item, "firstimage2")),
                parseEventDate(text(item, "eventstartdate")),
                parseEventDate(text(item, "eventenddate")));
    }

    private static LocalDate parseEventDate(String s) {
        if (s == null) {
            return null;
        }
        try {
            return LocalDate.parse(s, DateTimeFormatter.BASIC_ISO_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /** overview 에는 &lt;br&gt; 같은 태그가 섞여 온다. */
    private static String stripHtml(String s) {
        if (s == null) {
            return null;
        }
        String cleaned = s.replaceAll("<[^>]*>", " ")
                .replace("&nbsp;", " ")
                .replaceAll("\\s+", " ")
                .trim();
        return cleaned.isEmpty() ? null : cleaned;
    }

    /**
     * 공공데이터포털은 서비스키를 인코딩/디코딩 두 형태로 보여준다.
     * 어느 쪽을 설정에 넣었든 정확히 한 번만 인코딩되도록 맞춘다.
     */
    private static String decodeIfEncoded(String key) {
        if (key == null || key.isBlank()) {
            return "";
        }
        return key.contains("%") ? URLDecoder.decode(key, StandardCharsets.UTF_8) : key;
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

    private static String firstNonBlank(String a, String b) {
        return (a != null) ? a : b;
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

    private static String abbreviate(String s) {
        return s.length() <= 200 ? s : s.substring(0, 200) + "...";
    }
}
