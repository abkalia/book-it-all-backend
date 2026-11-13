package com.example.backendService.modules.shows.client;

import com.example.backendService.filter.RestClientLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class TvMazeClientConfig {
    @Bean
    public RestClient tvMazeRestClient(@Value("${tv-maze-service.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .requestInterceptor(new RestClientLoggingInterceptor())
                .defaultHeader("Accept", "application/json")
                .build();
    }
}