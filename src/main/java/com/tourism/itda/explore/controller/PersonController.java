package com.tourism.itda.explore.controller;

import com.tourism.itda.explore.dto.PersonResponse;
import com.tourism.itda.explore.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/explore/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // 전체 인물 목록
    @GetMapping
    public List<PersonResponse> getPersons() {
        return personService.getPersons();
    }

    // 인물 상세
    @GetMapping("/{personId}")
    public PersonResponse getPerson(
            @PathVariable Long personId
    ) {
        return personService.getPerson(personId);
    }
}