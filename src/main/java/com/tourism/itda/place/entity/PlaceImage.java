package com.tourism.itda.place.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Place 와 같은 place 패키지 소유라 plain Long 대신 관계로 걸어도 되지만,
     *  다른 엔티티들과의 조회 패턴(findAllById 일괄조회)을 맞추려고 그대로 Long 으로 둔다. */
    private Long placeId;

    private String imageUrl;
    private boolean isPrimary;
    private int sortOrder;

    public PlaceImage(Long placeId, String imageUrl, boolean isPrimary, int sortOrder) {
        this.placeId = placeId;
        this.imageUrl = imageUrl;
        this.isPrimary = isPrimary;
        this.sortOrder = sortOrder;
    }
}
