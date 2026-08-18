package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.enums.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByKingdom(Kingdom kingdom);
}