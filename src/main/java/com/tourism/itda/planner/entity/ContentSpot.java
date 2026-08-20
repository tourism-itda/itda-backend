package com.tourism.itda.planner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 콘텐츠 ↔ 촬영지(place) 매핑. content 패키지 소유가 아니라 여기 두는 이유는, 이 매핑이
 * 콘텐츠 자체의 속성이 아니라 "일정 추천"이라는 planner 도메인의 관심사이기 때문이다.
 *
 * <p>{@code contentId}, {@code placeId} 모두 FK 로 걸지 않고 단순 Long 값으로 둔 것은 의도적이다 —
 * content/place 패키지는 서로 다른 소유자가 독립적으로 바꿀 수 있어야 한다.
 */
@Entity
@Getter
@NoArgsConstructor
public class ContentSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contentId;
    private Long placeId;

    /** 콘텐츠 안에서의 추천 순서. 낮을수록 먼저 방문. */
    private int recommendOrder;

    public ContentSpot(Long contentId, Long placeId, int recommendOrder) {
        this.contentId = contentId;
        this.placeId = placeId;
        this.recommendOrder = recommendOrder;
    }
}
