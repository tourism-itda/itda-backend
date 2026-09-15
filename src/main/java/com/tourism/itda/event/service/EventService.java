package com.tourism.itda.event.service;

import com.tourism.itda.event.dto.EventSummaryResponse;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiFestival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * 오늘 이후 시작하는 축제·공연·행사 목록.
 *
 * <p>관광API 호출 결과를 1시간 TTL로 메모리에 캐싱해, 여러 유저가 동시에 요청해도
 * 실제 관광API 호출은 1시간에 한 번만 나가게 한다.
 */
@Service
@RequiredArgsConstructor
public class EventService {

    private static final Duration CACHE_TTL = Duration.ofHours(1);
    // 캐시 1건으로 여러 limit 요청을 받쳐야 하므로 넉넉히 받아 둔다.
    private static final int FETCH_ROWS = 100;

    private final TourApiClient tourApiClient;

    private volatile List<TourApiFestival> cache = List.of();
    private volatile Instant cachedAt = Instant.EPOCH;

    public List<EventSummaryResponse> getUpcoming(int limit) {
        LocalDate today = LocalDate.now();
        return festivals().stream()
                .filter(f -> f.eventStartDate() != null && !f.eventStartDate().isBefore(today))
                .sorted(Comparator.comparing(TourApiFestival::eventStartDate))
                .limit(limit)
                .map(f -> new EventSummaryResponse(
                        f.contentId(), f.title(), f.imageUrl(), f.address(),
                        f.eventStartDate(), f.eventEndDate()))
                .toList();
    }

    private List<TourApiFestival> festivals() {
        if (Duration.between(cachedAt, Instant.now()).compareTo(CACHE_TTL) < 0) {
            return cache;
        }
        return refresh();
    }

    private synchronized List<TourApiFestival> refresh() {
        if (Duration.between(cachedAt, Instant.now()).compareTo(CACHE_TTL) < 0) {
            return cache;   // 락을 기다리는 동안 다른 스레드가 이미 갱신했을 수 있다.
        }
        List<TourApiFestival> fetched = tourApiClient.findUpcomingFestivals(LocalDate.now(), FETCH_ROWS);
        // 새로 가져온 결과가 비어 있고 기존 캐시엔 값이 있다면 관광API 실패(타임아웃 등)일 가능성이
        // 크므로 기존 캐시를 그대로 둔다. 갱신 시점(cachedAt)만 미뤄서 다음 TTL 이후 다시 시도한다.
        if (!fetched.isEmpty() || cache.isEmpty()) {
            cache = fetched;
        }
        cachedAt = Instant.now();
        return cache;
    }
}
