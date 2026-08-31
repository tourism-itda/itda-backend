package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentFactCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentFactCheckRepository extends JpaRepository<ContentFactCheck, Long> {

    List<ContentFactCheck> findByContentOrderBySortOrderAsc(Content content);
}
