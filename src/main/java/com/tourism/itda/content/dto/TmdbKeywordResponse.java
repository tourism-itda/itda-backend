package com.tourism.itda.content.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TmdbKeywordResponse {

    private Long id;

    private List<KeywordDto> keywords;


    @Getter
    @Setter
    public static class KeywordDto {
        private Long id;
        private String name;
    }
}
