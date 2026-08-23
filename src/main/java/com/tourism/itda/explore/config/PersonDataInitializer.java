package com.tourism.itda.explore.config;

import com.tourism.itda.explore.data.HistoricalPersonData;
import com.tourism.itda.explore.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) {
        if (personRepository.count() == 0) {
            personRepository.saveAll(HistoricalPersonData.PEOPLE);
        }
    }
}