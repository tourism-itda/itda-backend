package com.tourism.itda.explore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.explore.enums.Kingdom;
import lombok.Getter;

@Getter
public class KingdomDetailResponse {

    private final String kingdom;
    private final String name;

    @JsonProperty("time_period")
    private final String timePeriod;

    private final String description;

    @JsonProperty("image_url")
    private final String imageUrl;

    public KingdomDetailResponse(
            Kingdom kingdom,
            String name,
            String timePeriod,
            String description,
            String imageUrl
    ) {
        this.kingdom = kingdom.name();
        this.name = name;
        this.timePeriod = timePeriod;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}