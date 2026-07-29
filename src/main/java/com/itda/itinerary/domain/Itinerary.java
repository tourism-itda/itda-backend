package com.itda.itinerary.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "itinerary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content_id")
    private Long contentId;

    private String title;

    @Column(name = "travel_date")
    private LocalDate travelDate;

    private String region;

    @Column(name = "duration_label")
    private String durationLabel;

    @Column(name = "is_shared")
    private boolean shared;

    @Column(name = "source_itinerary_id")
    private Long sourceItineraryId;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItineraryPlace> places = new ArrayList<>();

    @Builder
    private Itinerary(Long userId, Long contentId, String title, LocalDate travelDate,
                      String region, String durationLabel) {
        this.userId = userId;
        this.contentId = contentId;
        this.title = title;
        this.travelDate = travelDate;
        this.region = region;
        this.durationLabel = durationLabel;
    }

    public boolean isOwnedBy(Long userId) {
        return this.userId.equals(userId);
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

    public void softDelete(LocalDateTime now) {
        this.deletedAt = now;
    }

    // --- PATCH 부분 수정 (null 은 미변경) ---
    public void updateTitle(String title) {
        if (title != null) this.title = title;
    }

    public void updateTravelDate(LocalDate travelDate) {
        if (travelDate != null) this.travelDate = travelDate;
    }

    public void updateRegion(String region) {
        if (region != null) this.region = region;
    }

    public void updateDurationLabel(String durationLabel) {
        if (durationLabel != null) this.durationLabel = durationLabel;
    }

    // --- 장소 목록 전체 교체 (PATCH places 전달 시) ---
    public void addPlace(ItineraryPlace place) {
        place.assignItinerary(this);
        this.places.add(place);
    }

    public void replacePlaces(List<ItineraryPlace> newPlaces) {
        this.places.clear(); // orphanRemoval -> 기존 행 삭제
        for (ItineraryPlace p : newPlaces) {
            addPlace(p);
        }
    }
}
