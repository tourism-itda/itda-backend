package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.entity.PlacePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacePersonRepository
        extends JpaRepository<PlacePerson, PlacePerson.PlacePersonId> {

    List<PlacePerson> findByPerson(Person person);
}