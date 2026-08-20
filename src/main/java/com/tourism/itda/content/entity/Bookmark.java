package com.tourism.itda.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookmark", uniqueConstraints = @UniqueConstraint(name = "uk_bookmark_user_place", columnNames = {"user_id", "place_id"}))
@Getter
@NoArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    private Long userId;

    private Long placeId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Bookmark(Long userId, Long placeId) {
        this.userId = userId;
        this.placeId = placeId;
    }
}
