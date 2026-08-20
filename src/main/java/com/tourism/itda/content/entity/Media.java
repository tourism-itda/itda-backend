package com.tourism.itda.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long mediaId;

    private String title;

    private String type;

    private Integer releaseYear;

    public Media(String title, String type, Integer releaseYear) {
        this.title = title;
        this.type = type;
        this.releaseYear = releaseYear;
    }
}
