package com.tourism.itda.place.controller;

import com.tourism.itda.place.dto.*;
import com.tourism.itda.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // 위치기반 관광정보조회 - locationBasedList2
    @GetMapping("/location")
    public List<LocationBasedListItem> getLocationBased(
            @RequestParam String mapX,
            @RequestParam String mapY,
            @RequestParam String radius,
            @RequestParam(required = false) String contentTypeId,
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String sigunguCode,
            @RequestParam(required = false) String cat1,
            @RequestParam(required = false) String cat2,
            @RequestParam(required = false) String cat3,
            @RequestParam(required = false) String arrange,
            @RequestParam(required = false) String listYN,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return placeService.getLocationBased(mapX, mapY, radius, contentTypeId, areaCode, sigunguCode, cat1, cat2, cat3, arrange, listYN, pageNo, numOfRows);
    }

    // 키워드 검색 조회 - searchKeyword2
    @GetMapping("/keyword")
    public List<KeywordSearchItem> searchByKeyword(
            @RequestParam String keyword,
            @RequestParam(required = false) String contentTypeId,
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String sigunguCode,
            @RequestParam(required = false) String cat1,
            @RequestParam(required = false) String cat2,
            @RequestParam(required = false) String cat3,
            @RequestParam(required = false) String arrange,
            @RequestParam(required = false) String listYN,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return placeService.searchByKeyword(keyword, contentTypeId, areaCode, sigunguCode, cat1, cat2, cat3, arrange, listYN, pageNo, numOfRows);
    }

    // 행사정보조회 - searchFestival2
    @GetMapping("/festivals")
    public List<FestivalItem> searchFestivals(
            @RequestParam String eventStartDate,
            @RequestParam(required = false) String eventEndDate,
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String sigunguCode,
            @RequestParam(required = false) String arrange,
            @RequestParam(required = false) String listYN,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return placeService.searchFestivals(eventStartDate, eventEndDate, areaCode, sigunguCode, arrange, listYN, pageNo, numOfRows);
    }

    // 공통정보조회 - detailCommon2
    @GetMapping("/common")
    public DetailCommonItem getDetailCommon(
            @RequestParam String contentId,
            @RequestParam(required = false) String contentTypeId,
            @RequestParam(required = false) String defaultYN,
            @RequestParam(required = false) String firstImageYN,
            @RequestParam(required = false) String areaInfoYN,
            @RequestParam(required = false) String addrInfoYN,
            @RequestParam(required = false) String mapInfoYN,
            @RequestParam(required = false) String overviewYN
    ) {
        return placeService.getDetailCommon(contentId, contentTypeId, defaultYN, firstImageYN, areaInfoYN, addrInfoYN, mapInfoYN, overviewYN);
    }

    // 소개정보조회 - detailIntro2
    @GetMapping("/intro")
    public DetailIntroItem getDetailIntro(
            @RequestParam String contentId,
            @RequestParam String contentTypeId
    ) {
        return placeService.getDetailIntro(contentId, contentTypeId);
    }

    // 이미지정보조회 - detailImage2
    @GetMapping("/images")
    public List<DetailImageItem> getDetailImages(
            @RequestParam String contentId,
            @RequestParam(required = false) String imageYN,
            @RequestParam(required = false) String subImageYN,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return placeService.getDetailImages(contentId, imageYN, subImageYN, pageNo, numOfRows);
    }

    // 키워드 검색 연관 관광지 정보 조회 - TarRlteTarService/searchKeyword1
    @GetMapping("/related/keyword")
    public List<RelatedTourismItem> searchRelatedByKeyword(
            @RequestParam String keyword,
            @RequestParam String areaCd,
            @RequestParam(required = false) String signguCd,
            @RequestParam(required = false) String baseYm,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return placeService.searchRelatedByKeyword(keyword, areaCd, signguCd, baseYm, pageNo, numOfRows);
    }

    // 관광정보 동기화 목록 조회 - areaBasedSyncList2
    @GetMapping("/sync")
    public List<AreaBasedSyncItem> getAreaBasedSync(
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String sigunguCode,
            @RequestParam(required = false) String contentTypeId,
            @RequestParam(required = false) String cat1,
            @RequestParam(required = false) String cat2,
            @RequestParam(required = false) String cat3,
            @RequestParam(required = false) String arrange,
            @RequestParam(required = false) String listYN,
            @RequestParam(required = false) String modifiedTime,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return placeService.getAreaBasedSync(areaCode, sigunguCode, contentTypeId, cat1, cat2, cat3, arrange, listYN, modifiedTime, pageNo, numOfRows);
    }
}
