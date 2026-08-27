package com.tourism.itda.explore.config;

import com.tourism.itda.explore.data.HistoricalPlaceData;
import com.tourism.itda.explore.entity.PlaceKingdom;
import com.tourism.itda.explore.entity.PlacePerson;
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.repository.PersonRepository;
import com.tourism.itda.explore.repository.PlaceKingdomRepository;
import com.tourism.itda.explore.repository.PlacePersonRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PlaceDataInitializer implements CommandLineRunner {

    private final PlaceRepository placeRepository;
    private final PersonRepository personRepository;
    private final PlaceKingdomRepository placeKingdomRepository;
    private final PlacePersonRepository placePersonRepository;

    @Override
    @Transactional
    public void run(String... args) {

        if (placeRepository.count() > 0) {
            return;
        }

        validatePlaceMappings();

        // 1. 장소 저장
        List<Place> places = HistoricalPlaceData.PLACES.stream()
                .map(data -> Place.ofSeed(
                        data.name(),
                        data.category(),
                        data.description(),
                        data.latitude(),
                        data.longitude(),
                        data.address(),
                        data.region()
                ))
                .toList();

        placeRepository.saveAll(places);

        // 장소 이름 → Place 매핑
        Map<String, Place> placeMap = new HashMap<>();

        for (Place place : places) {
            placeMap.put(place.getName(), place);
        }

        // 2. 나라-장소 매핑
        List<PlaceKingdom> kingdomMappings =
                HistoricalPlaceData.KINGDOM_PLACES.stream()
                        .flatMap(data -> data.placeNames().stream()
                                .map(placeName -> {

                                    Place place = placeMap.get(placeName);

                                    if (place == null) {
                                        throw new IllegalStateException(
                                                "존재하지 않는 장소입니다. placeName=" + placeName
                                        );
                                    }

                                    return new PlaceKingdom(
                                            place,
                                            data.kingdom()
                                    );
                                }))
                        .toList();

        placeKingdomRepository.saveAll(kingdomMappings);

// 3. 인물-장소 매핑
        List<PlacePerson> personMappings =
                HistoricalPlaceData.PERSON_PLACES.stream()
                        .flatMap(data -> {
                            Person person = personRepository
                                    .findByNameAndKingdom(data.personName(), data.kingdom())
                                    .orElseThrow(() ->
                                            new IllegalStateException(
                                                    "존재하지 않는 인물입니다. personName="
                                                            + data.personName()
                                                            + ", kingdom="
                                                            + data.kingdom()
                                            )
                                    );

                            return data.placeNames().stream()
                                    .map(placeName -> {
                                        Place place = placeMap.get(placeName);

                                        if (place == null) {
                                            throw new IllegalStateException(
                                                    "존재하지 않는 장소입니다. placeName="
                                                            + placeName
                                            );
                                        }

                                        return new PlacePerson(place, person);
                                    });
                        })
                        .toList();

        placePersonRepository.saveAll(personMappings);
    }

    private void validatePlaceMappings() {

        Map<String, Boolean> placeNames = new HashMap<>();

        HistoricalPlaceData.PLACES.forEach(data ->
                placeNames.put(data.name(), true)
        );

        HistoricalPlaceData.KINGDOM_PLACES.forEach(data ->
                data.placeNames().forEach(placeName ->
                        placeNames.putIfAbsent(placeName, false)
                )
        );

        HistoricalPlaceData.PERSON_PLACES.forEach(data ->
                data.placeNames().forEach(placeName ->
                        placeNames.putIfAbsent(placeName, false)
                )
        );

        List<String> missingPlaces = placeNames.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(Map.Entry::getKey)
                .toList();

        if (!missingPlaces.isEmpty()) {
            throw new IllegalStateException(
                    "HistoricalPlaceData.PLACES에 등록되지 않은 장소:\n"
                            + String.join("\n", missingPlaces)
            );
        }
    }
}