package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KeywordSearchItem(
        @JsonProperty("contentid") String contentId,
        @JsonProperty("contenttypeid") String contentTypeId,
        @JsonProperty("title") String title,
        @JsonProperty("addr1") String addr1,
        @JsonProperty("addr2") String addr2,
        @JsonProperty("areacode") String areaCode,
        @JsonProperty("sigungucode") String sigunguCode,
        @JsonProperty("cat1") String cat1,
        @JsonProperty("cat2") String cat2,
        @JsonProperty("cat3") String cat3,
        @JsonProperty("firstimage") String firstImage,
        @JsonProperty("firstimage2") String firstImage2,
        @JsonProperty("cpyrhtDivCd") String cpyrhtDivCd,
        @JsonProperty("mapx") String mapX,
        @JsonProperty("mapy") String mapY,
        @JsonProperty("mlevel") String mLevel,
        @JsonProperty("tel") String tel,
        @JsonProperty("booktour") String bookTour,
        @JsonProperty("createdtime") String createdTime,
        @JsonProperty("modifiedtime") String modifiedTime
) {}
