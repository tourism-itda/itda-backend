package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.ContentKingdom;
import com.tourism.itda.explore.enums.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentKingdomRepository
        extends JpaRepository<ContentKingdom, ContentKingdom.ContentKingdomId> {

    List<ContentKingdom> findByKingdom(Kingdom kingdom);

    boolean existsByContentIdAndKingdom(Long contentId, Kingdom kingdom);
}