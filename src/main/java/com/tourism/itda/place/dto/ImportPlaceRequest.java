package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.PlaceSource;
import com.tourism.itda.place.entity.PlaceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 사용자가 고른 후보를 place 로 확정한다.
 *
 * <p>좌표·이름을 <b>저장에 쓰지 않는 것</b>은 의도적이다. 그대로 믿고 저장하면 임의의 place 행을
 * 밀어 넣을 수 있으므로, 서버가 {@code externalId} 로 출처 API 에 다시 물어 저장한다.
 * 카카오는 id 단건 조회가 없어 {@code name}/좌표를 <b>검색 힌트로만</b> 받는다 —
 * 검색 결과 중 id 가 일치하는 것만 채택하므로 클라이언트가 값을 꾸며도 통하지 않는다.
 *
 * @param externalId 후보의 외부 id ({@code CandidateView.external_id}).
 *                   TOUR_API 면 관광API contentid, KAKAO 면 카카오 place id.
 * @param placeType  RESTAURANT 또는 CAFE. TOUR_API 는 관광API 응답이 다르면 응답 쪽을 따른다.
 * @param source     후보의 출처 ({@code CandidateView.source}). <b>생략하면 TOUR_API 로 본다</b> —
 *                   이 필드가 생기기 전 프론트가 보내던 요청을 그대로 받기 위해서다.
 * @param name       카카오 후보 검증용 상호 ({@code CandidateView.name})
 * @param latitude   카카오 후보 검증용 위도 ({@code CandidateView.latitude})
 * @param longitude  카카오 후보 검증용 경도 ({@code CandidateView.longitude})
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ImportPlaceRequest(
        @NotBlank String externalId,
        @NotNull PlaceType placeType,
        PlaceSource source,
        String name,
        Double latitude,
        Double longitude) {

    public PlaceSource sourceOrTourApi() {
        return (source == null) ? PlaceSource.TOUR_API : source;
    }

    /**
     * 카카오에 되물을 수 있을 만큼의 힌트가 왔는가.
     *
     * <p>카카오 로컬 API 에는 id 단건 조회가 없어서 <b>이름이나 좌표 중 하나는 있어야</b>
     * 검색해서 id 를 대조할 수 있다. {@code source} 가 빠진 요청을 카카오로 재시도할지
     * 판단하는 기준이다.
     */
    public boolean hasKakaoHints() {
        boolean hasName = name != null && !name.isBlank();
        boolean hasCoords = latitude != null && longitude != null;
        return hasName || hasCoords;
    }
}
