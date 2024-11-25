package com.dialogagent.dialog_agent_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dialogagent.dialog_agent_backend.dto.QuestionRequest;
import com.dialogagent.dialog_agent_backend.dto.QuestionResponse;
import com.dialogagent.dialog_agent_backend.service.OpenAIClient;

@RestController
@RequestMapping("/api/v1/education")
public class EducationController {

    private final OpenAIClient openAIClient;

    public EducationController(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    @PostMapping("/ask")
    public ResponseEntity<QuestionResponse> askQuestion(@RequestBody QuestionRequest request) {
        try {
            QuestionResponse response = openAIClient.getAnswer(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log and handle the exception
            return ResponseEntity.status(500).body(
                new QuestionResponse("Failed to fetch answer", 0.0, 0));
        }
    }
}
