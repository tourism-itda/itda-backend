package com.itda.place.service;

import com.itda.common.exception.NotFoundException;
import com.itda.common.tourapi.OpeningHours;
import com.itda.common.tourapi.OpeningHoursParser;
import com.itda.common.tourapi.TourApiClient;
import com.itda.common.tourapi.TourApiIntro;
import com.itda.common.tourapi.TourApiPlace;
import com.itda.itinerary.route.RouteProperties;
import com.itda.place.domain.Place;
import com.itda.place.domain.PlaceSource;
import com.itda.place.domain.PlaceType;
import com.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자가 고른 관광API 후보를 place 테이블에 확정 저장하고 place_id 를 발급한다.
 *
 * <p>이 단계가 필요한 이유: {@code itinerary_place.place_id} 가 {@code place} 를 참조하는
 * FK 라서, 관광API 에서 온 식당은 place 행이 있어야 일정에 저장할 수 있다.
 *
 * <p>후보 목록 조회 시점이 아니라 <b>사용자가 하나를 고른 시점</b>에만 저장한다.
 * 지도에 뿌린 후보 수십 건을 매번 저장하면 테이블이 쓰레기로 찬다.
 *
 * <p>영업시간({@code detailIntro2})도 이때 한 번만 채운다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TourApiPlaceImporter {

    private final TourApiClient tourApiClient;
    private final PlaceRepository placeRepository;
    private final RouteProperties routeProperties;

    /**
     * 이미 저장된 장소면 그대로 재사용하고, 없으면 관광API 에서 받아 새로 저장한다.
     *
     * @param externalId 관광API contentid
     * @param placeType  기대하는 장소 성격. 관광API 응답이 우선한다.
     */
    @Transactional
    public Place importPlace(String externalId, PlaceType placeType) {
        return placeRepository.findBySourceAndExternalId(PlaceSource.TOUR_API, externalId)
                .orElseGet(() -> fetchAndSave(externalId, placeType));
    }

    private Place fetchAndSave(String externalId, PlaceType placeType) {
        TourApiPlace fetched = tourApiClient.findDetail(externalId, placeType)
                .orElseThrow(() -> new NotFoundException(
                        "관광API 에서 장소를 찾을 수 없습니다. (contentId=" + externalId + ")"));

        Place place = Place.ofTourApi(
                fetched.contentId(),
                fetched.placeType(),
                fetched.title(),
                fetched.category(),
                fetched.coord().latitude(),
                fetched.coord().longitude(),
                fetched.address(),
                regionOf(fetched.address()));

        applyOpeningHours(place, externalId, fetched.placeType());
        return placeRepository.save(place);
    }

    /**
     * 영업시간을 채운다. 파싱에 실패하면 원문만 남기고 {@code night_open} 은 false 로 둔다 —
     * 애매하면 밤 슬롯에 넣지 않는 쪽이 안전하다.
     */
    private void applyOpeningHours(Place place, String externalId, PlaceType type) {
        TourApiIntro intro = tourApiClient.findIntro(externalId, type);
        if (intro.isEmpty()) {
            return;
        }
        OpeningHours parsed = OpeningHoursParser.parse(
                intro.openingHours(), routeProperties.nightThreshold());
        place.applyOpeningHours(
                intro.openingHours(), parsed.openTime(), parsed.closeTime(), parsed.nightOpen());
    }

    /**
     * 주소 앞머리에서 시/도를 뽑는다. region 은 일정 목록 표시에만 쓰여서 이 정도면 충분하다.
     */
    private static String regionOf(String address) {
        if (address == null || address.isBlank()) {
            return null;
        }
        String first = address.trim().split("\\s+")[0];
        return first.length() > 50 ? first.substring(0, 50) : first;
    }
}
