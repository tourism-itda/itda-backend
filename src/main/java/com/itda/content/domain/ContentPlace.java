package com.itda.content.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 콘텐츠 ↔ 장소 매핑. recommend_order 로 추천 순서를 관리한다. (내 소유)
 */
@Getter
@Entity
@Table(name = "content_place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_place_id")
    private Long id;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "recommend_order")
    private int recommendOrder;
}
