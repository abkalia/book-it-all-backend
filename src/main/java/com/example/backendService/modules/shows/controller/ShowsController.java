package com.example.backendService.modules.shows.controller;

import com.example.backendService.modules.shows.model.ShowByIDResponseBody;
import com.example.backendService.modules.shows.model.ShowSearchResult;
import com.example.backendService.modules.shows.service.ShowsService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowsController {
    private static final Logger logger = LoggerFactory.getLogger(ShowsController.class);

    private final ShowsService showsService;

    public ShowsController(ShowsService showsService) {
        this.showsService = showsService;
    }

    @GetMapping(value = "/shows/search")
    public List<ShowSearchResult> getShow(
            @RequestHeader("x-booking-session-id") String sessionID,
            @RequestParam(name = "query") String searchQuery
    ) {
        logger.info("SessionID [{}] Triggered for Get Shows", sessionID);
        return showsService.getShows(sessionID,searchQuery);
    }

    @GetMapping(value = "/show-id")
    public ShowByIDResponseBody getShow(
            @RequestHeader("x-booking-session-id") String sessionID,
            @RequestParam(name = "id") int showID
    ) {
        logger.info("SessionID [{}] Triggered for Get Shows by ID", sessionID);
        return showsService.getShowByID(sessionID,showID);
    }
}