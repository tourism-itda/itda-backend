package com.itda.itinerary.dto;

import com.itda.itinerary.route.RouteCandidate;
import com.itda.place.domain.PlaceType;

/**
 * 지도에 핀으로 뿌릴 식당/카페 후보 하나.
 *
 * <p>{@code placeId} 가 아니라 {@code externalId}(관광API contentid)를 준다.
 * 아직 place 테이블에 저장하지 않았기 때문이다 — 사용자가 고르지도 않은 후보 수십 건을
 * 매번 저장하면 테이블이 쓰레기로 찬다. 사용자가 하나를 고르면
 * {@code POST /places/import} 로 그때 저장하고 place_id 를 발급받는다.
 *
 * @param detourMeters 이 곳을 들르면 동선이 늘어나는 거리(m)
 * @param detourKnown  false 면 {@code detourMeters} 는 우회거리가 아니라 앵커로부터의 거리다
 *                     (하루 마지막 구간은 뒤쪽 앵커가 없어 우회거리를 정의할 수 없다).
 *                     이 경우 "동선 +N m" 로 표시하면 안 된다.
 */
public record CandidateView(
        String externalId,
        PlaceType placeType,
        String name,
        String category,
        String address,
        String imageUrl,
        double latitude,
        double longitude,
        long detourMeters,
        boolean detourKnown) {

    public static CandidateView of(RouteCandidate candidate) {
        var place = candidate.place();
        return new CandidateView(
                place.contentId(),
                place.placeType(),
                place.title(),
                place.category(),
                place.address(),
                place.imageUrl(),
                place.coord().latitude(),
                place.coord().longitude(),
                candidate.detourMeters(),
                candidate.detourKnown());
    }
}
