package com.tourism.itda.planner.discovery;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.distance.DistanceCalculator;
import com.tourism.itda.global.kakao.KakaoLocalClient;
import com.tourism.itda.global.kakao.KakaoPlace;
import com.tourism.itda.global.tourapi.TourApiCategory;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiPlace;
import com.tourism.itda.place.entity.PlaceSource;
import com.tourism.itda.planner.route.LlmProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 작품 한 편의 줄거리를 Claude 에게 주고 관련 장소를 추출한 뒤, TourAPI(1순위)·카카오(보완)로 실재·좌표를 검증한다.
 *
 * <p>기존 {@code content_person}(작품→실존인물) 연결로는 근현대 작품(예: 말모이, 화려한 휴가)에
 * 연관 인물이 인물 DB에 없어 명소를 하나도 못 찾는 문제가 있었다. 이 서비스는 인물 매칭을 거치지 않고
 * 줄거리에서 바로 장소를 뽑아낸다.
 *
 * <p>역할 분담은 {@code SpotCurator} 와 같은 원칙을 따른다.
 * <ul>
 *   <li><b>LLM</b> — 줄거리를 읽고 "이 작품과 관련된 실제 장소가 어디인가"를 추정한다.
 *       이 판단은 코드로 할 수 없다.</li>
 *   <li><b>코드</b>(이 클래스의 검증 로직) — LLM 이 지목한 이름이 실재하는가, 관광지로 볼 수 있는가,
 *       국내 좌표인가. LLM 은 장소명을 지어내거나 좌표를 추정할 수 있으므로 반드시 외부 API 로 재확인한다.</li>
 * </ul>
 *
 * <p>이 프로젝트는 한국관광공사 TourAPI 활용이 전제이므로, 검증은 <b>TourAPI 를 1순위</b>로 시도한다
 * (관광지 contentTypeId=12 → 결과 없으면 문화시설 14 → 전체). <b>TourAPI 결과 중 검증(이름·지역)을
 * 통과한 것이 하나도 없을 때</b> 카카오 로컬 API 로 보완한다 — "TourAPI 가 0건일 때만" 이 아니다.
 * 실제로 "이화장"(이승만 사저) 검색은 TourAPI 가 "한국자이화장품"을 돌려주는데, 이건 결과가
 * 있긴 하지만 이름 검증에서 탈락한다. 여기서 폴백을 "0건 아님"으로 멈춰 버리면 카카오에 실제로
 * 등록된 "이화장(서울 종로구 이화동 2-1)"을 영영 못 찾는다. 그래서 "결과 유무"가 아니라
 * "검증 통과 유무"를 폴백 조건으로 삼는다.
 * TourAPI {@code searchKeyword2} 는 제목만 검색하므로, Claude 에게 "장소 이름 그대로" 뽑으라고 지시하는 것이
 * 이 순서에서 특히 중요하다.
 *
 * <p>Claude 를 못 쓰는 상황(설정 없음, API 오류, 타임아웃)에서는 예외를 던지지 않고
 * 빈 리스트를 돌려준다 — 이 서비스가 실패해도 배치 전체가 멈추면 안 되기 때문이다.
 */
@Slf4j
@Service
public class AnchorDiscoveryService {

    /** Claude 에게 요청하는 최대 후보 수. 무리하게 채우게 하지 않기 위한 상한이다. */
    private static final int MAX_CANDIDATES = 4;

    /** 국내 좌표 범위 방어 — 카카오가 잘못된 결과(해외 동명 장소 등)를 줄 때를 걸러낸다. */
    private static final double MIN_LAT = 33.0;
    private static final double MAX_LAT = 39.0;
    private static final double MIN_LNG = 124.0;
    private static final double MAX_LNG = 132.0;

    /** 확신도가 이 값 미만이면(추측성) 채택하지 않는다. */
    private static final int MIN_CONFIDENCE = 3;

    /** 이미 채택한 장소와 이 거리 이내면 같은 장소로 본다. */
    private static final long DUPLICATE_RADIUS_METERS = 250L;

