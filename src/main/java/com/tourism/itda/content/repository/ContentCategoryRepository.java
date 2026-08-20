package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentCategory;
import com.tourism.itda.content.entity.ContentCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentCategoryRepository extends JpaRepository<ContentCategory, ContentCategoryId> {

    List<ContentCategory> findByContent(Content content);
}
