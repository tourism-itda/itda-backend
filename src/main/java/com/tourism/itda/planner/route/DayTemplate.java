package com.tourism.itda.planner.route;

import com.tourism.itda.global.exception.InvalidRequestException;
import com.tourism.itda.place.entity.PlaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * 하루 일정의 슬롯 배치. 팀 확정 템플릿은 촬영지 3곳 기준:
 *
 * <pre>
 *   장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) → 장소
 *          └──── 구간 0 ────┘        └─ 구간 1 ─┘
 * </pre>
 *
 * <p>마지막이 촬영지인 것은 의도된 설계다 — 저녁 이후 시간대라 야간 운영 가능한
 * 촬영지(야경 명소, 야시장 등)를 그 자리에 배치해야 한다.
 * {@link SpotScorer} 가 {@code night_open} 을 점수에 반영한다.
 *
 * <p>촬영지를 3곳 미만으로 고른 경우 템플릿을 뒤에서부터 줄인다. 3곳을 넘겨 고르면
 * 하루에 소화할 수 없으므로 {@link #MAX_SPOTS} 로 잘라낸다 (UI 에서도 막지만 서버가 최종 방어).
 */
public final class DayTemplate {

    /** 하루에 넣을 수 있는 촬영지 최대 개수 — 팀 결정. */
    public static final int MAX_SPOTS = 3;

    private DayTemplate() {
    }

    /**
     * 촬영지 개수에 맞는 슬롯 배치를 만든다.
     *
     * @param spotCount 1 이상 {@link #MAX_SPOTS} 이하
     */
    public static List<TemplateSlot> forSpotCount(int spotCount) {
        if (spotCount < 1 || spotCount > MAX_SPOTS) {
            throw new InvalidRequestException(
                    "촬영지는 1곳 이상 " + MAX_SPOTS + "곳 이하로 선택해야 합니다. (요청: " + spotCount + ")");
        }

        List<PlaceType> shape = switch (spotCount) {
            // 장소 → 식당(점심)
            case 1 -> List.of(PlaceType.SPOT, PlaceType.RESTAURANT);
            // 장소 → 식당(점심) → 카페 → 장소 → 식당(저녁)
            case 2 -> List.of(PlaceType.SPOT, PlaceType.RESTAURANT, PlaceType.CAFE,
                    PlaceType.SPOT, PlaceType.RESTAURANT);
            // 장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) → 장소
            default -> List.of(PlaceType.SPOT, PlaceType.RESTAURANT, PlaceType.CAFE,
                    PlaceType.SPOT, PlaceType.RESTAURANT, PlaceType.SPOT);
        };

        List<TemplateSlot> slots = new ArrayList<>(shape.size());
        int mealsSoFar = 0;
        for (int i = 0; i < shape.size(); i++) {
            PlaceType type = shape.get(i);
            String label = switch (type) {
                case SPOT -> "촬영지";
                case CAFE -> "카페";
                case RESTAURANT -> (mealsSoFar++ == 0) ? "점심" : "저녁";
            };
            slots.add(new TemplateSlot(i, type, label));
        }
        return List.copyOf(slots);
    }

    /**
     * 템플릿을 '촬영지 사이에 채워 넣을 구간'들로 쪼갠다.
     * 지도에 그릴 타원 개수가 곧 이 리스트의 크기다.
     */
    public static List<FillSegment> segmentsOf(List<TemplateSlot> slots) {
        List<FillSegment> segments = new ArrayList<>();
        List<TemplateSlot> pending = new ArrayList<>();

        int spotsSeen = 0;
        int startSpotIndex = -1;

        for (TemplateSlot slot : slots) {
            if (slot.type() == PlaceType.SPOT) {
                if (!pending.isEmpty()) {
                    // 앞뒤 앵커가 모두 있는 구간이 하나 닫혔다.
                    segments.add(new FillSegment(
                            segments.size(), startSpotIndex, spotsSeen, List.copyOf(pending)));
                    pending.clear();
                }
                startSpotIndex = spotsSeen;
                spotsSeen++;
            } else {
                pending.add(slot);
            }
        }

        if (!pending.isEmpty()) {
            // 촬영지로 끝나지 않은 템플릿 — 뒤쪽 앵커가 없는 꼬리 구간.
            segments.add(new FillSegment(
                    segments.size(), startSpotIndex, null, List.copyOf(pending)));
        }
        return List.copyOf(segments);
    }
}
