package com.itda.itinerary.route;

import com.itda.common.exception.InvalidRequestException;
import com.itda.place.domain.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DayTemplateTest {

    @Test
    @DisplayName("촬영지 3곳이면 팀 확정 템플릿 그대로 나온다")
    void threeSpotsProducesAgreedTemplate() {
        List<TemplateSlot> slots = DayTemplate.forSpotCount(3);

        assertThat(slots).extracting(TemplateSlot::type).containsExactly(
                PlaceType.SPOT, PlaceType.RESTAURANT, PlaceType.CAFE,
                PlaceType.SPOT, PlaceType.RESTAURANT, PlaceType.SPOT);
        assertThat(slots).extracting(TemplateSlot::label).containsExactly(
                "촬영지", "점심", "카페", "촬영지", "저녁", "촬영지");
        assertThat(slots).extracting(TemplateSlot::index).containsExactly(0, 1, 2, 3, 4, 5);
    }

    @Test
    @DisplayName("첫 식사는 점심, 두 번째 식사는 저녁으로 라벨이 붙는다")
    void firstMealIsLunchSecondIsDinner() {
        List<TemplateSlot> slots = DayTemplate.forSpotCount(3);

        assertThat(slots.get(1).label()).isEqualTo("점심");
        assertThat(slots.get(4).label()).isEqualTo("저녁");
    }

    @Test
    @DisplayName("촬영지 3곳 템플릿은 구간이 2개 — 지도에 그릴 타원 2개")
    void threeSpotsHaveTwoSegments() {
        List<FillSegment> segments = DayTemplate.segmentsOf(DayTemplate.forSpotCount(3));

        assertThat(segments).hasSize(2);

        FillSegment first = segments.get(0);
        assertThat(first.startSpotIndex()).isZero();
        assertThat(first.endSpotIndex()).isEqualTo(1);
        assertThat(first.isTrailing()).isFalse();
        assertThat(first.slots()).extracting(TemplateSlot::type)
                .containsExactly(PlaceType.RESTAURANT, PlaceType.CAFE);

        FillSegment second = segments.get(1);
        assertThat(second.startSpotIndex()).isEqualTo(1);
        assertThat(second.endSpotIndex()).isEqualTo(2);
        assertThat(second.slots()).extracting(TemplateSlot::type)
                .containsExactly(PlaceType.RESTAURANT);
    }

    @Test
    @DisplayName("촬영지 2곳이면 마지막 저녁 칸에 뒤쪽 앵커가 없다")
    void twoSpotsLeaveTrailingSegment() {
        List<TemplateSlot> slots = DayTemplate.forSpotCount(2);
        List<FillSegment> segments = DayTemplate.segmentsOf(slots);

        assertThat(slots).extracting(TemplateSlot::type).containsExactly(
                PlaceType.SPOT, PlaceType.RESTAURANT, PlaceType.CAFE,
                PlaceType.SPOT, PlaceType.RESTAURANT);

        assertThat(segments).hasSize(2);
        assertThat(segments.get(0).isTrailing()).isFalse();

        FillSegment trailing = segments.get(1);
        assertThat(trailing.isTrailing()).isTrue();
        assertThat(trailing.endSpotIndex()).isNull();
        assertThat(trailing.startSpotIndex()).isEqualTo(1);
    }

    @Test
    @DisplayName("촬영지 1곳이면 장소 → 식당 두 칸으로 줄어든다")
    void oneSpotShrinksTemplate() {
        List<TemplateSlot> slots = DayTemplate.forSpotCount(1);

        assertThat(slots).extracting(TemplateSlot::type)
                .containsExactly(PlaceType.SPOT, PlaceType.RESTAURANT);
        assertThat(DayTemplate.segmentsOf(slots)).singleElement()
                .satisfies(segment -> assertThat(segment.isTrailing()).isTrue());
    }

    @Test
    @DisplayName("촬영지가 0곳이거나 3곳을 넘으면 거부한다")
    void rejectsOutOfRangeSpotCount() {
        assertThatThrownBy(() -> DayTemplate.forSpotCount(0))
                .isInstanceOf(InvalidRequestException.class);
        assertThatThrownBy(() -> DayTemplate.forSpotCount(4))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("모든 빈 칸은 정확히 한 구간에 속한다")
    void everyFillableSlotBelongsToExactlyOneSegment() {
        for (int spotCount = 1; spotCount <= DayTemplate.MAX_SPOTS; spotCount++) {
            List<TemplateSlot> slots = DayTemplate.forSpotCount(spotCount);
            List<Integer> fillable = slots.stream()
                    .filter(slot -> slot.type() != PlaceType.SPOT)
                    .map(TemplateSlot::index)
                    .toList();

            List<Integer> covered = DayTemplate.segmentsOf(slots).stream()
                    .flatMap(segment -> segment.slots().stream())
                    .map(TemplateSlot::index)
                    .toList();

            assertThat(covered)
                    .as("촬영지 %d곳", spotCount)
                    .containsExactlyElementsOf(fillable)
                    .doesNotHaveDuplicates();
        }
    }
}
