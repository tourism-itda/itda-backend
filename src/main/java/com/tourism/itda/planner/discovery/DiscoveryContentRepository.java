package com.tourism.itda.planner.discovery;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@link AnchorDiscoveryBatch} 전용 조회 — 노출 중인(PUBLISHED) 작품만 대상으로 한다.
 * 기존 {@code ContentRepository} 를 건드리지 않으려고 같은 엔티티에 대한 별도 리포지터리를 둔다.
 */
public interface DiscoveryContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByStatusOrderByIdAsc(ContentStatus status);
}
