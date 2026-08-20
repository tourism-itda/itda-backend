package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentFactCheck;

public record FactCheckResponse(
        @JsonProperty("content_fact_check_id") Long contentFactCheckId,
        String topic,
        String fact,
        String fiction,
        @JsonProperty("sort_order") Integer sortOrder
) {
    public static FactCheckResponse from(ContentFactCheck factCheck) {
        return new FactCheckResponse(
                factCheck.getId(),
                factCheck.getTopic(),
                factCheck.getFact(),
                factCheck.getFiction(),
                factCheck.getSortOrder()
        );
    }
}
