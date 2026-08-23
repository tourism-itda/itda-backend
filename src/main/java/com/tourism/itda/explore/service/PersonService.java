package com.tourism.itda.explore.service;

import com.tourism.itda.explore.dto.PersonResponse;
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.repository.PersonRepository;
import com.tourism.itda.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    public List<PersonResponse> getPersons() {
        return personRepository.findAll()
                .stream()
                .map(PersonResponse::new)
                .toList();
    }

    public PersonResponse getPerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new NotFoundException("존재하지 않는 인물입니다.")
                );

        return new PersonResponse(person);
    }
}