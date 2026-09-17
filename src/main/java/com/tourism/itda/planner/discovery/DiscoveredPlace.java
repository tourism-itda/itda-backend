package com.tourism.itda.planner.discovery;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Claude 가 작품 줄거리를 읽고 추출한 장소 후보 1건.
 *
 * <p>좌표는 일부러 받지 않는다. LLM 이 좌표를 지어내는 것을 막기 위함이며,
 * 실재 여부와 좌표 검증은 전부 {@link AnchorDiscoveryService} 가 카카오 로컬 API 로 수행한다.
 */
public record DiscoveredPlace(

        @JsonPropertyDescription("카카오 지도에서 검색할 정확한 장소명. 지도에 실제 등록된 이름이어야 한다. 예: \"화성 융건릉\"")
        String searchKeyword,

        @JsonPropertyDescription("장소와 작품의 관계. HISTORICAL(작품의 실제 역사 현장·인물 유적) / FILMING(실제 촬영지) / EXHIBIT(작품·인물 관련 전시관·기념관) 중 하나")
        String relationType,

        @JsonPropertyDescription("이 장소가 작품과 어떻게 연결되는지 한국어 한 문장, 80자 이내")
        String reason,

        @JsonPropertyDescription("이 후보에 대한 확신도. 1~5. 확실한 근거가 있으면 5, 추측이면 1")
        int confidence,

        @JsonPropertyDescription("이 장소가 있는 시/도. 서울, 인천, 경기, 강원, 충남, 충북, 전남, 전북, 경남, 경북, 제주, 부산, 대구, 광주, 대전, 울산, 세종 중 하나. 모르면 빈 문자열.")
        String region) {
}