    /** 관광지로 인정하는 카카오 카테고리 조각. */
    private static final String[] TOURISM_CATEGORY_FRAGMENTS = {
            "관광,명소", "문화시설", "공원", "절,사찰", "문화유적", "체험마을", "박물관", "기념관"
    };

    /**
     * TourAPI 를 시도할 contentTypeId 순서 — 관광지(12) → 문화시설(14) → 전체(null).
     * 원래 12/14 만 봤는데, 이 둘로도 안 잡히는 장소(사적지·기타 시설)가 있어 마지막에
     * 타입 제한 없는 전체 검색을 한 번 더 추가했다.
     */
    private static final String[] TOUR_CONTENT_TYPE_FALLBACK = {
            TourApiCategory.CONTENT_TYPE_ATTRACTION, TourApiCategory.CONTENT_TYPE_CULTURAL, null
    };

    /**
     * 관광API 주소는 정식 명칭("경상남도")을 쓰는데 LLM 은 약칭("경남")으로 region 을 적는다.
     * 그대로 문자열 포함 비교를 하면 항상 어긋나므로 약칭 ↔ 정식 명칭 별칭을 둔다.
     * 서울·인천처럼 약칭과 정식 명칭이 같은 지역은 목록에 없고, {@link #regionMatches} 에서
     * 이 맵에 없으면 지역명 자체를 그대로 쓴다.
     */
    private static final Map<String, List<String>> REGION_ALIASES = Map.ofEntries(
            Map.entry("충남", List.of("충남", "충청남도")),
            Map.entry("충북", List.of("충북", "충청북도")),
            Map.entry("전남", List.of("전남", "전라남도")),
            Map.entry("전북", List.of("전북", "전라북도")),
            Map.entry("경남", List.of("경남", "경상남도")),
            Map.entry("경북", List.of("경북", "경상북도"))
    );

    private static final String SYSTEM_PROMPT = """
            너는 한국 역사 영화·드라마와 관련된 실제 관광지를 찾아내는 조사원이다.
            작품의 제목·개봉연도·줄거리가 주어지면, 그 작품과 실제로 관련된 국내 장소를 찾아라.

            장소는 다음 세 가지 관계 중 하나여야 한다.
            - HISTORICAL: 작품이 다루는 실제 역사적 사건·인물의 현장이나 유적
            - FILMING: 실제로 이 작품을 촬영한 장소 (확실한 근거가 있을 때만. 아무 데나 촬영지라고 부르지 마라)
            - EXHIBIT: 작품이나 관련 인물을 기리는 전시관·기념관

            반드시 지켜야 할 원칙:
            - "시대가 같다" 또는 "등장인물 이름이 같다"는 이유만으로 관련 장소라고 판단하지 마라.
              그 장소가 실제로 그 사건·인물·촬영과 구체적으로 연결된다는 근거가 있어야 한다.
            - 확신이 서지 않으면 빈 목록을 돌려주는 것이 낫다. 개수를 채우려고 억지로 끼워 넣지 마라.
            - 숙박시설, 쇼핑몰, 음식점, 카페, "여행코스" 자체는 절대 장소로 제시하지 마라.
            - 장소의 좌표나 거리를 추정하지 마라. 실재 여부와 좌표 검증은 별도 시스템(관광공사 TourAPI·카카오 지도)이 한다.
              너는 정확한 장소명만 알려주면 된다.
            - 촬영지가 아닌 곳을 FILMING 이라고 부르지 마라. 확실하지 않으면 HISTORICAL 이나 EXHIBIT 을 쓰거나 아예 제시하지 마라.

            searchKeyword 는 관광 정보 검색에 그대로 입력했을 때 그 장소가 나올 정확한 명칭(지도에 실제 등록된 이름)으로 써라.
            region 은 그 장소가 실제로 있는 시/도를 적어라. 확실하지 않으면 빈 문자열로 두어라.
            """;

