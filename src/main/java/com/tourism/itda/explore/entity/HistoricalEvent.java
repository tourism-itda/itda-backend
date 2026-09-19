package com.tourism.itda.explore.entity;

import com.tourism.itda.explore.enums.Kingdom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 검수된 역사적 시대/사건. 실존 인물이 없는(허구 인물이 실제 역사를 다루는) 작품도
 * 연표(chronology) grounding을 할 수 있도록, 사람이 검수한 연도 구간을 보관한다.
 *
 * <p>Claude는 정해진 사건 이름 중 하나를 '선택'만 하므로(자유 연도 생성 X),
 * startYear/endYear 는 항상 우리가 소유한 검수 값이다 — 연도 오추론으로 인한 연표 오염이 없다.
 */
@Entity
@Getter
@NoArgsConstructor
public class HistoricalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    // Claude 분류 결과의 eventName 과 정확히 일치하는 키. 조회 안정성을 위해 유니크로 둔다.
    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // 카드/목록용 1~2줄 짧은 소개.
    @Column(length = 255)
    private String summary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Kingdom kingdom;

    // 연표에서 사건을 뽑아올 구간. 사람이 검수한 값.
    @Column(nullable = false)
    private Integer startYear;

    @Column(nullable = false)
    private Integer endYear;

    public HistoricalEvent(
            String name,
            String description,
            String summary,
            Kingdom kingdom,
            Integer startYear,
            Integer endYear
    ) {
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.kingdom = kingdom;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public void update(
            String description,
            String summary,
            Kingdom kingdom,
            Integer startYear,
            Integer endYear
    ) {
        this.description = description;
        this.summary = summary;
        this.kingdom = kingdom;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
