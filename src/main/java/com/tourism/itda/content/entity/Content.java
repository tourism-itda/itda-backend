package com.tourism.itda.content.entity;

import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    private Kingdom kingdom;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private String personName;

    @Column(columnDefinition = "TEXT")
    private String tagline;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(columnDefinition = "TEXT")
    private String keywords;

    private String summary;

    private String storyIntro;

    @Column(columnDefinition = "TEXT")
    private String storyBody;

    private Long viewCount = 0L;

    private String thumbnailUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

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

    public void changeSummary(String summary) {
        this.summary = summary;
    }

    public void changeStoryIntro(String storyIntro) {
        this.storyIntro = storyIntro;
    }

    public void changeStoryBody(String storyBody) {
        this.storyBody = storyBody;
    }

    public void changeThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void changeViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public void classify(Kingdom kingdom, PersonType personType, String personName) {
        this.kingdom = kingdom;
        this.personType = personType;
        this.personName = personName;
    }
}