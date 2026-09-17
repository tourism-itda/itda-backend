package com.tourism.itda.place.service;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.exception.InvalidRequestException;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.global.kakao.KakaoLocalClient;
import com.tourism.itda.global.kakao.KakaoPlace;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceType;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 사용자가 고른 <b>카카오</b> 식당/카페 후보를 place 테이블에 확정 저장한다.
 *
 * <p>{@link TourApiPlaceImporter} 의 카카오판이다. 식당·카페 후보는 TourAPI 를 먼저 채우고
 * 모자랄 때 카카오로 보충하는데({@code planner.route.CandidateFinder}), 지금까지 확정 경로가
 * TourAPI 하나뿐이라 <b>카카오 후보는 고르는 족족 "관광API 에서 장소를 찾을 수 없습니다" 로
 * 튕겨 나가 루트에 담기지 못했다</b>. 카카오 place id 는 관광API 의 contentId 가 아니므로
 * {@code detailCommon2} 로 조회될 리가 없다.
 *
 * <p>TourAPI 쪽과 마찬가지로 <b>클라이언트가 보낸 이름·좌표를 그대로 저장하지 않는다.</b>
 * 그대로 믿으면 임의의 place 행을 밀어 넣을 수 있다. 다만 카카오 로컬 API 에는 id 단건 조회가
 * 없어서, 받은 이름·좌표로 <i>검색</i>한 뒤 <b>id 가 일치하는 결과만</b> 채택하고 저장값은
 * 전부 카카오 응답에서 가져온다. 클라이언트 값은 검색 힌트 이상의 힘이 없다.
 *
 * <p>영업시간도 사진도 채우지 않는다 — 카카오 로컬 API 에 그 필드가 아예 없다
 * ({@code place_name/category_name/x/y/place_url} 뿐). 사진은 응답을 만들 때
 * {@link PlaceholderImages} 가 분류에 맞는 기본 이미지로 메운다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoPlaceImporter {

    /** 검색 힌트로 받은 좌표 주변 이 반경 안에서 같은 id 를 찾는다. */
    private static final int VERIFY_RADIUS_M = 500;

    private static final String CATEGORY_RESTAURANT = "FD6";
    private static final String CATEGORY_CAFE = "CE7";
    private static final String CATEGORY_ATTRACTION = "AT4";

    private final KakaoLocalClient kakaoLocalClient;
    private final PlaceRepository placeRepository;

    /**
     * 이미 저장된 장소면 그대로 재사용하고, 없으면 카카오에서 검증해 새로 저장한다.
     *
     * @param kakaoPlaceId 카카오 place id ({@code CandidateView.external_id})
     * @param placeType    기대하는 장소 성격 (RESTAURANT / CAFE)
     * @param nameHint     후보 목록에 실려 나간 상호. 검색 힌트로만 쓴다.
     * @param latitude     후보 목록에 실려 나간 위도. 검색 힌트로만 쓴다.
     * @param longitude    후보 목록에 실려 나간 경도. 검색 힌트로만 쓴다.
     */
    @Transactional
    public Place importPlace(String kakaoPlaceId,
                             PlaceType placeType,
                             String nameHint,
                             Double latitude,
                             Double longitude) {
        return placeRepository.findByKakaoPlaceId(kakaoPlaceId)
                .orElseGet(() -> fetchAndSave(kakaoPlaceId, placeType, nameHint, latitude, longitude));
    }

    private Place fetchAndSave(String kakaoPlaceId,
                               PlaceType placeType,
                               String nameHint,
                               Double latitude,
                               Double longitude) {
        if ((nameHint == null || nameHint.isBlank()) && (latitude == null || longitude == null)) {
            // 둘 다 없으면 검색할 방법이 없다. 프론트가 후보 목록의 값을 그대로 실어 보내야 한다.
            throw new InvalidRequestException(
                    "카카오 장소를 확정하려면 후보의 이름 또는 좌표가 필요합니다. (place_id=" + kakaoPlaceId + ")");
        }

        KakaoPlace verified = verify(kakaoPlaceId, placeType, nameHint, latitude, longitude)
                .orElseThrow(() -> new NotFoundException(
                        "카카오에서 장소를 찾을 수 없습니다. (place_id=" + kakaoPlaceId + ")"));

        String address = (verified.roadAddress() != null) ? verified.roadAddress() : verified.address();
        Place place = Place.ofKakao(
                verified.id(),
                placeType,
                verified.name(),
                verified.categoryName(),
                null,
                verified.coord().latitude(),
                verified.coord().longitude(),
                address,
                AddressRegion.of(address));

        return placeRepository.save(place);
    }

    /**
     * 카카오에 되물어 같은 id 인지 확인한다.
     *
     * <p>이름으로 먼저 찾는다. 후보 목록에 나간 상호를 그대로 쓰면 보통 첫 페이지에서 걸린다.
     * 상호가 안 왔거나 못 찾으면 좌표 주변 카테고리 검색으로 한 번 더 훑는다.
     */
    private Optional<KakaoPlace> verify(String kakaoPlaceId,
                                        PlaceType placeType,
                                        String nameHint,
                                        Double latitude,
                                        Double longitude) {
        Coord center = (latitude != null && longitude != null) ? new Coord(latitude, longitude) : null;

        if (nameHint != null && !nameHint.isBlank()) {
            Optional<KakaoPlace> byName = matchId(
                    kakaoLocalClient.searchKeyword(nameHint, center, VERIFY_RADIUS_M), kakaoPlaceId);
            if (byName.isPresent()) {
                return byName;
            }
        }

        if (center != null) {
            return matchId(kakaoLocalClient.searchCategory(
                    categoryGroupCodeFor(placeType), center, VERIFY_RADIUS_M, true), kakaoPlaceId);
        }

        log.info("카카오 장소 검증 실패 — id={} name='{}' (좌표 없음)", kakaoPlaceId, nameHint);
        return Optional.empty();
    }

    private static Optional<KakaoPlace> matchId(Iterable<KakaoPlace> places, String kakaoPlaceId) {
        for (KakaoPlace place : places) {
            if (kakaoPlaceId.equals(place.id())) {
                return Optional.of(place);
            }
        }
        return Optional.empty();
    }

    private static String categoryGroupCodeFor(PlaceType type) {
        return switch (type) {
            case RESTAURANT -> CATEGORY_RESTAURANT;
            case CAFE -> CATEGORY_CAFE;
            case SPOT -> CATEGORY_ATTRACTION;
        };
    }
}
