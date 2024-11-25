// package com.dialogagent.dialog_agent_backend.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import com.dialogagent.dialog_agent_backend.dto.QuestionRequest;
// import com.dialogagent.dialog_agent_backend.dto.QuestionResponse;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @Service
// public class HuggingFaceClient {

//     private final RestTemplate restTemplate;

//     @Value("${huggingface.api.url}")
//     private String huggingFaceApiUrl;

//     @Value("${huggingface.api.token}")
//     private String huggingFaceApiToken;

//     public HuggingFaceClient(RestTemplate restTemplate) {
//         this.restTemplate = restTemplate;
//     }

//     public QuestionResponse getAnswer(QuestionRequest request) {
//         long startTime = System.currentTimeMillis();

//         try {
//             // Build payload
//             String payload = String.format(
//                 "{\"inputs\": {\"question\": \"%s\", \"context\": \"%s\"}}",
//                 request.getQuestion(),
//                 request.getSubject()
//             );

//             // Set headers
//             HttpHeaders headers = new HttpHeaders();
//             headers.set("Authorization", "Bearer " + huggingFaceApiToken);
//             headers.setContentType(MediaType.APPLICATION_JSON);

//             // Send request
//             HttpEntity<String> entity = new HttpEntity<>(payload, headers);
//             String response = restTemplate.postForObject(huggingFaceApiUrl, entity, String.class);

//             // Log raw response for debugging
//             System.out.println("Raw Response: " + response);

//             // Parse and validate response
//             JsonNode jsonNode = new ObjectMapper().readTree(response);
//             if (!jsonNode.has("answer")) {
//                 throw new RuntimeException("Unexpected response format: Missing 'answer' key.");
//             }

//             String answer = jsonNode.get("answer").asText();
//             long endTime = System.currentTimeMillis();

//             // Return the response
//             return new QuestionResponse(answer, 1.0, endTime - startTime);

//         } catch (Exception e) {
//             throw new RuntimeException("Failed to fetch answer from Hugging Face: " + e.getMessage(), e);
//         }
//     }
// }
