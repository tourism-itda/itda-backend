package com.itda.content.controller;

import com.itda.common.exception.NotFoundException;
import com.itda.content.dto.ContentSpotResponse;
import com.itda.itinerary.route.ContentSpot;
import com.itda.itinerary.route.RoutePlanner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 콘텐츠에 매핑된 촬영지 전체 목록. 사용자가 "꼭 가고 싶은 곳"을 직접 고르는 화면용이다.
 *
 * <p>⚠️ 명세서에 없는 신규 엔드포인트이고, 경로상 <b>콘텐츠 도메인(박세현 파트)</b>과 겹친다.
 * 나중에 합칠 때 이쪽 컨트롤러를 콘텐츠 파트로 옮기거나, 경로를 조정해야 할 수 있다.
 * 지금은 내 루트 생성 흐름에 필요해서 여기 둔다.
 */
@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentPlaceController {

    private final RoutePlanner routePlanner;

    /** recommend_order 순으로 정렬된 촬영지 목록. */
    @GetMapping("/{contentId}/places")
    public List<ContentSpotResponse> spots(@PathVariable Long contentId) {
        List<ContentSpot> spots = routePlanner.loadSpots(contentId);
        if (spots.isEmpty()) {
            throw new NotFoundException("이 콘텐츠에 등록된 촬영지가 없습니다.");
        }
        Map<Long, String> images = routePlanner.primaryImages(spots);
        return spots.stream()
                .map(spot -> ContentSpotResponse.of(spot, images.get(spot.placeId())))
                .toList();
    }
}
