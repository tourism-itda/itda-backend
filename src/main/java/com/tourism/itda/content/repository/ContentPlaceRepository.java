package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.entity.ContentPlaceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentPlaceRepository extends JpaRepository<ContentPlace, ContentPlaceId> {

    List<ContentPlace> findByContentOrderByRecommendOrderAsc(Content content);
}
