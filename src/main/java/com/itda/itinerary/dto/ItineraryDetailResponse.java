package com.itda.itinerary.dto;

import java.time.LocalDate;
import java.util.List;

/** No.27 응답: 저장 일정 상세 (v2). */
public record ItineraryDetailResponse(
        Long itineraryId,
        String title,
        Long contentId,
        String contentTitle,
        LocalDate travelDate,
        String region,
        String durationLabel,
        boolean isShared,
        List<ItineraryPlaceView> places
) {
}
