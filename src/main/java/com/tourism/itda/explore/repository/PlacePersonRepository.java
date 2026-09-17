package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.entity.PlacePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PlacePersonRepository
        extends JpaRepository<PlacePerson, PlacePerson.PlacePersonId> {

    List<PlacePerson> findByPerson(Person person);

    /** 루트 앵커 폴백 — 인물 여러 명에 걸린 장소를 한 번에. 인물 수만큼 쿼리하지 않는다. */
    List<PlacePerson> findByPersonPersonIdIn(Collection<Long> personIds);
}