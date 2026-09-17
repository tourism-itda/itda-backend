package com.tourism.itda.place.controller;

import com.tourism.itda.place.dto.PlaceImageBackfillReport;
import com.tourism.itda.place.dto.PlaceImageStatusResponse;
import com.tourism.itda.place.service.PlaceImageBackfillBatch;
import com.tourism.itda.place.service.PlaceImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 장소 사진 백필 배치({@link PlaceImageBackfillBatch}) 수동 실행용 엔드포인트.
 *
 * <p><b>관리자 수동 실행용. 인증·권한 연결은 아직 안 됨.</b>
 * 운영 {@code place_image} 테이블에 직접 쓰기 때문에, 인증 미들웨어가 붙기 전까지는
 * 아는 사람만 호출해야 한다 ({@code planner.discovery.AnchorDiscoveryController} 와 같은 상태).
 */
@RestController
@RequestMapping("/api/admin/places")
@RequiredArgsConstructor
public class PlaceImageAdminController {

    private final PlaceImageBackfillBatch placeImageBackfillBatch;
    private final PlaceImageService placeImageService;

    /**
     * 사진이 채워진 정도를 출처별로 본다. 외부 API 를 부르지 않으므로 몇 번을 눌러도 안전하다.
     * 백필 전후로 찍어 보면 얼마나 채워졌는지 바로 확인된다.
     */
    @GetMapping("/image-status")
    public PlaceImageStatusResponse imageStatus() {
        return placeImageService.status();
    }

    @PostMapping("/backfill-images")
    public PlaceImageBackfillReport backfillImages(
            @RequestParam(name = "max_places", defaultValue = "50") int maxPlaces,
            @RequestParam(name = "naver_fallback", defaultValue = "true") boolean naverFallback) {
        return placeImageBackfillBatch.run(maxPlaces, naverFallback);
    }
}
