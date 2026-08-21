package com.tourism.itda.planner.controller;

import com.tourism.itda.global.auth.LoginUser;
import com.tourism.itda.planner.dto.*;
import com.tourism.itda.planner.service.ItineraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    /** No.27 GET /itineraries/recommend — 인증 불필요, DB 미저장. */
    @GetMapping("/recommend")
    public RecommendItineraryResponse recommend(@RequestParam("content_id") Long contentId) {
        return itineraryService.recommend(contentId);
    }

    /** No.28 POST /itineraries — 인증 필요. 응답 { itinerary_id }. */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItineraryIdResponse create(@LoginUser Long userId,
                                      @Valid @RequestBody CreateItineraryRequest request) {
        return itineraryService.create(userId, request);
    }

    /** No.29 GET /itineraries — 내 플래너 목록, 인증 필요. */
    @GetMapping
    public List<ItinerarySummaryResponse> myItineraries(@LoginUser Long userId) {
        return itineraryService.getMyItineraries(userId);
    }

    /** No.30 GET /itineraries/:id — 인증 필요, 본인 소유 검증(403). */
    @GetMapping("/{itineraryId}")
    public ItineraryDetailResponse detail(@LoginUser Long userId,
                                          @PathVariable Long itineraryId) {
        return itineraryService.getDetail(userId, itineraryId);
    }

    /** No.31 PATCH /itineraries/:id — 부분 수정, places 전달 시 전체 교체. 응답 { itinerary_id }. */
    @PatchMapping("/{itineraryId}")
    public ItineraryIdResponse update(@LoginUser Long userId,
                                      @PathVariable Long itineraryId,
                                      @Valid @RequestBody UpdateItineraryRequest request) {
        return itineraryService.update(userId, itineraryId, request);
    }

    /** No.32 DELETE /itineraries/:id — soft delete. 응답 { success: true }. */
    @DeleteMapping("/{itineraryId}")
    public SuccessResponse delete(@LoginUser Long userId,
                                  @PathVariable Long itineraryId) {
        itineraryService.delete(userId, itineraryId);
        return SuccessResponse.ok();
    }

    /** No.38 POST /itineraries/:id/share — 커뮤니티 공유. 본인 소유만, 인증 필요. */
    @PostMapping("/{itineraryId}/share")
    public ShareItineraryResponse share(@LoginUser Long userId,
                                        @PathVariable Long itineraryId,
                                        @RequestBody(required = false) ShareItineraryRequest request) {
        return itineraryService.share(userId, itineraryId, request);
    }

    /** No.39 DELETE /itineraries/:id/share — 공유 해제. 본인 소유만, 인증 필요. */
    @DeleteMapping("/{itineraryId}/share")
    public ShareItineraryResponse unshare(@LoginUser Long userId,
                                          @PathVariable Long itineraryId) {
        return itineraryService.unshare(userId, itineraryId);
    }

    /** No.42 POST /itineraries/import — 공유된 일정을 내 플래너로 복사. 인증 필요. */
    @PostMapping("/import")
    @ResponseStatus(HttpStatus.CREATED)
    public ItineraryIdResponse importItinerary(@LoginUser Long userId,
                                               @Valid @RequestBody ImportItineraryRequest request) {
        return itineraryService.importItinerary(userId, request);
    }
}
