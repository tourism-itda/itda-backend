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

            // findByName(이름만)으로 조회하면 같은 이름이 서로 다른 kingdom으로 존재해야 하는
            // 인물(예: 고종 = 조선 왕 / 대한제국 황제)이 한 행으로 뭉개진다. PlaceDataInitializer가
            // (이름, kingdom) 조합으로 조회하므로 여기도 동일한 키로 맞춘다.
            personRepository.findByNameAndKingdom(source.getName(), source.getKingdom())
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