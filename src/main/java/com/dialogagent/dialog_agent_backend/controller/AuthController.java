package com.dialogagent.dialog_agent_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dialogagent.dialog_agent_backend.config.JwtUtils;
import com.dialogagent.dialog_agent_backend.dto.UserDto;
import com.dialogagent.dialog_agent_backend.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        if (userService.isUserExists(userDto.getUsername(), userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "User with this username or email already exists."));
        }

        userService.registerUser(userDto);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        return userService.validateUser(userDto.getUsername(), userDto.getPassword())
                .map(user -> {
                    String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
                    return ResponseEntity.ok(new JwtResponse(true, "Login successful", "Bearer " + token));
                })
                .orElseGet(() -> ResponseEntity.status(401)
                        .body(new JwtResponse(false, "Invalid credentials", null)));
    }
    
    
    
}
