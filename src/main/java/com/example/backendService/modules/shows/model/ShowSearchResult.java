package com.example.backendService.modules.shows.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowSearchResult(
        Double score,
        ShowsResponseBody show
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ShowsResponseBody(
            Long id,
            String url,
            String name,
            String type,
            String language,
            List<String> genres,
            Rating rating,
            Image image,
            String summary
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Rating(
                Double average
        ) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Image(
                String medium,
                String original
        ) {}
    }
}