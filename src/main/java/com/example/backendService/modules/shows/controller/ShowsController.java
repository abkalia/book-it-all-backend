package com.example.backendService.modules.shows.controller;

import com.example.backendService.modules.shows.model.ShowsResponseBody;
import com.example.backendService.modules.shows.service.ShowsService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShowsController{
    private static final Logger logger = LoggerFactory.getLogger(ShowsController.class);

    private final ShowsService showsService;

    public ShowsController(ShowsService showsService) {
        this.showsService = showsService;
    }

    @GetMapping(value = "/get-shows")
    private ShowsResponseBody getShow(
            @RequestHeader("X-BOOKING-SESSION-ID") String sessionID
    ) {
        logger.info("Session [{}] Initated for get shows",sessionID);
        return showsService.getShows(sessionID);
    }
}
