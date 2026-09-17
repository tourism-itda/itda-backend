package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.ContentPerson;
import com.tourism.itda.explore.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentPersonRepository
        extends JpaRepository<ContentPerson, ContentPerson.ContentPersonId> {

    List<ContentPerson> findByPerson(Person person);

    /** 루트 앵커 폴백 — 이 작품에 연결된 인물들. content_place 가 비어 있을 때 쓴다. */
    List<ContentPerson> findByContentId(Long contentId);
}