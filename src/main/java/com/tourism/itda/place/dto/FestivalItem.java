package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FestivalItem(
        @JsonProperty("contentid") String contentId,
        @JsonProperty("title") String title,
        @JsonProperty("addr1") String addr1,
        @JsonProperty("firstimage") String firstImage,
        @JsonProperty("eventstartdate") String eventStartDate,
        @JsonProperty("eventenddate") String eventEndDate,
        @JsonProperty("mapx") String mapX,
        @JsonProperty("mapy") String mapY
) {}
