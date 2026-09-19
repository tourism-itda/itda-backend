package com.tourism.itda.content.reprocess;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 저장된 콘텐츠 스토리 재처리 배치({@link ContentReprocessBatch}) 수동 실행용 엔드포인트.
 *
 * <p><b>관리자 수동 실행용. 인증·권한 연결은 아직 안 됨.</b>
 * 운영 content 테이블의 스토리 필드를 직접 갱신하므로 아는 사람만 호출해야 한다.
 *
 * <p>권장 흐름:
 * <ol>
 *   <li>{@code dry_run=true} 로 먼저 호출해 각 작품의 사건/인물 분류 결과를 검토한다(저장 안 됨).</li>
 *   <li>결과가 타당하면 {@code dry_run=false} 로 호출해 실제 스토리를 재생성한다(Claude 호출 비용 발생).</li>
 * </ol>
 */
@RestController
@RequestMapping("/api/admin/content")
@RequiredArgsConstructor
public class ContentReprocessController {

    private final ContentReprocessBatch contentReprocessBatch;

    @PostMapping("/reprocess-story")
    public ContentReprocessReport reprocessStory(
            @RequestParam(name = "dry_run", defaultValue = "true") boolean dryRun,
            @RequestParam(name = "only_missing_story", defaultValue = "false") boolean onlyMissingStory,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "max_contents", defaultValue = "10") int maxContents) {
        return contentReprocessBatch.run(dryRun, onlyMissingStory, offset, maxContents);
    }

    /**
     * 특정 작품 id 들만 골라 재처리한다. 예: ?ids=482209,357228,760497,770322
     * 문제 있는 소수 작품만 정밀 재생성할 때 쓴다.
     */
    @PostMapping("/reprocess-story/by-ids")
    public ContentReprocessReport reprocessStoryByIds(
            @RequestParam(name = "dry_run", defaultValue = "true") boolean dryRun,
            @RequestParam(name = "ids") List<Long> ids) {
        return contentReprocessBatch.runByIds(dryRun, ids);
    }
}
