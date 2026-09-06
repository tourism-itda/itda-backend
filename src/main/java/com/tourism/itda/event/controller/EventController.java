package com.tourism.itda.event.controller;

import com.tourism.itda.event.dto.EventSummaryResponse;
import com.tourism.itda.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /** GET /api/events/upcoming — 인증 불필요. */
    @GetMapping("/upcoming")
    public List<EventSummaryResponse> getUpcoming(@RequestParam(defaultValue = "3") int limit) {
        return eventService.getUpcoming(limit);
    }
}
