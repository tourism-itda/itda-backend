package com.tourism.itda.bookmark.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 장소 북마크. v3부터 콘텐츠가 아니라 장소 전용 — UNIQUE(user_id, place_id).
 *
 * <p>userId 는 User 엔티티에 대한 FK 로 걸지 않는다 — user 패키지는 다른 팀원 소유라
 * 서로 독립적으로 바뀔 수 있어야 한다. placeId 도 같은 이유로 plain Long.
 */
@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "placeId"}))
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long placeId;

    public Bookmark(Long userId, Long placeId) {
        this.userId = userId;
        this.placeId = placeId;
    }
}
