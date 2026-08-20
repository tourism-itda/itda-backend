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
public class ContentFactCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_fact_check_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private String topic;

    @Column(columnDefinition = "TEXT")
    private String fact;

    @Column(columnDefinition = "TEXT")
    private String fiction;

    private Integer sortOrder;

    public ContentFactCheck(Content content, String topic, String fact, String fiction, Integer sortOrder) {
        this.content = content;
        this.topic = topic;
        this.fact = fact;
        this.fiction = fiction;
        this.sortOrder = sortOrder;
    }
}
