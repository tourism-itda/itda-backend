package com.tourism.itda.place.controller;

import com.tourism.itda.place.dto.PlaceBackfillReport;
import com.tourism.itda.place.dto.PlaceImageStatusResponse;
import com.tourism.itda.place.service.PlaceBackfillBatch;
import com.tourism.itda.place.service.PlaceImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 장소 사진·설명 백필 배치({@link PlaceBackfillBatch}) 수동 실행용 엔드포인트.
 *
 * <p><b>관리자 수동 실행용. 인증·권한 연결은 아직 안 됨.</b>
 * 운영 {@code place_image} / {@code place.description} 에 직접 쓰기 때문에, 인증 미들웨어가
 * 붙기 전까지는 아는 사람만 호출해야 한다
 * ({@code planner.discovery.AnchorDiscoveryController} 와 같은 상태).
 */
@RestController
@RequestMapping("/api/admin/places")
@RequiredArgsConstructor
public class PlaceImageAdminController {

    private final PlaceBackfillBatch placeBackfillBatch;
    private final PlaceImageService placeImageService;

    /**
     * 사진·설명이 채워진 정도를 본다. 외부 API 를 부르지 않으므로 몇 번을 눌러도 안전하다.
     * 백필 전후로 찍어 보면 얼마나 채워졌는지 바로 확인된다.
     */
    @GetMapping("/backfill-status")
    public PlaceImageStatusResponse backfillStatus() {
        return placeImageService.status();
    }

    /**
     * 관광API {@code detailCommon2} + {@code detailImage2} 로 사진과 설명을 채운다.
     *
     * <p>여기서 못 찾은 장소는 실패가 아니라 사진이 기본 이미지로 나간다
     * ({@code PlaceholderImages}). 응답의 {@code not_found_in_tour_api} 가 그 수다.
     *
     * @param maxPlaces 장소당 외부 API 를 1~5회 부르므로 나눠 도는 것을 권한다.
     */
    @PostMapping("/backfill")
    public PlaceBackfillReport backfill(
            @RequestParam(name = "max_places", defaultValue = "50") int maxPlaces) {
        return placeBackfillBatch.run(maxPlaces);
    }
}
