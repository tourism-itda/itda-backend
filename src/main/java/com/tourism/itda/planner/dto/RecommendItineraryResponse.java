package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * No.24 GET /itineraries/recommend 응답 (v2, 저장 전 미리보기, DB 미저장).
 *
 * <p><b>장소가 없어도 200 으로 내려간다.</b> 예전에는 {@code content_place} 가 비면 404 였는데,
 * 프론트에서 서버 오류와 구분이 안 돼 "추천 일정을 불러오지 못했어요" 로 뭉뚱그려 표시됐다.
 * 이제 {@code anchor_source} 로 상태를 구분한다 — {@code NONE} 이면 아직 준비되지 않은 작품이다.
 *
 * @param anchorSource 장소를 어디서 얻었는지.
 *                     <ul>
 *                       <li>{@code CONTENT_PLACE} — 승인된 작품–장소 매핑.
 *                           "작품의 장소"라고 말해도 되는 유일한 등급</li>
 *                       <li>{@code PERSON_CHAIN} — 작품에 나오는 실존 인물의 연고지.
 *                           <b>"촬영지"라고 쓰면 안 된다.</b> "작품 속 인물과 연결된 곳" 정도로</li>
 *                       <li>{@code NONE} — 아직 없음. {@code slots} 가 비어 있다.
 *                           "아직 준비 중" 안내를 띄우면 된다</li>
 *                     </ul>
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecommendItineraryResponse(
        Long contentId,
        String contentTitle,
        String region,
        String anchorSource,
        List<RecommendSlot> slots
) {
}
