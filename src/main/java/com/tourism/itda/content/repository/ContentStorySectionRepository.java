package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentStorySection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentStorySectionRepository extends JpaRepository<ContentStorySection, Long> {

    List<ContentStorySection> findByContentOrderBySortOrderAsc(Content content);
}
