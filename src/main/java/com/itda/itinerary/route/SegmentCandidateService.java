package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import com.itda.common.exception.InvalidRequestException;
import com.itda.common.exception.NotFoundException;
import com.itda.itinerary.dto.CandidateListResponse;
import com.itda.itinerary.dto.CandidateView;
import com.itda.place.domain.Place;
import com.itda.place.domain.PlaceType;
import com.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 구간 하나의 식당/카페 후보를 조회한다.
 *
 * <p>앵커를 place_id 로 받기 때문에 서버가 루트 상태를 기억할 필요가 없다.
 * 사용자가 허용거리 슬라이더를 움직이면 같은 앵커로 다시 부르기만 하면 된다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SegmentCandidateService {

    private final PlaceRepository placeRepository;
    private final CandidateFinder candidateFinder;
    private final DetourFilter detourFilter;
    private final RouteProperties properties;

    /**
     * @param startPlaceId       앞쪽 앵커 촬영지
     * @param endPlaceId         뒤쪽 앵커. null 이면 하루 마지막 구간이라 앵커 주변을 가까운 순으로 준다.
     * @param slotType           RESTAURANT 또는 CAFE
     * @param requestedAllowance 허용거리(m). null 이면 기본값.
     * @param excludeExternalIds 이미 고른/거부한 관광API contentId — "다른 곳 추천"을 여러 번 눌러도
     *                           같은 곳이 다시 나오지 않게 한다.
     */
    public CandidateListResponse find(Long startPlaceId,
                                      Long endPlaceId,
                                      PlaceType slotType,
                                      Long requestedAllowance,
                                      Set<String> excludeExternalIds) {
        if (slotType == PlaceType.SPOT) {
            throw new InvalidRequestException("촬영지는 후보 조회 대상이 아닙니다. 식당 또는 카페만 조회할 수 있습니다.");
        }

        long allowance = properties.clampAllowance(requestedAllowance);
        Coord start = coordOf(startPlaceId, "구간 시작 장소를 찾을 수 없습니다.");

        if (endPlaceId == null) {
            List<RouteCandidate> candidates = candidateFinder.findAround(
                    start, slotType, allowance, excludeExternalIds, properties.candidateLimit());
            return new CandidateListResponse(slotType, allowance, false, toViews(candidates));
        }

        Coord end = coordOf(endPlaceId, "구간 끝 장소를 찾을 수 없습니다.");
        List<RouteCandidate> candidates = candidateFinder.findBetween(
                start, end, slotType, allowance, excludeExternalIds, properties.candidateLimit());

        return new CandidateListResponse(
                slotType,
                allowance,
                detourFilter.isPartialCoverage(start, end, allowance),
                toViews(candidates));
    }

    private Coord coordOf(Long placeId, String notFoundMessage) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new NotFoundException(notFoundMessage));
        return new Coord(place.getLatitude(), place.getLongitude());
    }

    private static List<CandidateView> toViews(List<RouteCandidate> candidates) {
        return candidates.stream().map(CandidateView::of).toList();
    }
}
