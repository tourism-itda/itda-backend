package com.tourism.itda.explore.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategoryFact> facts = new ArrayList<>();

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String years;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    public Category(String type, String name) {
        this(type, name, null, null, null, null);
    }

    public Category(String type, String name, Category parent, String imageUrl, String description, String years) {
        this.type = type;
        this.name = name;
        this.parent = parent;
        this.imageUrl = imageUrl;
        this.description = description;
        this.years = years;
    }

    public void changeParent(Category parent) {
        this.parent = parent;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeYears(String years) {
        this.years = years;
    }
}