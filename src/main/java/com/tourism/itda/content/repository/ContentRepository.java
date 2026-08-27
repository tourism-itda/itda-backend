package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {


    @Query(value = "SELECT DISTINCT c FROM Content c "
            + "LEFT JOIN ContentCategory cc ON cc.content = c "
            + "LEFT JOIN ContentMedia cm ON cm.content = c "
            + "LEFT JOIN cm.media m "
            + "WHERE (:likePattern IS NULL OR c.title LIKE :likePattern) "
            + "AND (:mediaType IS NULL OR m.type = :mediaType) "
            + "AND (:categoryId IS NULL OR cc.id.categoryId = :categoryId)",
            countQuery = "SELECT COUNT(DISTINCT c) FROM Content c "
                    + "LEFT JOIN ContentCategory cc ON cc.content = c "
                    + "LEFT JOIN ContentMedia cm ON cm.content = c "
                    + "LEFT JOIN cm.media m "
                    + "WHERE (:likePattern IS NULL OR c.title LIKE :likePattern) "
                    + "AND (:mediaType IS NULL OR m.type = :mediaType) "
                    + "AND (:categoryId IS NULL OR cc.id.categoryId = :categoryId)")
    Page<Content> search(
            @Param("likePattern") String likePattern,
            @Param("mediaType") String mediaType,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );
}