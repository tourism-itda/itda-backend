package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentMedia;
import com.tourism.itda.content.entity.ContentMediaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentMediaRepository extends JpaRepository<ContentMedia, ContentMediaId> {

    List<ContentMedia> findByContent(Content content);
}
