package com.tourism.itda.global.distance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 하버사인 직선거리 + 평균 이동속도 환산 구현. (TODO 1번 결정)
 */
@Component
public class HaversineDistanceCalculator implements DistanceCalculator {

    private static final double EARTH_RADIUS_M = 6_371_000d;

    private final double averageSpeedKmh;

    public HaversineDistanceCalculator(
            @Value("${itda.distance.average-speed-kmh:25}") double averageSpeedKmh) {
        this.averageSpeedKmh = averageSpeedKmh > 0 ? averageSpeedKmh : 25;
    }

    @Override
    public long distanceMeters(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round(EARTH_RADIUS_M * c);
    }

    @Override
    public long durationMinutes(double lat1, double lon1, double lat2, double lon2) {
        long meters = distanceMeters(lat1, lon1, lat2, lon2);
        double metersPerMinute = (averageSpeedKmh * 1000d) / 60d;
        return Math.max(1, Math.round(meters / metersPerMinute));
    }
}
