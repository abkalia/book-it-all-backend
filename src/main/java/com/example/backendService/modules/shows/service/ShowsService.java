package com.example.backendService.modules.shows.service;

import com.example.backendService.modules.shows.model.ShowsResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShowsService {

    private static final Logger logger = LoggerFactory.getLogger(ShowsService.class);
    public ShowsResponseBody getShows(String sessionId){
        logger.info("SessionID [{}] Initiated for Get Shows",sessionId);
        return new ShowsResponseBody(
                1,
                "Abhishek Kalia",
                "abhishekkalia@gmail.com",
                "ACTIVE",
                sessionId
        );
    }
}