package com.example.backendService.modules.shows.model;

public record ShowsResponseBody(
    Integer id,
    String Name,
    String email,
    String status,
    String sessionID
){ }
