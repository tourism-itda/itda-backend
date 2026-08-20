package com.tourism.itda.place.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 저장된 장소 상세. place 패키지의 PlaceController/PlaceService(관광공사 API 프록시)와는 별개다 —
 * 그쪽은 저장을 안 하므로, "이 id로 상세를 다시 볼 수 있어야 하는" 장소(콘텐츠 추천 슬롯에 들어간
 * 장소, 사용자가 북마크한 장소 등)를 위해 이 엔티티가 필요하다.
 *
 * <p>fee(입장료) 컬럼은 두지 않는다 — v2에서 제거 확정.
 */
@Entity
@Getter
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double latitude;
    private double longitude;
    private String address;
    private String region;
    private String openingHours;
    private String kakaoPlaceId;

    public Place(String name, String category, String description, double latitude, double longitude,
                 String address, String region, String openingHours, String kakaoPlaceId) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.region = region;
        this.openingHours = openingHours;
        this.kakaoPlaceId = kakaoPlaceId;
    }
}
