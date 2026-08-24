package com.tourism.itda.explore.controller;

import com.tourism.itda.explore.dto.KingdomDetailResponse;
import com.tourism.itda.explore.dto.KingdomResponse;
import com.tourism.itda.explore.dto.PersonResponse;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.service.KingdomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.tourism.itda.explore.dto.KingdomContentResponse;

import java.util.List;

@RestController
@RequestMapping("api/explore/kingdoms")
@RequiredArgsConstructor
public class KingdomController {

    private final KingdomService kingdomService;

    // 나라 목록
    @GetMapping
    public List<KingdomResponse> getKingdoms() {
        return kingdomService.getKingdoms();
    }

    // 나라 상세
    @GetMapping("/{kingdom}")
    public KingdomDetailResponse getKingdom(
            @PathVariable Kingdom kingdom
    ) {
        return kingdomService.getKingdom(kingdom);
    }

    // 나라별 인물
    @GetMapping("/{kingdom}/persons")
    public List<PersonResponse> getPersonsByKingdom(
            @PathVariable Kingdom kingdom
    ) {
        return kingdomService.getPersonsByKingdom(kingdom);
    }
    // 나라별 콘텐츠
    @GetMapping("/{kingdom}/contents")
    public List<KingdomContentResponse> getContentsByKingdom(
            @PathVariable Kingdom kingdom
    ) {
        return kingdomService.getContentsByKingdom(kingdom);
    }

    @GetMapping("/{kingdom}/contents/test")
    public List<String> searchKingdomContents(
            @PathVariable Kingdom kingdom
    ) {
        return kingdomService.searchKingdomContents(kingdom);
    }
}