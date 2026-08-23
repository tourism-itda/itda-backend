package com.tourism.itda.explore.service;

import com.tourism.itda.explore.data.HistoricalPersonData;
import com.tourism.itda.explore.dto.PersonResponse;
import com.tourism.itda.explore.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    // 전체 인물 조회
    public List<PersonResponse> getPersons() {

        return HistoricalPersonData.PEOPLE.stream()
                .map(PersonResponse::new)
                .toList();
    }

    // 인물 상세 조회
    public PersonResponse getPerson(Long personId) {

        Person person = HistoricalPersonData.PEOPLE.stream()
                .filter(p -> p.getPersonId().equals(personId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 인물입니다.")
                );

        return new PersonResponse(person);
    }
}