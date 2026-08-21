package com.itda.itinerary.route;

import com.itda.place.domain.PlaceType;

/**
 * 하루 템플릿의 한 칸.
 *
 * @param index 템플릿 내 순번 (0-based). 응답의 visit_order 로도 쓰인다.
 * @param type  이 칸에 들어갈 장소의 성격
 * @param label 사용자에게 보여줄 이름 — "촬영지", "점심", "카페", "저녁"
 */
public record TemplateSlot(int index, PlaceType type, String label) {
}
