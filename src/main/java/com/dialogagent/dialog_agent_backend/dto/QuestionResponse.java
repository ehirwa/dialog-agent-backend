package com.dialogagent.dialog_agent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private String answer;
    private double confidence;
    private long timeTakenMs;
}
