package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.ContentCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentCharacterRepository
        extends JpaRepository<ContentCharacter, Long> {

    List<ContentCharacter> findByContentIdOrderBySortOrderAsc(Long contentId);
}
