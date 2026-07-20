package com.tourism.itda.place.controller;

import com.tourism.itda.place.dto.FestivalItem;
import com.tourism.itda.place.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @GetMapping("/festivals")
    public List<FestivalItem> searchFestivals(
            @RequestParam String eventStartDate,
            @RequestParam(required = false) String areaCode,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows
    ) {
        return festivalService.searchFestivals(eventStartDate, areaCode, pageNo, numOfRows);
    }
}
