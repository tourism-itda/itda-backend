package com.tourism.itda.place.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.bookmark.repository.BookmarkRepository;
import com.tourism.itda.global.client.PublicDataClient;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.place.dto.*;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
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

    private final PublicDataClient publicDataClient;
    private final ObjectMapper objectMapper;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final BookmarkRepository bookmarkRepository;

    @Value("${public-data.tar-rlte-url}")
    private String tarRlteUrl;

    /**
     * GET /api/places/{placeId}. userId 가 null 이면 비로그인 → is_bookmarked=false.
     * 위의 메서드들(관광공사 API 프록시)과 달리 이건 저장된 place 테이블을 직접 조회한다.
     */
    public PlaceDetailResponse getPlaceDetail(Long placeId, Long userId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new NotFoundException("장소를 찾을 수 없습니다."));

        List<PlaceImageResponse> images = placeImageRepository
                .findByPlaceIdOrderBySortOrderAsc(placeId).stream()
                .map(PlaceImageResponse::from)
                .toList();

        boolean isBookmarked = userId != null && bookmarkRepository.existsByUserIdAndPlaceId(userId, placeId);

        return PlaceDetailResponse.of(place, images, isBookmarked);
    }

    public List<FestivalItem> searchFestivals(String eventStartDate, String eventEndDate, String areaCode, String sigunguCode, String arrange, String listYN, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("eventStartDate", eventStartDate);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (eventEndDate != null) params.put("eventEndDate", eventEndDate);
        if (areaCode != null) params.put("areaCode", areaCode);
        if (sigunguCode != null) params.put("sigunguCode", sigunguCode);
        if (arrange != null) params.put("arrange", arrange);
        if (listYN != null) params.put("listYN", listYN);
        return parseList("/searchFestival2", params, FestivalItem.class);
    }

    public List<LocationBasedListItem> getLocationBased(String mapX, String mapY, String radius, String contentTypeId, String areaCode, String sigunguCode, String cat1, String cat2, String cat3, String arrange, String listYN, int pageNo, int numOfRows) {
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
        if (listYN != null) params.put("listYN", listYN);
        return parseList("/locationBasedList2", params, LocationBasedListItem.class);
    }

    public List<KeywordSearchItem> searchByKeyword(String keyword, String contentTypeId, String areaCode, String sigunguCode, String cat1, String cat2, String cat3, String arrange, String listYN, int pageNo, int numOfRows) {
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
        if (listYN != null) params.put("listYN", listYN);
        return parseList("/searchKeyword2", params, KeywordSearchItem.class);
    }

    public DetailCommonItem getDetailCommon(String contentId, String contentTypeId, String defaultYN, String firstImageYN, String areaInfoYN, String addrInfoYN, String mapInfoYN, String overviewYN) {
        Map<String, String> params = new HashMap<>();
        params.put("contentId", contentId);
        if (contentTypeId != null) params.put("contentTypeId", contentTypeId);
        if (defaultYN != null) params.put("defaultYN", defaultYN);
        if (firstImageYN != null) params.put("firstImageYN", firstImageYN);
        if (areaInfoYN != null) params.put("areainfoYN", areaInfoYN);
        if (addrInfoYN != null) params.put("addrinfoYN", addrInfoYN);
        if (mapInfoYN != null) params.put("mapinfoYN", mapInfoYN);
        if (overviewYN != null) params.put("overviewYN", overviewYN);
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

    public List<DetailImageItem> getDetailImages(String contentId, String imageYN, String subImageYN, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (imageYN != null) params.put("imageYN", imageYN);
        if (subImageYN != null) params.put("subImageYN", subImageYN);
        return parseList("/detailImage2", params, DetailImageItem.class);
    }

    public List<AreaBasedSyncItem> getAreaBasedSync(String areaCode, String sigunguCode, String contentTypeId, String cat1, String cat2, String cat3, String arrange, String listYN, String modifiedTime, int pageNo, int numOfRows) {
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
        if (listYN != null) params.put("listYN", listYN);
        if (modifiedTime != null) params.put("modifiedtime", modifiedTime);
        return parseList("/areaBasedSyncList2", params, AreaBasedSyncItem.class);
    }

    public List<RelatedTourismItem> searchRelatedByKeyword(String keyword, String areaCd, String signguCd, String baseYm, int pageNo, int numOfRows) {
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("areaCd", areaCd);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numOfRows", String.valueOf(numOfRows));
        if (signguCd != null) params.put("signguCd", signguCd);
        if (baseYm != null) params.put("baseYm", baseYm);
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
