package com.example.backendService.modules.shows.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowByIDResponseBody(
        Long id,
        String url,
        String name,
        String type,
        List<String> genres,
        Rating rating,
        Image image
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Rating(String average){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Image(
            String original,
            String summary
    ){}
}