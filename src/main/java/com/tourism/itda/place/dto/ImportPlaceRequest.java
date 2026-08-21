package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.PlaceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 사용자가 고른 관광API 후보를 place 로 확정한다.
 *
 * <p>좌표·이름을 클라이언트에서 받지 않는 것은 의도적이다. 그대로 믿고 저장하면
 * 임의의 place 행을 밀어 넣을 수 있으므로, 서버가 {@code externalId} 로 관광API 에서
 * 다시 조회해 저장한다.
 *
 * @param externalId 관광API contentid ({@code CandidateView.external_id})
 * @param placeType  RESTAURANT 또는 CAFE. 관광API 응답이 다르면 응답 쪽을 따른다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ImportPlaceRequest(
        @NotBlank String externalId,
        @NotNull PlaceType placeType) {
}
