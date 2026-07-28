package com.tourism.itda.explore.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "category_fact")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_fact_id")
    private Long categoryFactId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "fact_text", nullable = false, columnDefinition = "TEXT")
    private String factText;

    @Column(name = "fact_order")
    private Integer factOrder;
}