package com.tourism.itda.content.reprocess;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.content.service.ContentService;
import com.tourism.itda.content.service.ContentService.StoryReprocessResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 이미 저장된 콘텐츠를 순회하며 (재분류 → 인물/사건 연표 매칭 → 스토리 재생성)을 수행하는 배치.
 *
 * <p>세분화된 사건({@link com.tourism.itda.explore.data.HistoricalEventData})이 추가된 뒤,
 * 기존 콘텐츠의 줄거리를 연표 기반으로 다시 만들기 위해 쓴다.
 *
 * <p>{@code dryRun=true} 로 먼저 돌려 "각 작품이 어떤 사건/인물로 분류되는지"만 검토한 뒤,
 * 결과가 타당하면 {@code dryRun=false} 로 실제 재생성을 돌리는 흐름을 권장한다(스토리 생성은 비용이 크다).
 *
 * <p>범위: 스토리 텍스트와 콘텐츠 행의 분류 필드(kingdom/personType/personName)만 갱신한다.
 * content_kingdom/content_person 매핑 테이블은 건드리지 않는다(스테일 매핑 정리는 별도 판단).
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContentReprocessBatch {

    private final ContentRepository contentRepository;
    private final ContentService contentService;

    /**
     * @param dryRun          true 면 스토리 재생성 없이 분류/연표 매칭 결과만 리포트한다(아무것도 저장 안 함).
     * @param onlyMissingStory true 면 storyBody 가 비어 있는 작품만 대상으로 한다.
     * @param offset          id 오름차순으로 정렬된 대상에서 앞에서부터 건너뛸 개수(페이징용).
     * @param maxContents     이번 실행에서 처리할 최대 작품 수(호출량·비용·프록시 타임아웃 방어).
     */
    @Transactional
    public ContentReprocessReport run(boolean dryRun, boolean onlyMissingStory, int offset, int maxContents) {

        // id 오름차순으로 필터링된 전체 대상을 만든 뒤 offset~offset+maxContents 구간만 처리한다.
        // 한 요청이 프록시 타임아웃(60s) 안에 끝나도록 작게 나눠 반복 호출하는 페이징 용도다.
        List<Content> eligible = new ArrayList<>();
        for (Content content : contentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))) {
            if (onlyMissingStory && content.getStoryBody() != null && !content.getStoryBody().isBlank()) {
                continue;
            }
            eligible.add(content);
        }

        List<Content> targets = offset >= eligible.size()
                ? List.of()
                : eligible.subList(offset, Math.min(offset + maxContents, eligible.size()));

        int processed = 0, storyRegenerated = 0, byPerson = 0, byEvent = 0, noChronology = 0, classifyFailed = 0;
        List<ContentReprocessReport.Item> items = new ArrayList<>();

        log.info("콘텐츠 스토리 재처리 배치 시작 — 이번 처리 {}편 / 전체대상 {}편 "
                        + "(dryRun={}, onlyMissingStory={}, offset={}, maxContents={})",
                targets.size(), eligible.size(), dryRun, onlyMissingStory, offset, maxContents);

        for (Content content : targets) {
            processed++;
            try {
                StoryReprocessResult result = dryRun
                        ? contentService.previewClassification(
                                content.getTitle(), content.getOverview(),
                                content.getKeywords(), content.getTagline())
                        : contentService.classifyAndGenerateStory(
                                content, content.getTitle(), content.getOverview(),
                                content.getKeywords(), content.getTagline());

                switch (result.chronologySource()) {
                    case "PERSON" -> byPerson++;
                    case "EVENT" -> byEvent++;
                    default -> {
                        // 분류 자체가 실패(null kingdom+source NONE)인지, 분류는 됐지만 연표만 없는지 구분.
                        if (result.kingdom() == null && result.matchedPerson() == null
                                && result.usedEventName() == null) {
                            classifyFailed++;
                        } else {
                            noChronology++;
                        }
                    }
                }
                if (result.storyRegenerated()) {
                    storyRegenerated++;
                }

                items.add(new ContentReprocessReport.Item(
                        content.getId(),
                        content.getTitle(),
                        result.kingdom() != null ? result.kingdom().name() : null,
                        result.matchedPerson() != null ? result.matchedPerson().getName() : null,
                        result.usedEventName(),
                        result.chronologySource(),
                        result.storyRegenerated()));

                log.info("[{}/{}] '{}' — source={}, person={}, event={}, storyRegenerated={}",
                        processed, targets.size(), content.getTitle(), result.chronologySource(),
                        result.matchedPerson() != null ? result.matchedPerson().getName() : "-",
                        result.usedEventName() != null ? result.usedEventName() : "-",
                        result.storyRegenerated());

            } catch (Exception e) {
                log.warn("작품 '{}'(id={}) 스토리 재처리 실패 — 건너뜁니다: {}",
                        content.getTitle(), content.getId(), e.toString());
            }
        }

        log.info("콘텐츠 스토리 재처리 배치 종료 — 처리 {}편, 재생성 {}편, 인물연표 {}편, 사건연표 {}편, "
                        + "연표없음 {}편, 분류실패 {}편 (dryRun={})",
                processed, storyRegenerated, byPerson, byEvent, noChronology, classifyFailed, dryRun);

        return new ContentReprocessReport(
                dryRun, processed, storyRegenerated, byPerson, byEvent, noChronology, classifyFailed, items);
    }
}
