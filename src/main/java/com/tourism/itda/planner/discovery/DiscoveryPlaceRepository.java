package com.tourism.itda.planner.discovery;

import com.tourism.itda.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * {@link AnchorDiscoveryBatch} 전용 place 조회.
 *
 * <p>기존 {@code PlaceRepository} 를 건드리지 않으려고 같은 엔티티에 대한 별도 리포지터리를 둔다
 * (Spring Data JPA 는 한 엔티티에 여러 리포지터리 인터페이스를 두는 것을 허용한다).
 * 카카오로 검증한 장소는 {@code kakaoPlaceId} 로, TourAPI 로 검증한 장소는 기존
 * {@code PlaceRepository.findBySourceAndExternalId} 로 중복을 판정한다.
 */
public interface DiscoveryPlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByKakaoPlaceId(String kakaoPlaceId);
}
