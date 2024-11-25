package com.dialogagent.dialog_agent_backend.controller;

public class JwtResponse extends ApiResponse {
    private final String token;

    public JwtResponse(boolean success, String message, String token) {
        super(success, message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
