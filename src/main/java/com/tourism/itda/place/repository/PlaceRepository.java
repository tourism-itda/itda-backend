package com.tourism.itda.place.repository;

import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceSource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findBySourceAndExternalId(
            PlaceSource source,
            String externalId
    );

    Optional<Place> findByName(String name);

    /**
     * 카카오에서 온 장소의 중복 판정용. {@code source=KAKAO} 인 행은 {@code external_id} 가 비고
     * {@code kakao_place_id} 에 id 가 들어간다.
     */
    Optional<Place> findByKakaoPlaceId(String kakaoPlaceId);

    /**
     * 대표 이미지가 아직 없는 장소. 사진 백필 배치({@code PlaceImageBackfillBatch})가 쓴다.
     *
     * <p>{@code place_image} 는 FK 없이 {@code place_id} 컬럼만 들고 있어 연관관계가 없다.
     * 그래서 조인 대신 상관 서브쿼리로 판정한다.
     */
    @Query("""
            select p from Place p
            where not exists (
                select 1 from PlaceImage i
                where i.placeId = p.id and i.primary = true
            )
            order by p.id asc
            """)
    List<Place> findWithoutPrimaryImage(Pageable pageable);

    /** 출처별 전체 장소 수. 사진 현황 조회용. */
    @Query("select p.source as source, count(p) as total from Place p group by p.source")
    List<SourceCount> countBySource();

    /** 출처별 <b>대표 이미지가 있는</b> 장소 수. 사진 현황 조회용. */
    @Query("""
            select p.source as source, count(p) as total from Place p
            where exists (
                select 1 from PlaceImage i
                where i.placeId = p.id and i.primary = true
            )
            group by p.source
            """)
    List<SourceCount> countWithPrimaryImageBySource();

    /** {@link #countBySource()} 결과 한 줄. */
    interface SourceCount {
        PlaceSource getSource();

        long getTotal();
    }
}