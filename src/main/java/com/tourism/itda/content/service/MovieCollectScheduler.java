package com.tourism.itda.content.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 한국 역사 영화를 주기적으로 자동 수집한다.
 * 아직 저장되지 않은 영화 중 최대 batchSize편을 처리한다.
 * 이미 저장된 영화는 건너뛰므로, 초기 적재 이후에는 신작이 나올 때만 비용이 발생한다.
 * 실행 주기·편수는 application.yml(itda.movie-collect)에서 조정한다.
 */
@Slf4j
@Component
public class MovieCollectScheduler {

    private final ContentService contentService;

    @Value("${itda.movie-collect.batch-size}")
    private int batchSize;

    public MovieCollectScheduler(ContentService contentService) {
        this.contentService = contentService;
    }

    @Scheduled(cron = "${itda.movie-collect.cron}", zone = "Asia/Seoul")
    public void collect() {
        log.info("한국 역사 영화 자동 수집 시작 (최대 {}편)", batchSize);
        try {
            int saved = contentService.collectKoreanHistoryMovies(batchSize);
            log.info("한국 역사 영화 자동 수집 완료: 신규 {}편 저장", saved);
        } catch (Exception e) {
            log.error("한국 역사 영화 자동 수집 실패: {}", e.toString());
        }
    }
}
