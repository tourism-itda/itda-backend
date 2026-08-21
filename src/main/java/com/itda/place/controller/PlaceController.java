package com.itda.place.controller;

import com.itda.common.auth.LoginUser;
import com.itda.itinerary.dto.RoutePlaceView;
import com.itda.place.dto.AlternativePlaceResponse;
import com.itda.place.dto.ImportPlaceRequest;
import com.itda.place.dto.PlaceDetailResponse;
import com.itda.place.service.PlaceService;
import com.itda.place.service.TourApiPlaceImporter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final TourApiPlaceImporter tourApiPlaceImporter;

    /** No.25 GET /places/:place_id — 인증 선택 (로그인 시에만 is_bookmarked 계산). */
    @GetMapping("/{placeId}")
    public PlaceDetailResponse getPlace(@PathVariable Long placeId,
                                        @LoginUser(required = false) Long userId) {
        return placeService.getPlaceDetail(placeId, userId);
    }

    /** No.26 GET /places/alternative — 인증 불필요. */
    @GetMapping("/alternative")
    public AlternativePlaceResponse getAlternative(
            @RequestParam("content_id") Long contentId,
            @RequestParam("visit_order") int visitOrder,
            @RequestParam(value = "exclude_place_id", required = false) Long excludePlaceId) {
        return placeService.getAlternative(contentId, visitOrder, excludePlaceId);
    }

    /**
     * 사용자가 고른 관광API 후보(식당/카페)를 place 로 확정하고 place_id 를 발급한다.
     *
     * <p>일정 저장(No.28)은 place_id 를 요구하는데 관광API 장소는 place 테이블에 없으므로,
     * 사용자가 후보를 확정한 이 시점에 저장한다. 이미 저장된 곳이면 기존 행을 재사용한다.
     *
     * <p>⚠️ 명세서에 없는 신규 엔드포인트 — 팀·프론트 합의 필요.
     */
    @PostMapping("/import")
    public RoutePlaceView importPlace(@Valid @RequestBody ImportPlaceRequest request) {
        var place = tourApiPlaceImporter.importPlace(request.externalId(), request.placeType());
        return RoutePlaceView.of(place, null);
    }
}
