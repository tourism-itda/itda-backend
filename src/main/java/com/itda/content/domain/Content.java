package com.itda.content.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 박세현 소유(콘텐츠) placeholder. 내 API 는 title / thumbnail_url 만 조인해서 쓴다.
 * 실제 엔티티 확정되면 교체.
 */
@Getter
@Entity
@Table(name = "content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
}
