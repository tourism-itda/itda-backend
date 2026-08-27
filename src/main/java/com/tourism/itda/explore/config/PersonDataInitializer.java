package com.tourism.itda.explore.config;

import com.tourism.itda.explore.data.HistoricalPersonData;
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class PersonDataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) {

        for (Person source : HistoricalPersonData.PEOPLE) {

            personRepository.findByName(source.getName())
                    .ifPresentOrElse(
                            existing -> existing.update(
                                    source.getDescription(),
                                    source.getSummary(),
                                    source.getKingdom(),
                                    source.getType(),
                                    source.getStartYear(),
                                    source.getEndYear()
                            ),
                            () -> personRepository.save(source)
                    );
        }
    }
}