    /** TourAPI 검색에서 한 번에 받아올 후보 수. 1순위 결과 중 첫 통과분만 쓰므로 넉넉히만 잡는다. */
    private static final int TOUR_API_SEARCH_LIMIT = 5;

    private final LlmProperties properties;
    private final KakaoLocalClient kakaoLocalClient;
    private final TourApiClient tourApiClient;
    private final DistanceCalculator distanceCalculator;
    private final AnthropicClient client;

    public AnchorDiscoveryService(LlmProperties properties,
                                  KakaoLocalClient kakaoLocalClient,
                                  TourApiClient tourApiClient,
                                  DistanceCalculator distanceCalculator) {
        this.properties = properties;
        this.kakaoLocalClient = kakaoLocalClient;
        this.tourApiClient = tourApiClient;
        this.distanceCalculator = distanceCalculator;
        this.client = createClient(properties);
    }

    /**
     * 작품 하나에 대해 검증까지 마친 관련 명소 목록을 돌려준다.
     * Claude 를 못 쓰거나 실패하면 빈 리스트를 돌려준다 — 예외를 던지지 않는다.
     */
    public List<DiscoveredAnchor> discover(Content content) {
        if (client == null) {
            return List.of();
        }

        DiscoveryResult result;
        try {
            result = ask(content);
        } catch (Exception e) {
            log.warn("Claude 장소 발굴 실패 — 빈 목록으로 처리합니다: {} ({})", content.getTitle(), e.toString());
            return List.of();
        }

        if (result == null || result.places() == null || result.places().isEmpty()) {
            return List.of();
        }

        List<DiscoveredAnchor> accepted = new ArrayList<>();
        for (DiscoveredPlace candidate : result.places()) {
            if (accepted.size() >= MAX_CANDIDATES) {
                break;
            }
            DiscoveredAnchor anchor = verify(candidate, accepted);
            if (anchor != null) {
                accepted.add(anchor);
            }
        }
        return accepted;
    }

    private DiscoveryResult ask(Content content) {
        StructuredMessageCreateParams<DiscoveryResult> params = MessageCreateParams.builder()
                .model(properties.model())
                .maxTokens(properties.maxTokens())
                .system(SYSTEM_PROMPT)
                .outputConfig(DiscoveryResult.class)
                .addUserMessage(userPrompt(content))
                .build();

        return client.messages().create(params).content().stream()
                .flatMap(block -> block.text().stream())
                .map(block -> block.text())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Claude 응답에 구조화 결과가 없습니다."));
    }

    private String userPrompt(Content content) {
        String plot = content.getOverview();
        if (plot == null || plot.isBlank()) {
            plot = content.getSummary();
        }
        if (plot == null || plot.isBlank()) {
            plot = "(줄거리 정보 없음)";
        }

        StringBuilder prompt = new StringBuilder();
        prompt.append("제목: ").append(content.getTitle()).append('\n');
        if (content.getReleaseYear() != null) {
            prompt.append("개봉/방영 연도: ").append(content.getReleaseYear()).append('\n');
        }
        prompt.append("줄거리:\n").append(plot).append('\n');
        prompt.append("\n이 작품과 실제로 관련된 국내 장소를 최대 ")
                .append(MAX_CANDIDATES)
                .append("곳까지 찾아라. 확신이 없으면 빈 목록을 돌려줘도 된다.");
        return prompt.toString();
    }

    /**
     * Claude 후보 하나를 검증한다. TourAPI(표기 변형 x 관광지→문화시설→전체) 를 먼저 시도하고,
     * <b>검증을 통과한 결과가 하나도 없을 때</b> 카카오 로컬 API 로 보완한다. 모두 실패하면 null.
     */
    private DiscoveredAnchor verify(DiscoveredPlace candidate, List<DiscoveredAnchor> alreadyAccepted) {
        if (candidate == null) {
            return null;
        }
        if (candidate.confidence() < MIN_CONFIDENCE) {
            return null;
        }
        if (candidate.searchKeyword() == null || candidate.searchKeyword().isBlank()) {
            return null;
        }

        Optional<DiscoveredAnchor> anchor = verifyViaTourApi(candidate, alreadyAccepted);
        if (anchor.isPresent()) {
            return anchor.get();
        }
        return verifyViaKakao(candidate, alreadyAccepted).orElse(null);
    }

