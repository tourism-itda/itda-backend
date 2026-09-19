package com.tourism.itda.explore.config;

import com.tourism.itda.explore.data.HistoricalEventData;
import com.tourism.itda.explore.entity.HistoricalEvent;
import com.tourism.itda.explore.repository.HistoricalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 기동 시 {@link HistoricalEventData} 의 검수된 시대/사건을 DB에 upsert 한다.
 * 인물/장소 시드와 독립적이므로 순서에 의존하지 않는다.
 */
@Component
@RequiredArgsConstructor
@Order(1)
public class HistoricalEventDataInitializer implements CommandLineRunner {

    private final HistoricalEventRepository historicalEventRepository;

    @Override
    public void run(String... args) {

        for (HistoricalEvent source : HistoricalEventData.EVENTS) {

            historicalEventRepository.findByName(source.getName())
                    .ifPresentOrElse(
                            existing -> {
                                existing.update(
                                        source.getDescription(),
                                        source.getSummary(),
                                        source.getKingdom(),
                                        source.getStartYear(),
                                        source.getEndYear()
                                );
                                historicalEventRepository.save(existing);
                            },
                            () -> historicalEventRepository.save(source)
                    );
        }
    }
}
