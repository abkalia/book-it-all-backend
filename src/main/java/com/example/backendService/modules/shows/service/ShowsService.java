package com.example.backendService.modules.shows.service;

import com.example.backendService.modules.shows.model.ShowByIDResponseBody;
import com.example.backendService.modules.shows.model.ShowSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ShowsService {
    private static final Logger logger = LoggerFactory.getLogger(ShowsService.class);
    private final RestClient restClient;

    public ShowsService(RestClient tvMazeRestClient) {
        this.restClient = tvMazeRestClient;
    }

    public List<ShowSearchResult> getShows(String sessionId, String searchQuery) {
        logger.info("SessionID [{}] Initiated for Get Shows", sessionId);

        List<ShowSearchResult> results = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/shows")
                        .queryParam("q", searchQuery)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return results;
    }

    public ShowByIDResponseBody getShowByID(String sessionId, int id) {
        logger.info("SessionID [{}] Initiated for Get Show by ID [{}]",sessionId,id);
        try{
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/shows/" + id)
                            .build())
                    .retrieve()
                    .body(ShowByIDResponseBody.class);
        } catch (HttpClientErrorException.NotFound e) {
            logger.info("Show not found");
            throw e;
        } catch (HttpClientErrorException.TooManyRequests e) {
            logger.warn("Rate limited (429) on page ");
            throw e;
        }
    }

    public List<ShowByIDResponseBody> getShowsByPage(int page) {
        try {
            return restClient.get()
                    .uri("/shows?page={page}", page)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ShowByIDResponseBody>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            logger.info("Reached end of pagination at page {}", page);
            return List.of(); // 404 means past the last page
        } catch (HttpClientErrorException.TooManyRequests e) {
            logger.warn("Rate limited (429) on page {}. Consider backing off.", page);
            throw e;
        }
    }
}