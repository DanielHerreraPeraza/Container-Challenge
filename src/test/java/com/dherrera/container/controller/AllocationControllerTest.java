package com.dherrera.container.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class AllocationControllerTest {

    private final String ENDPOINT = "http://localhost:8080/api/allocations";

    @Test
    void statusCode() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .POST(HttpRequest.BodyPublishers.ofString("{\"capacity\":1150,\"hours\":1}"))
                .header("content-type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

    @Test
    void checkContent() throws IOException, InterruptedException {
        String expectedResult = "{\"Output\":[{\"region\":\"New York\",\"total_cost\":\"$10150\",\"machines\":[\"(XLarge, 1)\",\"(Large, 1)\",\"(8XLarge, 7)\"]},{\"region\":\"China\",\"total_cost\":\"$8570\",\"machines\":[\"(XLarge, 1)\",\"(Large, 1)\",\"(8XLarge, 7)\"]},{\"region\":\"India\",\"total_cost\":\"$9520\",\"machines\":[\"(Large, 3)\",\"(8XLarge, 7)\"]}]}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .POST(HttpRequest.BodyPublishers.ofString("{\"capacity\":1150,\"hours\":1}"))
                .header("content-type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        assertEquals(expectedResult, response.body());
    }
}