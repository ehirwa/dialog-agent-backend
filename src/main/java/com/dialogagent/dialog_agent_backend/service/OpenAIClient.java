package com.dialogagent.dialog_agent_backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dialogagent.dialog_agent_backend.dto.QuestionRequest;
import com.dialogagent.dialog_agent_backend.dto.QuestionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenAIClient {

    @Value("${openai.api.url}")
    private String openAiApiUrl;

    @Value("${openai.api.token}")
    private String openAiApiToken;

    private final RestTemplate restTemplate;

    public OpenAIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public QuestionResponse getAnswer(QuestionRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            // Build the payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("model", "gpt-3.5-turbo");
            payload.put("messages", new Object[] {
                Map.of("role", "system", "content", "You are a helpful assistant."),
                Map.of("role", "user", "content", request.getQuestion())
            });
            payload.put("max_tokens", 100);
            payload.put("temperature", 0.7);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + openAiApiToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            // Make the POST call
            String response = restTemplate.postForObject(openAiApiUrl, entity, String.class);

            // Parse the response
            JsonNode jsonNode = new ObjectMapper().readTree(response);
            String answer = jsonNode.get("choices").get(0).get("message").get("content").asText();

            long endTime = System.currentTimeMillis();
            return new QuestionResponse(answer.trim(), 1.0, endTime - startTime);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch answer from OpenAI", e);
        }
    }
}
