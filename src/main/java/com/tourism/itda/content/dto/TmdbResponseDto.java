package com.tourism.itda.content.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TmdbResponseDto {

    private Long id;

    private String title;

    private String overview;

    private String poster_path;

    private String release_date;

    private String tagline;

}