package com.tourism.itda.planner.discovery;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@link AnchorDiscoveryBatch} 전용 조회 — 노출 중인(PUBLISHED) 작품과 보류 중인(PENDING) 작품을 대상으로 한다.
 * PENDING 작품도 대상에 넣어, 장소가 새로 생기면 다시 PUBLISHED 로 승격될 수 있게 한다.
 * 기존 {@code ContentRepository} 를 건드리지 않으려고 같은 엔티티에 대한 별도 리포지터리를 둔다.
 */
public interface DiscoveryContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByStatusInOrderByIdAsc(List<ContentStatus> statuses);
}
