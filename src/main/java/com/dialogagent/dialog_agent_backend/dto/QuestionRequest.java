package com.dialogagent.dialog_agent_backend.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequest {
    @NotBlank
    private String subject;

    @NotBlank
    private String question;
}
