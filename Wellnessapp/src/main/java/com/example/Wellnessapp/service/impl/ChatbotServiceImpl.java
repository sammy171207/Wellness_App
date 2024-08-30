package com.example.Wellnessapp.service.impl;

import com.example.Wellnessapp.service.ChatbotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ChatbotServiceImpl implements ChatbotService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ChatbotServiceImpl() {
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    @Override
    public ResponseEntity<String> generateContent(String prompt) {
        String url = String.format("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s", apiKey);

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Prepare body
        String body = String.format("{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}", prompt);

        // Create the HttpEntity object
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        }
    }
}