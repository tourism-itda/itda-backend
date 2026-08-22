package com.tourism.itda.content.dto;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContentResponse {

    private Long id;
    private Long tmdbId;
    private String title;
    private Integer releaseYear;
    private String mediaType;
    private String posterUrl;
    private String thumbnailUrl;
    private String tagline;
    private String overview;
    private String keywords;
    private String summary;
    private String storyIntro;
    private String storyBody;
    private Long viewCount;
    private Kingdom kingdom;
    private PersonType personType;
    private LocalDateTime createdAt;

    public static ContentResponse from(Content content) {
        return ContentResponse.builder()
                .id(content.getId())
                .tmdbId(content.getTmdbId())
                .title(content.getTitle())
                .releaseYear(content.getReleaseYear())
                .mediaType(content.getMediaType())
                .posterUrl(content.getPosterUrl())
                .thumbnailUrl(content.getThumbnailUrl())
                .tagline(content.getTagline())
                .overview(content.getOverview())
                .keywords(content.getKeywords())
                .summary(content.getSummary())
                .storyIntro(content.getStoryIntro())
                .storyBody(content.getStoryBody())
                .viewCount(content.getViewCount())
                .kingdom(content.getKingdom())
                .personType(content.getPersonType())
                .createdAt(content.getCreatedAt())
                .build();
    }
}