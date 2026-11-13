package com.example.backendService.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RestClientLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.info("=========================== HTTP Request Start ===========================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        if (body.length > 0) {
            log.info("Request Body: {}", new String(body, StandardCharsets.UTF_8));
        }
        log.info("==========================================================================");
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        log.info("=========================== HTTP Response Start ==========================");
        log.info("Status Code : {}", response.getStatusCode());
        log.info("Status Text : {}", response.getStatusText());
        log.info("Headers     : {}", response.getHeaders());
        log.info("==========================================================================");
    }
}