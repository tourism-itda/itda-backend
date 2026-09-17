package com.tourism.itda.place.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.global.client.PublicDataClient;
import com.tourism.itda.global.exception.InvalidRequestException;
import com.tourism.itda.place.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    /**
     * 연관 관광지 기준 연월 기본값.
     *
     * <p>202412~202606 구간을 찍어 보면 어느 달을 넣어도 같은 결과가 나온다 — 데이터가 월별로
     * 크게 흔들리지 않는다. 최신 달을 계산해 넣으면 아직 집계 안 된 달을 물어 0건이 될 수 있어
     * 확인된 값으로 고정한다.
     */
    private static final String DEFAULT_BASE_YM = "202503";

    private final PublicDataClient publicDataClient;
    private final ObjectMapper objectMapper;

    @Value("${public-data.tar-rlte-url}")
    private String tarRlteUrl;

    public List<FestivalItem> searchFestivals(String eventStartDate, String eventEndDate, String areaCode, String sigunguCode, String arrange, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("eventStartDate", eventStartDate);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (eventEndDate != null) params.put("eventEndDate", eventEndDate);
        if (areaCode != null) params.put("areaCode", areaCode);
        if (sigunguCode != null) params.put("sigunguCode", sigunguCode);
        if (arrange != null) params.put("arrange", arrange);
        return parseList("/searchFestival2", params, FestivalItem.class);
    }

    public List<LocationBasedListItem> getLocationBased(String mapX, String mapY, String radius, String contentTypeId, String areaCode, String sigunguCode, String cat1, String cat2, String cat3, String arrange, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("mapX", mapX);
        params.put("mapY", mapY);
        params.put("radius", radius);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (contentTypeId != null) params.put("contentTypeId", contentTypeId);
        if (areaCode != null) params.put("areaCode", areaCode);
        if (sigunguCode != null) params.put("sigunguCode", sigunguCode);
        if (cat1 != null) params.put("cat1", cat1);
        if (cat2 != null) params.put("cat2", cat2);
        if (cat3 != null) params.put("cat3", cat3);
        if (arrange != null) params.put("arrange", arrange);
        return parseList("/locationBasedList2", params, LocationBasedListItem.class);
    }

    public List<KeywordSearchItem> searchByKeyword(String keyword, String contentTypeId, String areaCode, String sigunguCode, String cat1, String cat2, String cat3, String arrange, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (contentTypeId != null) params.put("contentTypeId", contentTypeId);
        if (areaCode != null) params.put("areaCode", areaCode);
        if (sigunguCode != null) params.put("sigunguCode", sigunguCode);
        if (cat1 != null) params.put("cat1", cat1);
        if (cat2 != null) params.put("cat2", cat2);
        if (cat3 != null) params.put("cat3", cat3);
        if (arrange != null) params.put("arrange", arrange);
        return parseList("/searchKeyword2", params, KeywordSearchItem.class);
    }

    /**
     * 공통정보조회.
     *
     * <p>KorService2 는 {@code contentId} <b>하나만</b> 받는다. {@code contentTypeId} 를 포함해
     * KorService1 시절의 {@code defaultYN/overviewYN/addrinfoYN/mapinfoYN/firstImageYN} 은 전부
     * {@code INVALID_REQUEST_PARAMETER_ERROR} 를 낸다. overview·좌표·주소·대표이미지는
     * 파라미터 없이 기본으로 내려온다. (2026-08-26 실호출 확인)
     */
    public DetailCommonItem getDetailCommon(String contentId) {
        Map<String, String> params = new HashMap<>();
        params.put("contentId", contentId);
        List<DetailCommonItem> list = parseList("/detailCommon2", params, DetailCommonItem.class);
        return list.isEmpty() ? null : list.get(0);
    }

    public DetailIntroItem getDetailIntro(String contentId, String contentTypeId) {
        Map<String, String> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("contentTypeId", contentTypeId);
        List<DetailIntroItem> list = parseList("/detailIntro2", params, DetailIntroItem.class);
        return list.isEmpty() ? null : list.get(0);
    }

    /** {@code subImageYN} 은 KorService2 에서 거부된다. {@code imageYN} 만 유효하다. */
    public List<DetailImageItem> getDetailImages(String contentId, String imageYN, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (imageYN != null) params.put("imageYN", imageYN);
        return parseList("/detailImage2", params, DetailImageItem.class);
    }

    public List<AreaBasedSyncItem> getAreaBasedSync(String areaCode, String sigunguCode, String contentTypeId, String cat1, String cat2, String cat3, String arrange, String modifiedTime, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (areaCode != null) params.put("areaCode", areaCode);
        if (sigunguCode != null) params.put("sigunguCode", sigunguCode);
        if (contentTypeId != null) params.put("contentTypeId", contentTypeId);
        if (cat1 != null) params.put("cat1", cat1);
        if (cat2 != null) params.put("cat2", cat2);
        if (cat3 != null) params.put("cat3", cat3);
        if (arrange != null) params.put("arrange", arrange);
        if (modifiedTime != null) params.put("modifiedtime", modifiedTime);
        return parseList("/areaBasedSyncList2", params, AreaBasedSyncItem.class);
    }

    /**
     * 키워드 검색 연관 관광지 조회 (한국관광 데이터랩 계열).
     *
     * <p><b>{@code areaCd} · {@code signguCd} · {@code baseYm} 이 전부 필수다.</b> 하나라도 빠지면
     * {@code NO_MANDATORY_REQUEST_PARAMETERS_ERROR} 가 난다. 예전 구현은 뒤의 둘을 선택으로 두어
     * 호출이 100% 실패하고 있었다.
     *
     * <p>{@code signguCd} 는 <b>구(區) 단위까지</b> 정확해야 한다. "수원화성"을 {@code 41110}(수원시)로
     * 물으면 0건, {@code 41115}(팔달구)로 물으면 50건이다. 주소에서 뽑으려면
     * {@link com.tourism.itda.global.tourapi.LdongCodeResolver} 를 쓴다.
     *
     * <p>커버리지가 전부는 아니다. 경복궁·수원화성·한국민속촌은 나오지만 창덕궁·남한산성은 0건이다.
     * 호출부는 <b>없을 수 있다는 전제</b>로 폴백을 준비해야 한다. (2026-08-26 실호출 확인)
     *
     * @param baseYm 기준 연월(YYYYMM). null 이면 {@link #DEFAULT_BASE_YM}
     */
    public List<RelatedTourismItem> searchRelatedByKeyword(String keyword, String areaCd, String signguCd, String baseYm, int pageNo, int numOfRows) {
        if (signguCd == null || signguCd.isBlank()) {
            throw new InvalidRequestException("연관 관광지 조회에는 signguCd(법정동 시군구코드 5자리)가 필요합니다.");
        }
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("areaCd", areaCd);
        params.put("signguCd", signguCd);
        params.put("baseYm", (baseYm == null || baseYm.isBlank()) ? DEFAULT_BASE_YM : baseYm);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        return parseList(tarRlteUrl, "/searchKeyword1", params, RelatedTourismItem.class);
    }

    private <T> List<T> parseList(String endpoint, Map<String, String> params, Class<T> type) {
        return parseList(null, endpoint, params, type);
    }

    private <T> List<T> parseList(String customBaseUrl, String endpoint, Map<String, String> params, Class<T> type) {
        try {
            String raw = customBaseUrl != null
                    ? publicDataClient.get(customBaseUrl, endpoint, params)
                    : publicDataClient.get(endpoint, params);

            JsonNode items = objectMapper.readTree(raw)
                    .path("response").path("body").path("items").path("item");
            if (items.isMissingNode() || items.isNull()) return Collections.emptyList();
            return objectMapper.readerForListOf(type).readValue(items);
        } catch (Exception e) {
            throw new RuntimeException(endpoint + " 조회 실패", e);
        }
    }
}
