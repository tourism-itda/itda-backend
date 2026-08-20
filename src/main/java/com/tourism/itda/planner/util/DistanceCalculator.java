package com.tourism.itda.planner.util;

/**
 * 하버사인 직선거리 + 평균속도 환산. 실제 도로 길찾기(카카오 API 등)로 교체될 때까지의 임시 근사치다.
 */
public final class DistanceCalculator {

    private static final double EARTH_RADIUS_M = 6_371_000;
    private static final double AVERAGE_SPEED_KMH = 25;

    private DistanceCalculator() {
    }

    public static long distanceMeters(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round(EARTH_RADIUS_M * c);
    }

    public static long durationMinutes(double lat1, double lon1, double lat2, double lon2) {
        long meters = distanceMeters(lat1, lon1, lat2, lon2);
        double hours = (meters / 1000.0) / AVERAGE_SPEED_KMH;
        return Math.max(1, Math.round(hours * 60));
    }
}
