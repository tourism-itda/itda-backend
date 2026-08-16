package com.tourism.itda.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Content {

    @Id
    private Long id;

    private Long tmdbId;

    private String title;

    private Integer releaseYear;

    private String mediaType;

    private String posterUrl;

    @Column(columnDefinition = "TEXT")
    private String tagline;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(columnDefinition = "TEXT")
    private String keywords;

    public Content(
            Long id,
            Long tmdbId,
            String title,
            String posterUrl,
            String overview,
            Integer releaseYear,
            String mediaType,
            String keywords,
            String tagline
    ) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.overview = overview;
        this.releaseYear = releaseYear;
        this.mediaType = mediaType;
        this.keywords = keywords;
        this.tagline = tagline;
    }
}