package com.tourism.itda.planner.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 저장된 일정(플래너). userId 는 User 엔티티에 FK 로 걸지 않는다 — user 패키지는 다른 팀원 소유라
 * 서로 독립적으로 바뀔 수 있어야 한다. contentId 도 같은 이유로 plain Long(nullable).
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long contentId;

    private String title;
    private LocalDate travelDate;
    private String region;
    private String durationLabel;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItineraryPlace> places = new ArrayList<>();

    public Itinerary(Long userId, Long contentId, String title, LocalDate travelDate,
                      String region, String durationLabel) {
        this.userId = userId;
        this.contentId = contentId;
        this.title = title;
        this.travelDate = travelDate;
        this.region = region;
        this.durationLabel = durationLabel;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void addPlace(ItineraryPlace place) {
        places.add(place);
        place.assignTo(this);
    }
}
