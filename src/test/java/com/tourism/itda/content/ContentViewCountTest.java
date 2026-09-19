package com.tourism.itda.content;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.exception.ContentNotFoundException;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.content.service.ContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * GET /api/contents/:id 조회 시 view_count 가 정확히 1씩 증가하고,
 * 노출되지 않는(PENDING) 콘텐츠 조회 실패 시에는 증가하지 않는지 확인한다.
 */
@SpringBootTest
@ActiveProfiles("h2")
@TestPropertySource(properties = {
        "aws.region=ap-northeast-2",
        "aws.access-key=dummy",
        "aws.secret-key=dummy"
})
@Transactional
class ContentViewCountTest {

    private static final long PUBLISHED_CONTENT_ID = 9_100_001L;
    private static final long PENDING_CONTENT_ID = 9_100_002L;

    @Autowired
    private ContentService contentService;
    @Autowired
    private ContentRepository contentRepository;

    @Test
    void findContent_incrementsViewCount_onEachCall() {
        Content content = publishedContent(PUBLISHED_CONTENT_ID);
        contentRepository.save(content);
        assertThat(contentRepository.findById(PUBLISHED_CONTENT_ID).orElseThrow().getViewCount()).isZero();

        contentService.findContent(PUBLISHED_CONTENT_ID);
        assertThat(contentRepository.findById(PUBLISHED_CONTENT_ID).orElseThrow().getViewCount()).isEqualTo(1L);

        contentService.findContent(PUBLISHED_CONTENT_ID);
        assertThat(contentRepository.findById(PUBLISHED_CONTENT_ID).orElseThrow().getViewCount()).isEqualTo(2L);
    }

    @Test
    void findContent_doesNotIncrementViewCount_whenNotPublished() {
        Content content = pendingContent(PENDING_CONTENT_ID);
        contentRepository.save(content);

        assertThatThrownBy(() -> contentService.findContent(PENDING_CONTENT_ID))
                .isInstanceOf(ContentNotFoundException.class);

        assertThat(contentRepository.findById(PENDING_CONTENT_ID).orElseThrow().getViewCount()).isZero();
    }

    private Content publishedContent(long id) {
        Content content = new Content(id, id, "조회수테스트작품", "https://img.test/poster.jpg",
                "테스트 개요", 2020, "MOVIE", "", null);
        content.publish();
        return content;
    }

    private Content pendingContent(long id) {
        return new Content(id, id, "비공개조회수테스트작품", "https://img.test/poster.jpg",
                "테스트 개요", 2020, "MOVIE", "", null);
    }
}
