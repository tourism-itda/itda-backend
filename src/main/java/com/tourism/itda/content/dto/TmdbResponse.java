package com.tourism.itda.content.dto;

import lombok.Getter;

@Getter
public class TmdbResponse {

    private Long id;

    private String title;

    private String overview;

    private String posterPath;

    private String releaseDate;

    private String tagline;

}