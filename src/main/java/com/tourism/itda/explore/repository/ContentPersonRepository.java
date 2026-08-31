package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.ContentPerson;
import com.tourism.itda.explore.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentPersonRepository
        extends JpaRepository<ContentPerson, ContentPerson.ContentPersonId> {

    List<ContentPerson> findByPerson(Person person);
}