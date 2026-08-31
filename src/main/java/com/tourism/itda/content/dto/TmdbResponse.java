package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TmdbResponse {

    private Long id;

    // 영화
    private String title;

    // TV
    private String name;

    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    // 영화
    @JsonProperty("release_date")
    private String releaseDate;

    // TV
    @JsonProperty("first_air_date")
    private String firstAirDate;

    private String tagline;
}