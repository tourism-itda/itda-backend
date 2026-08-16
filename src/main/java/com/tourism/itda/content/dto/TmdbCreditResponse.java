package com.tourism.itda.content.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TmdbCreditResponse {

    private List<CastDto> cast;


    @Getter
    public static class CastDto {

        private String name;

        private String character;
    }
}