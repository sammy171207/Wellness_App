package com.example.Wellnessapp.service;

import org.springframework.http.ResponseEntity;

public interface ChatbotService {
    ResponseEntity<String> generateContent(String prompt);
}
