package com.tourism.itda.place.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "place_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_image_id")
    private Long id;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_primary")
    private boolean primary;

    @Column(name = "sort_order")
    private int sortOrder;

    /**
     * 대표 이미지 한 장을 만든다.
     *
     * <p>지금까지 이 테이블에 쓰는 코드가 아예 없어서 관광지 사진이 전부 비어 있었다.
     * 장소를 저장하는 시점({@code TourApiPlaceImporter}/{@code KakaoPlaceImporter})과
     * 백필 배치({@code PlaceImageBackfillBatch})가 이 팩토리로 한 장을 남긴다.
     *
     * <p>{@code sortOrder} 는 0 이다 — 부가 이미지를 뒤에 붙일 여지를 남긴다.
     */
    public static PlaceImage primaryOf(Long placeId, String imageUrl) {
        PlaceImage image = new PlaceImage();
        image.placeId = placeId;
        image.imageUrl = imageUrl;
        image.primary = true;
        image.sortOrder = 0;
        return image;
    }
}
