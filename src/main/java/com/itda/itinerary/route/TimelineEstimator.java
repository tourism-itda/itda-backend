package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import com.itda.place.domain.PlaceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 각 칸의 예상 도착 시각을 계산한다.
 *
 * <p>순서를 정하는 근거가 아니라 <b>표시용이자 검증용</b>이다. 계산 결과 점심 칸이
 * 15시에 나온다면 템플릿이나 체류시간이 잘못됐다는 신호로 쓸 수 있다.
 *
 * <p>야간 운영 가산점({@link SpotScorer})도 이 계산에 의존한다. 마지막 촬영지가
 * 실제로 몇 시에 도착하는지 모르면 "야간 운영 가능"이 의미가 없기 때문이다.
 *
 * <p>빈 칸(식당·카페)은 아직 좌표를 모르므로, 앵커 A→B 사이 이동시간을 그 구간의
 * 칸 수로 균등 분배한다. 후보가 A→B 직선 위에 있을수록(= 우회거리 0에 가까울수록)
 * 정확해지는 근사다. 사용자가 실제로 장소를 고르면 다시 계산된다.
 */
@Component
@RequiredArgsConstructor
public class TimelineEstimator {

    /** 자정을 넘기면 시각 비교가 뒤집히므로 이 값에서 자른다. */
    private static final int MINUTES_IN_DAY = 24 * 60;

    private final DetourFilter detourFilter;
    private final RouteProperties properties;

    /**
     * 템플릿 각 칸의 예상 도착 시각.
     *
     * @param spotCoords 촬영지 좌표들. 템플릿이 요구하는 개수보다 <b>적어도 된다</b> —
     *                   아직 다 안 고른 중간 상태에서도 추정할 수 있어야 하기 때문이다.
     *                   모르는 구간의 이동시간은 0으로 둔다 (실제보다 이르게 나온다).
     */
    public List<LocalTime> estimate(List<TemplateSlot> template, List<Coord> spotCoords, long allowanceMeters) {
        int size = template.size();
        long[] legMinutes = new long[Math.max(0, size - 1)];

        List<Integer> spotSlotIndexes = template.stream()
                .filter(slot -> slot.type() == PlaceType.SPOT)
                .map(TemplateSlot::index)
                .toList();

        // 촬영지와 촬영지 사이: 실제 이동시간을 그 사이의 칸 수로 나눈다.
        for (int i = 0; i + 1 < spotSlotIndexes.size(); i++) {
            if (i + 1 >= spotCoords.size()) {
                break;   // 아직 좌표를 모르는 구간
            }
            int from = spotSlotIndexes.get(i);
            int to = spotSlotIndexes.get(i + 1);
            long total = detourFilter.durationMinutes(spotCoords.get(i), spotCoords.get(i + 1));
            int gaps = to - from;
            long perLeg = Math.max(1, total / gaps);
            for (int leg = from; leg < to; leg++) {
                legMinutes[leg] = perLeg;
            }
        }

        // 마지막 촬영지 이후의 꼬리 구간: 좌표를 모르니 허용거리의 절반쯤 떨어져 있다고 본다.
        if (!spotSlotIndexes.isEmpty() && !spotCoords.isEmpty()) {
            int lastSpotSlot = spotSlotIndexes.get(spotSlotIndexes.size() - 1);
            if (lastSpotSlot < size - 1) {
                long tail = Math.max(1, tailMinutes(spotCoords.get(spotCoords.size() - 1), allowanceMeters));
                for (int leg = lastSpotSlot; leg < size - 1; leg++) {
                    legMinutes[leg] = tail;
                }
            }
        }

        List<LocalTime> times = new ArrayList<>(size);
        long minutesFromStart = 0;
        for (int i = 0; i < size; i++) {
            times.add(toTime(minutesFromStart));
            if (i < size - 1) {
                minutesFromStart += properties.dwellMinutesOf(template.get(i).type()) + legMinutes[i];
            }
        }
        return List.copyOf(times);
    }

    /**
     * 마지막 칸의 예상 도착 시각.
     *
     * <p>{@link SpotScorer} 가 "이 자리가 정말 저녁 이후인가"를 판단할 때 쓴다.
     * 촬영지를 아직 다 고르지 않은 중간 상태에서도 호출되므로, 알고 있는 좌표만으로
     * 추정한다 — 이동시간이 빠지는 만큼 <b>실제보다 이르게</b> 나온다.
     */
    public LocalTime lastSlotArrival(int targetSpotCount, List<Coord> spotCoords, long allowanceMeters) {
        List<TemplateSlot> template = DayTemplate.forSpotCount(targetSpotCount);
        List<LocalTime> times = estimate(template, spotCoords, allowanceMeters);
        return times.get(times.size() - 1);
    }

    /** 꼬리 구간의 이동시간 추정 — 허용거리의 절반만큼 떨어져 있다고 가정. */
    private long tailMinutes(Coord anchor, long allowanceMeters) {
        double halfAllowanceDegrees = (allowanceMeters / 2.0) / 111_000.0;   // 위도 1도 ≈ 111km
        return detourFilter.durationMinutes(
                anchor, new Coord(anchor.latitude() + halfAllowanceDegrees, anchor.longitude()));
    }

    /**
     * 하루 시작 시각 + 경과 분.
     * 자정을 넘어가면 {@code LocalTime} 이 되감겨 시각 비교가 뒤집히므로 23:59 로 자른다.
     */
    private LocalTime toTime(long minutesFromStart) {
        long absolute = properties.dayStart().toSecondOfDay() / 60 + minutesFromStart;
        if (absolute >= MINUTES_IN_DAY) {
            return LocalTime.of(23, 59);
        }
        return LocalTime.ofSecondOfDay(absolute * 60);
    }
}
