package com.example.Wellnessapp.controller;

import com.example.Wellnessapp.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatbot")
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @GetMapping("/generate")
    public ResponseEntity<String> generate(@RequestParam String prompt) {
        return chatbotService.generateContent(prompt);
    }

}