    /**
     * 표기 변형(가운뎃점/공백) x contentTypeId(관광지→문화시설→전체) 조합마다 검색하고,
     * 그 결과에 {@link #pickValid} 를 즉시 적용한다. 통과하는 결과가 나오면 그 순간 멈춘다.
     *
     * <p>예전에는 "검색 결과가 하나라도 나오면" 멈추고 그 결과들만 검증했다. 그러면
     * "이화장" 검색이 TourAPI 에서 "한국자이화장품"만 돌려주고 끝나 버려(결과는 있었으니),
     * 검증 실패 후에도 카카오로 안 넘어가는 문제가 있었다. 이제는 검증까지 통과해야
     * 멈추므로, 통과 못 한 조합은 계속 다음 변형/타입으로 넘어간다.
     */
    private Optional<DiscoveredAnchor> verifyViaTourApi(DiscoveredPlace candidate, List<DiscoveredAnchor> alreadyAccepted) {
        try {
            for (String variant : keywordVariants(candidate.searchKeyword())) {
                for (String contentTypeId : TOUR_CONTENT_TYPE_FALLBACK) {
                    List<TourApiPlace> results = tourApiClient.searchByTitle(variant, contentTypeId, TOUR_API_SEARCH_LIMIT);
                    if (results == null || results.isEmpty()) {
                        continue;
                    }
                    Optional<DiscoveredAnchor> picked = pickValid(
                            toHits(results), candidate, PlaceSource.TOUR_API, alreadyAccepted);
                    if (picked.isPresent()) {
                        return picked;
                    }
                }
            }
        } catch (Exception e) {
            log.warn("TourAPI 검색 실패 — 후보 건너뜀: {} ({})", candidate.searchKeyword(), e.toString());
            return Optional.empty();
        }
        // 결과가 아예 없었을 수도, 결과는 있었지만 전부 검증에서 탈락했을 수도 있다.
        // 어느 쪽이든 카카오로 넘어가야 한다 — "0건일 때만 폴백"은 후자를 놓친다.
        log.debug("TourAPI 검증 통과 없음 — 카카오 폴백 시도: {}", candidate.searchKeyword());
        return Optional.empty();
    }

    /** 원본 → '.'을 '·'로 → 공백 제거 → '·'을 공백으로 → '.'을 공백으로. 중복은 제거한다. */
    private static List<String> keywordVariants(String keyword) {
        Set<String> variants = new LinkedHashSet<>();
        variants.add(keyword);
        variants.add(keyword.replace(".", "·"));
        variants.add(keyword.replace(" ", ""));
        variants.add(keyword.replace("·", " "));
        variants.add(keyword.replace(".", " "));
        return new ArrayList<>(variants);
    }

    private Optional<DiscoveredAnchor> verifyViaKakao(DiscoveredPlace candidate, List<DiscoveredAnchor> alreadyAccepted) {
        List<KakaoPlace> results;
        try {
            results = kakaoLocalClient.searchKeyword(candidate.searchKeyword(), null, null);
        } catch (Exception e) {
            log.warn("카카오 검색 실패 — 후보 건너뜀: {} ({})", candidate.searchKeyword(), e.toString());
            return Optional.empty();
        }
        if (results == null || results.isEmpty()) {
            log.debug("카카오 결과 없음 — {}", candidate.searchKeyword());
            return Optional.empty();
        }
        return pickValid(toHitsFromKakao(results), candidate, PlaceSource.KAKAO, alreadyAccepted);
    }

    private boolean passesKakaoFilter(KakaoPlace place) {
        if (place.looksLikeSubFacility()) {
            return false;
        }
        if (!place.hasAnyCategory(TOURISM_CATEGORY_FRAGMENTS)) {
            return false;
        }
        return place.coord() != null && withinKoreanBounds(place.coord());
    }

