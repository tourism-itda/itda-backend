package com.tourism.itda.planner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 콘텐츠 ↔ 촬영지 매핑. content 패키지 소유가 아니라 여기 두는 이유는, 이 매핑이
 * 콘텐츠 자체의 속성이 아니라 "일정 추천"이라는 planner 도메인의 관심사이기 때문이다.
 *
 * <p>{@code contentId} 를 Content 엔티티에 대한 JPA 연관관계(FK)로 걸지 않고 단순 Long 값으로
 * 둔 것은 의도적이다 — content 패키지는 다른 팀원 소유라 서로 독립적으로 바뀔 수 있어야 한다.
 */
@Entity
@Getter
@NoArgsConstructor
public class ContentSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contentId;
    private Long spotId;

    /** 콘텐츠 안에서의 추천 순서. 낮을수록 먼저 방문. */
    private int recommendOrder;

    public ContentSpot(Long contentId, Long spotId, int recommendOrder) {
        this.contentId = contentId;
        this.spotId = spotId;
        this.recommendOrder = recommendOrder;
    }
}
