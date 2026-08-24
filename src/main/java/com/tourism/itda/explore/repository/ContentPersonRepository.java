package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.ContentPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentPersonRepository
        extends JpaRepository<ContentPerson, ContentPerson.ContentPersonId> {
}