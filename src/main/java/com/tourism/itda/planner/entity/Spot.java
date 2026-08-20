package com.tourism.itda.planner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 콘텐츠 추천 일정에 들어가는 촬영지/장소.
 *
 * <p>place 패키지의 PlaceController 는 관광공사 API 를 그대로 프록시할 뿐 저장하지 않으므로,
 * "이 콘텐츠의 촬영지는 여기다"를 기억하려면 별도로 저장된 레코드가 필요하다. 그 레코드가 이거다.
 *
 * <p>fee(입장료) 컬럼은 두지 않는다 — v2에서 제거 확정.
 */
@Entity
@Getter
@NoArgsConstructor
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String description;
    private String imageUrl;
    private String openingHours;
    private String region;
    private double latitude;
    private double longitude;

    public Spot(String name, String category, String description, String imageUrl,
                String openingHours, String region, double latitude, double longitude) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.openingHours = openingHours;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
