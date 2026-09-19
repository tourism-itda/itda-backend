package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentMedia;
import com.tourism.itda.content.entity.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * findAllWithoutMedia() 의 "NOT EXISTS (ContentMedia)" JPQL이 실제 DB에서 의도대로 동작하는지 확인한다.
 *
 * @DataJpaTest 는 JPA/리포지토리 계층만 띄우고 임베디드 DB를 자동 구성한다 — 앱 전체를 띄우지 않으므로
 * (CommandLineRunner 등 시작 시 데이터 시더가 실행되지 않는다) 다른 통합 테스트들과 DB를 공유할 일이 없다.
 */
@DataJpaTest
class ContentRepositoryMediaQueryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ContentRepository contentRepository;

    @Test
    void findAllWithoutMedia_excludesContentsThatAlreadyHaveMedia() {
        Content withMedia = entityManager.persist(
                new Content(1L, 1L, "미디어있음", "https://img.test/a.jpg", "개요", 2020, "MOVIE", "", null));
        Media media = entityManager.persist(new Media(withMedia.getTitle(), withMedia.getMediaType(),
                withMedia.getReleaseYear()));
        entityManager.persist(new ContentMedia(withMedia, media));

        Content withoutMedia = entityManager.persist(
                new Content(2L, 2L, "미디어없음", "https://img.test/b.jpg", "개요", 2018, "TV", "", null));

        entityManager.flush();
        entityManager.clear();

        List<Content> result = contentRepository.findAllWithoutMedia();

        assertThat(result).extracting(Content::getId).containsExactly(withoutMedia.getId());
    }

    @Test
    void findAllWithoutMedia_returnsEmpty_whenEveryContentAlreadyHasMedia() {
        Content content = entityManager.persist(
                new Content(3L, 3L, "미디어있음2", "https://img.test/c.jpg", "개요", 2021, "MOVIE", "", null));
        Media media = entityManager.persist(new Media(content.getTitle(), content.getMediaType(),
                content.getReleaseYear()));
        entityManager.persist(new ContentMedia(content, media));

        entityManager.flush();
        entityManager.clear();

        assertThat(contentRepository.findAllWithoutMedia()).isEmpty();
    }
}
