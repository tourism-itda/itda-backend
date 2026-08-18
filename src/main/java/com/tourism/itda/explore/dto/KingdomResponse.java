package com.tourism.itda.explore.dto;

import com.tourism.itda.explore.enums.Kingdom;
import lombok.Getter;

@Getter
public class KingdomResponse {

    private final String kingdom;
    private final String name;

    public KingdomResponse(Kingdom kingdom, String name) {
        this.kingdom = kingdom.name();
        this.name = name;
    }
}