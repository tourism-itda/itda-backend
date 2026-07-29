package com.itda.bookmark.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 박세현 소유(북마크) placeholder. v3부터 '장소 전용'(v4 기준).
 * 내 API(No.25)는 is_bookmarked 계산을 위해 (user_id, place_id) 존재 여부만 조회한다.
 */
@Getter
@Entity
@Table(name = "bookmark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "place_id")
    private Long placeId;
}
