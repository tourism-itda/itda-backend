package com.tourism.itda.event.service;

import com.tourism.itda.event.dto.EventSummaryResponse;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiFestival;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 오늘 이후 시작하는 축제·공연·행사 목록.
 *
 * <p>느림의 원인은 (1) 콜드 캐시일 때 사용자 요청 스레드가 관광API 목록 호출을 기다리고,
 * (2) 결과마다 홈페이지(detailIntro2)를 하나씩 순차로 다시 호출하던 N+1 구조였다.
 * 게다가 홈페이지가 없는 행사는 computeIfAbsent 가 null 을 저장하지 않아 매 요청마다 재호출됐다.
 *
 * <p>해결: 백그라운드 스케줄러가 목록과 홈페이지를 미리(병렬로) 데워 캐시에 넣어 두고,
 * 사용자 요청은 순수 메모리 캐시만 읽게 한다. "홈페이지 없음"도 표식으로 캐시해 재호출을 막는다.
 * 그래서 요청 경로에서는 관광API 호출이 없다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private static final int FETCH_ROWS = 100;
    // 요청에서 자주 쓰는 limit(기본 3)을 넉넉히 덮도록 상위 N개 홈페이지를 미리 병렬 조회해 둔다.
    private static final int PREFETCH_HOMEPAGES = 30;
    // "홈페이지 없음"을 이미 확인했다는 표식(computeIfAbsent 가 null 을 저장하지 않아 매번 재호출되는 것 방지).
    private static final String NO_HOMEPAGE = "";

    private final TourApiClient tourApiClient;

    private volatile List<TourApiFestival> cache = List.of();
    private final Map<String, String> homepageCache = new ConcurrentHashMap<>();

    // 홈페이지 프리페치용 데몬 스레드 풀. 관광API 호출이 블로킹이라 소수 스레드로 병렬 처리한다.
    private final ExecutorService prefetchPool = Executors.newFixedThreadPool(8, r -> {
        Thread t = new Thread(r, "festival-prefetch");
        t.setDaemon(true);
        return t;
    });

    public List<EventSummaryResponse> getUpcoming(int limit) {
        LocalDate today = LocalDate.now();
        return festivals().stream()
                .filter(f -> f.eventStartDate() != null && !f.eventStartDate().isBefore(today))
                .sorted(Comparator.comparing(TourApiFestival::eventStartDate))
                .limit(limit)
                .map(f -> new EventSummaryResponse(
                        f.contentId(), f.title(), f.imageUrl(), f.address(),
                        f.eventStartDate(), f.eventEndDate(), homepageFor(f.contentId()),
                        f.longitude(), f.latitude()))
                .toList();
    }

    /** 30분마다(기동 직후 1회 포함) 캐시를 미리 데워, 사용자 요청이 콜드 캐시를 맞지 않게 한다. */
    @Scheduled(fixedDelayString = "PT30M")
    public void warmCache() {
        try {
            refresh();
        } catch (Exception e) {
            log.warn("행사 캐시 예열 실패 — 기존 캐시를 유지합니다: {}", e.toString());
        }
    }

    private List<TourApiFestival> festivals() {
        // 스케줄러가 갱신을 담당한다. 요청 스레드는 캐시만 읽되, 기동 직후 등 캐시가 완전히
        // 비어 있을 때만 한 번 동기로 채운다(이후 요청은 관광API 호출 없이 메모리로 응답).
        if (cache.isEmpty()) {
            refresh();
        }
        return cache;
    }

    private synchronized void refresh() {
        long start = System.currentTimeMillis();
        List<TourApiFestival> fetched = tourApiClient.findUpcomingFestivals(LocalDate.now(), FETCH_ROWS);

        // 새로 가져온 결과가 비었고 기존 캐시엔 값이 있으면 관광API 실패(타임아웃 등)로 보고 기존 캐시를 유지한다.
        if (fetched.isEmpty() && !cache.isEmpty()) {
            log.warn("행사 목록이 비어 반환됨 — 관광API 실패로 보고 기존 캐시({}건)를 유지합니다", cache.size());
            return;
        }

        cache = fetched;
        prefetchHomepages(fetched);
        log.info("행사 캐시 갱신 완료 — {}건, 홈페이지 프리페치 {}건 ({}ms)",
                fetched.size(), homepageCache.size(), System.currentTimeMillis() - start);
    }

    /** 상위 N개 행사의 홈페이지를 병렬로 미리 조회해 캐시에 채운다. */
    private void prefetchHomepages(List<TourApiFestival> festivals) {
        LocalDate today = LocalDate.now();
        List<String> ids = festivals.stream()
                .filter(f -> f.eventStartDate() != null && !f.eventStartDate().isBefore(today))
                .sorted(Comparator.comparing(TourApiFestival::eventStartDate))
                .map(TourApiFestival::contentId)
                .filter(id -> id != null && !id.isBlank())
                .distinct()
                .limit(PREFETCH_HOMEPAGES)
                .toList();

        Map<String, String> fresh = new ConcurrentHashMap<>();
        CompletableFuture<?>[] futures = ids.stream()
                .map(id -> CompletableFuture.runAsync(() -> {
                    String homepage = tourApiClient.findFestivalHomepage(id);
                    fresh.put(id, homepage != null ? homepage : NO_HOMEPAGE);
                }, prefetchPool))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        homepageCache.clear();
        homepageCache.putAll(fresh);
    }

    private String homepageFor(String contentId) {
        if (contentId == null || contentId.isBlank()) {
            return null;
        }
        String cached = homepageCache.get(contentId);
        if (cached != null) {
            return cached.isEmpty() ? null : cached;   // NO_HOMEPAGE 표식은 null 로 되돌린다.
        }
        // 프리페치 범위(상위 N) 밖의 드문 경우 — 이때만 동기 조회 후 캐시에 저장한다.
        String homepage = tourApiClient.findFestivalHomepage(contentId);
        homepageCache.put(contentId, homepage != null ? homepage : NO_HOMEPAGE);
        return homepage;
    }

    @PreDestroy
    void shutdown() {
        prefetchPool.shutdownNow();
    }
}
