package com.itda.place.repository;

import com.itda.place.domain.Place;
import com.itda.place.domain.PlaceSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    /**
     * 관광API 에서 가져온 장소를 중복 저장하지 않기 위한 조회.
     * (source, external_id) 에 유니크 인덱스가 걸려 있다.
     */
    Optional<Place> findBySourceAndExternalId(PlaceSource source, String externalId);
}
