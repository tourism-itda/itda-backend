package com.tourism.itda.planner.controller;

import com.tourism.itda.planner.dto.CandidateListResponse;
import com.tourism.itda.planner.dto.RoutePlanRequest;
import com.tourism.itda.planner.dto.RoutePlanResponse;
import com.tourism.itda.planner.route.RoutePlanner;
import com.tourism.itda.planner.route.SegmentCandidateService;
import com.tourism.itda.place.entity.PlaceType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 하루 루트 생성과 구간별 후보 조회.
 *
 * <p>⚠️ 명세서 v4(No.25~32)에 없는 신규 엔드포인트다. 프론트·팀과 합의가 필요하다.
 * 기존 No.27({@code GET /itineraries/recommend})은 콘텐츠 기반 단순 추천으로 그대로 남아 있고,
 * 이쪽은 촬영지 직접선택 + 식당/카페 직접선택을 지원하는 새 흐름이다.
 */
@RestController
@RequestMapping("/api/itineraries/route")
@RequiredArgsConstructor
public class RouteController {

    private final RoutePlanner routePlanner;
    private final SegmentCandidateService segmentCandidateService;

    /**
     * 하루 루트 생성 (비저장).
     *
     * <p>{@code spot_place_ids} 를 비우면 자동추천, 채우면 직접선택 모드다.
     * 식당·카페 칸은 비어서 돌아오고, 사용자가 {@code /candidates} 에서 골라 채운다.
     */
    @PostMapping
    public RoutePlanResponse plan(@Valid @RequestBody RoutePlanRequest request) {
        return routePlanner.plan(request);
    }

    /**
     * 구간 하나의 식당/카페 후보. 우회거리 오름차순.
     *
     * <p>앵커를 place_id 로 받으므로 서버가 루트 상태를 들고 있지 않다.
     * 사용자가 허용거리를 바꾸면 {@code allowance_meters} 만 바꿔 다시 부르면 된다.
     *
     * @param excludeExternalIds 이미 보여준 후보들. 여러 번 눌러도 같은 곳이 다시 나오지 않게 한다.
     */
    @GetMapping("/candidates")
    public CandidateListResponse candidates(
            @RequestParam("start_place_id") Long startPlaceId,
            @RequestParam(value = "end_place_id", required = false) Long endPlaceId,
            @RequestParam("slot_type") PlaceType slotType,
            @RequestParam(value = "allowance_meters", required = false) Long allowanceMeters,
            @RequestParam(value = "exclude_external_ids", required = false) List<String> excludeExternalIds) {

        Set<String> exclude = (excludeExternalIds == null) ? Set.of() : Set.copyOf(excludeExternalIds);
        return segmentCandidateService.find(startPlaceId, endPlaceId, slotType, allowanceMeters, exclude);
    }
}