    /** TourAPI·카카오 결과를 공통 검증 형태로 옮긴다. */
    private record PlaceHit(String externalId, String name, String category, String address, Coord coord) {
    }

    private static List<PlaceHit> toHits(List<TourApiPlace> places) {
        List<PlaceHit> hits = new ArrayList<>();
        for (TourApiPlace place : places) {
            if (place.contentId() == null) {
                continue;
            }
            hits.add(new PlaceHit(place.contentId(), place.title(), place.category(), place.address(), place.coord()));
        }
        return hits;
    }

    private List<PlaceHit> toHitsFromKakao(List<KakaoPlace> places) {
        List<PlaceHit> hits = new ArrayList<>();
        for (KakaoPlace place : places) {
            if (!passesKakaoFilter(place)) {
                continue;
            }
            hits.add(new PlaceHit(place.id(), place.name(), place.categoryName(), place.address(), place.coord()));
        }
        return hits;
    }

    /**
     * TourAPI 와 카카오에 공통으로 적용하는 검증. 국내 좌표 → 이미 채택한 장소와 중복(250m) →
     * 이름 일치({@link #nameMatches}) → 지역 일치({@link #regionMatches}) 순으로 걸러
     * 통과하는 첫 결과를 채택한다. 두 출처가 같은 검증을 공유해야 폴백해도 기준이 흔들리지 않는다.
     */
    private Optional<DiscoveredAnchor> pickValid(List<PlaceHit> hits, DiscoveredPlace candidate,
                                                  PlaceSource source, List<DiscoveredAnchor> alreadyAccepted) {
        for (PlaceHit hit : hits) {
            if (hit.externalId() == null || hit.coord() == null) {
                continue;
            }
            if (!withinKoreanBounds(hit.coord())) {
                continue;
            }
            if (!nameMatches(candidate.searchKeyword(), hit.name())) {
                log.debug("{} 이름 불일치로 거부: 검색어='{}' 결과='{}'", source, candidate.searchKeyword(), hit.name());
                continue;
            }
            if (!regionMatches(candidate.region(), hit.address())) {
                log.debug("{} 지역 불일치로 거부: 검색어='{}' 기대지역='{}' 결과주소='{}'",
                        source, candidate.searchKeyword(), candidate.region(), hit.address());
                continue;
            }
            if (isDuplicateExternalId(source, hit.externalId(), alreadyAccepted)
                    || isDuplicateByDistance(hit.coord(), alreadyAccepted)) {
                continue;
            }
            return Optional.of(new DiscoveredAnchor(
                    source,
                    hit.externalId(),
                    hit.name(),
                    hit.category(),
                    hit.address(),
                    hit.coord(),
                    candidate.relationType(),
                    candidate.reason(),
                    candidate.confidence()));
        }
        return Optional.empty();
    }

    /**
     * searchKeyword2 는 제목 부분일치라 엉뚱한 곳이 걸린다. 실제 오탐 사례:
     * "이화장"(이승만 사저) 검색 → "한국자이화장품"(강서구 화장품 가게)이 매칭,
     * "전쟁기념관"(서울 용산) 검색 → "박진전쟁기념관"(창녕)이 매칭.
     *
     * <p>공백·가운뎃점·마침표를 제거해 정규화한 뒤, 다음 셋 중 하나면 같은 장소로 인정한다.
     * <ul>
     *   <li>결과 이름이 검색어로 시작한다</li>
     *   <li>결과 이름이 검색어로 끝난다 — 관광API 는 "운현궁"을 "서울 운현궁"처럼
     *       지역명을 <b>앞에</b> 붙여 등록하는 경우가 흔해서, 시작 조건만으로는
     *       "서울 운현궁"이 "운현궁" 검색에 걸리지 못하고 정상 결과를 놓친다.</li>
     *   <li>검색어가 여는 괄호 바로 뒤에 온다</li>
     * </ul>
     * "자유공원(인천)", "남한산성 수어장대", "창덕궁 낙선재", "서울 운현궁", "제주4·3평화공원"은 통과하고,
     * "한국자이화장품"(검색어 "이화장"이 이름 한가운데 끼어 있어 시작도 끝도 아님)은 걸러진다.
     *
     * <p>다만 이 검사만으로는 "박진전쟁기념관"(검색어 "전쟁기념관")처럼 접미 조건에 걸려
     * 통과해 버리는 경우가 있다 — 그래서 이름 검사 뒤에 반드시 {@link #regionMatches} 를
     * 함께 적용해야 한다(서울 용산을 기대했는데 결과 주소가 경상남도 창녕이면 거기서 걸러진다).
     */
    private static boolean nameMatches(String keyword, String name) {
        String normalizedKeyword = normalizeForNameMatch(keyword);
        String normalizedName = normalizeForNameMatch(name);
        if (normalizedKeyword.isEmpty() || normalizedName.isEmpty()) {
            return false;
        }
        return normalizedName.startsWith(normalizedKeyword)
                || normalizedName.endsWith(normalizedKeyword)
                || normalizedName.contains("(" + normalizedKeyword);
    }

