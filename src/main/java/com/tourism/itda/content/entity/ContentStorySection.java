package com.tourism.itda.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ContentStorySection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_story_section_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private String keyword;

    @Column(columnDefinition = "TEXT")
    private String body;

    private Integer sortOrder;

    public ContentStorySection(Content content, String keyword, String body, Integer sortOrder) {
        this.content = content;
        this.keyword = keyword;
        this.body = body;
        this.sortOrder = sortOrder;
    }
}
