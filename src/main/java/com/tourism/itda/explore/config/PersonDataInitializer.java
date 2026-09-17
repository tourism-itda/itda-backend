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
                            existing -> {
                                // update()만 호출하면 findByNameAndKingdom로 가져온 시점에 이미 트랜잭션이
                                // 끝나 detach된 엔티티를 메모리에서만 바꾸는 꼴이라 DB에 반영되지 않는다
                                // (run()에 @Transactional이 없음). save()로 명시적으로 다시 반영한다.
                                existing.update(
                                        source.getDescription(),
                                        source.getSummary(),
                                        source.getKingdom(),
                                        source.getType(),
                                        source.getStartYear(),
                                        source.getEndYear()
                                );
                                personRepository.save(existing);
                            },
                            () -> personRepository.save(source)
                    );
        }
    }
}