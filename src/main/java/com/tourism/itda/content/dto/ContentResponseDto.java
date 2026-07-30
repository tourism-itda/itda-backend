package com.tourism.itda.content.dto;

import com.tourism.itda.content.entity.Content;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentResponseDto {

    private Long id;
    private Long tmdbId;
    private String title;
    private Integer releaseYear;
    private String mediaType;
    private String posterUrl;
    private String tagline;
    private String overview;
    private String keywords;

    public static ContentResponseDto from(Content content) {
        return ContentResponseDto.builder()
                .id(content.getId())
                .tmdbId(content.getTmdbId())
                .title(content.getTitle())
                .releaseYear(content.getReleaseYear())
                .mediaType(content.getMediaType())
                .posterUrl(content.getPosterUrl())
                .tagline(content.getTagline())
                .overview(content.getOverview())
                .keywords(content.getKeywords())
                .build();
    }
}