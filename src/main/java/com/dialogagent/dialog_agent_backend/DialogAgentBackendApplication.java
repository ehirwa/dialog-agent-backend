package com.dialogagent.dialog_agent_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dialogagent.dialog_agent_backend.repository") // Specify JPA repository package
public class DialogAgentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DialogAgentBackendApplication.class, args);
    }
}

