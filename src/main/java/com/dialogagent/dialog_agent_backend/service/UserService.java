package com.dialogagent.dialog_agent_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dialogagent.dialog_agent_backend.dto.UserDto;
import com.dialogagent.dialog_agent_backend.model.Role;
import com.dialogagent.dialog_agent_backend.model.User;
import com.dialogagent.dialog_agent_backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUserExists(String username, String email) {
        return userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent();
    }

    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        user.setEmail(userDto.getEmail());
        // user.setRole(Role.valueOf(userDto.getRole().toUpperCase())); // Convert string to enum
        user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));
        userRepository.save(user);
    }
    

    public Optional<User> validateUser(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }
}