    private static String normalizeForNameMatch(String s) {
        if (s == null) {
            return "";
        }
        return s.replace(" ", "").replace("·", "").replace(".", "");
    }

    /**
     * 동명 장소를 지역으로 거른다. 예: "자유공원" 검색 시 관광API 가 인천이 아니라
     * 안양시 자유공원을 1순위로 돌려주는 경우가 있다. LLM 이 매긴 region 이 결과 주소에
     * 들어있는지 확인한다. region 이 비어 있으면(확신 없음) 검사를 건너뛴다.
     * 약칭("경남")과 정식 명칭("경상남도")을 모두 허용한다 — {@link #REGION_ALIASES} 참고.
     */
    private static boolean regionMatches(String expectedRegion, String address) {
        if (expectedRegion == null || expectedRegion.isBlank()) {
            return true;
        }
        String addr = address == null ? "" : address;
        List<String> aliases = REGION_ALIASES.getOrDefault(expectedRegion, List.of(expectedRegion));
        for (String alias : aliases) {
            if (addr.contains(alias)) {
                return true;
            }
        }
        return false;
    }

    private static boolean withinKoreanBounds(Coord coord) {
        double lat = coord.latitude();
        double lng = coord.longitude();
        return lat >= MIN_LAT && lat <= MAX_LAT && lng >= MIN_LNG && lng <= MAX_LNG;
    }

    private static boolean isDuplicateExternalId(PlaceSource source, String externalId,
                                                 List<DiscoveredAnchor> alreadyAccepted) {
        if (externalId == null) {
            return false;
        }
        for (DiscoveredAnchor accepted : alreadyAccepted) {
            if (accepted.source() == source && externalId.equals(accepted.externalId())) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateByDistance(Coord coord, List<DiscoveredAnchor> alreadyAccepted) {
        if (coord == null) {
            return false;
        }
        for (DiscoveredAnchor accepted : alreadyAccepted) {
            if (accepted.coord() == null) {
                continue;
            }
            long distance = distanceCalculator.distanceMeters(
                    accepted.coord().latitude(), accepted.coord().longitude(),
                    coord.latitude(), coord.longitude());
            if (distance <= DUPLICATE_RADIUS_METERS) {
                return true;
            }
        }
        return false;
    }

    private static AnthropicClient createClient(LlmProperties properties) {
        if (!properties.isUsable()) {
            log.info("Claude 장소 발굴이 비활성 상태입니다 (itda.llm.api-key 미설정 또는 enabled=false). "
                    + "작품 관련 명소 발굴은 건너뜁니다.");
            return null;
        }
        try {
            return AnthropicOkHttpClient.builder()
                    .apiKey(properties.apiKey())
                    .timeout(Duration.ofSeconds(30))
                    .build();
        } catch (Exception e) {
            log.warn("Claude 클라이언트 생성 실패 — 장소 발굴 없이 동작합니다: {}", e.toString());
            return null;
        }
    }
}
