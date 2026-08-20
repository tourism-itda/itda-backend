package com.tourism.itda.planner.controller;

import com.tourism.itda.planner.dto.RecommendItineraryResponse;
import com.tourism.itda.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itineraries")
@RequiredArgsConstructor
public class ItineraryController {

    private final PlannerService plannerService;

    /** GET /api/itineraries/recommend?content_id={id} — 인증 불필요, DB 미저장 미리보기. */
    @GetMapping("/recommend")
    public RecommendItineraryResponse recommend(@RequestParam("content_id") Long contentId) {
        return plannerService.recommend(contentId);
    }
}
