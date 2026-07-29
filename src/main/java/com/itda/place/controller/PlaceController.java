package com.itda.place.controller;

import com.itda.common.auth.LoginUser;
import com.itda.place.dto.AlternativePlaceResponse;
import com.itda.place.dto.PlaceDetailResponse;
import com.itda.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

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
}
