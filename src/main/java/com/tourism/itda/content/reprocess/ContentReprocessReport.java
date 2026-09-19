package com.tourism.itda.content.reprocess;

import java.util.List;

/**
 * 저장된 콘텐츠 스토리 재처리 결과 리포트.
 *
 * @param dryRun         true 면 분류/연표 매칭만 미리 보고 스토리는 재생성하지 않은 실행
 * @param byPerson       연표를 '인물' 구간으로 뽑은 작품 수
 * @param byEvent        연표를 '사건' 구간으로 뽑은 작품 수(허구 인물+실제 역사 커버)
 * @param noChronology   인물·사건 모두 매칭 실패해 연표 없이 처리된 작품 수
 * @param classifyFailed Claude 분류 자체가 실패/공백이라 건너뛴 작품 수
 */
public record ContentReprocessReport(
        boolean dryRun,
        int processed,
        int storyRegenerated,
        int byPerson,
        int byEvent,
        int noChronology,
        int classifyFailed,
        List<Item> items
) {
    public record Item(
            Long contentId,
            String title,
            String kingdom,
            String personName,
            String eventName,
            String chronologySource,
            boolean storyRegenerated
    ) {}
}
