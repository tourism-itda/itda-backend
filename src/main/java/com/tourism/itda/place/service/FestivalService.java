package com.tourism.itda.place.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.global.client.PublicDataClient;
import com.tourism.itda.place.dto.FestivalItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FestivalService {

    private final PublicDataClient publicDataClient;
    private final ObjectMapper objectMapper;

    public List<FestivalItem> searchFestivals(String eventStartDate, String areaCode, int pageNo, int numOfRows) {
        try {
            Map<String, String> params = new java.util.HashMap<>();
            params.put("eventStartDate", eventStartDate);
            params.put("pageNo", String.valueOf(pageNo));
            params.put("numOfRows", String.valueOf(numOfRows));
            if (areaCode != null) params.put("areaCode", areaCode);

            String raw = publicDataClient.get("/searchFestival2", params);
            JsonNode items = objectMapper.readTree(raw)
                    .path("response").path("body").path("items").path("item");

            if (items.isMissingNode() || items.isNull()) return Collections.emptyList();

            return objectMapper.readerForListOf(FestivalItem.class).readValue(items);
        } catch (Exception e) {
            throw new RuntimeException("축제 정보 조회 실패", e);
        }
    }
}
