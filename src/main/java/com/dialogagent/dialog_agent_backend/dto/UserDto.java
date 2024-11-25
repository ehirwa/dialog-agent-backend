package com.dialogagent.dialog_agent_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String role; // Must match Role enum (e.g., STUDENT, ENTERTAINER, GENERAL)

    @NotBlank
    private String context; // Multi-context identifier
}
