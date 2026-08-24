package com.tourism.itda.explore.entity;

import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Kingdom kingdom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonType type;

    private Integer startYear;

    private Integer endYear;

    public Person(
            String name,
            String description,
            Kingdom kingdom,
            PersonType type,
            Integer startYear,
            Integer endYear
    ) {
        this.name = name;
        this.description = description;
        this.kingdom = kingdom;
        this.type = type;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}