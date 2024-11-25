```markdown
# Dialog Agent Backend

The Dialog Agent Backend is a Spring Boot-based REST API that facilitates AI-driven interactions using OpenAI's GPT models. The backend supports multiple user roles (e.g., STUDENT, ENTERTAINER, GENERAL) with secured authentication and authorization using JSON Web Tokens (JWT).

## Features

- **User Authentication**: Register, log in, and manage user sessions securely with JWT.
- **Role-Based Access Control**: Role-based permissions for accessing specific endpoints.
- **AI-Powered Question Answering**: Leverages OpenAI's GPT models to answer educational questions dynamically.
- **Secure API Communication**: Environment-variable-based API key management with Spring Security for endpoint protection.

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the backend services.
- **Spring Security**: Security framework for authentication and authorization.
- **JWT (JSON Web Tokens)**: Used for secure session management.
- **OpenAI GPT-3.5-Turbo**: For AI-driven question answering.
- **PostgreSQL**: Database for storing user information.
- **Maven**: Dependency management.
- **RestTemplate**: For making external API calls.

## Prerequisites

Before running the application, ensure you have:

- **Java 17 or later** installed.
- **PostgreSQL** running and accessible.
- **OpenAI API Key** (configured via environment variables or `application.properties`).

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ehirwa/dialog-agent-backend.git
   cd dialog-agent-backend
   ```

2. Set up your database:
   - Create a PostgreSQL database named `dialogdb`.
   - Update your database credentials in `src/main/resources/application.properties`.

3. Configure your OpenAI API Key:
   - Add your OpenAI API key to the environment variables:
     ```bash
     export OPENAI_API_TOKEN=your_actual_api_key
     ```
   - Alternatively, update `application.properties`:
     ```properties
     openai.api.token=your_actual_api_key
     ```

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

6. Access the application:
   - The backend will run on `http://localhost:8080`.

## API Endpoints

### Authentication Endpoints

- **POST** `/api/v1/auth/register`: Register a new user.
  ```json
  {
    "username": "testuser",
    "email": "test@example.com",
    "password": "securePassword",
    "role": "STUDENT"
  }
  ```

- **POST** `/api/v1/auth/login`: Log in and receive a JWT token.
  ```json
  {
    "username": "testuser",
    "password": "securePassword"
  }
  ```

### Educational Questions Endpoint

- **POST** `/api/v1/education/ask`: Ask a question and receive an AI-generated response.
  - **Authorization**: Requires `STUDENT` role and a valid JWT token.
  ```json
  {
    "subject": "Physics",
    "question": "What is Newton's first law of motion?"
  }
  ```

### Example Response
```json
{
  "answer": "Newton's first law of motion states that an object at rest stays at rest and an object in motion stays in motion unless acted upon by an external force.",
  "confidence": 1.0,
  "timeTakenMs": 215
}
```

## Environment Variables

| Variable Name      | Description                  |
|--------------------|------------------------------|
| `OPENAI_API_TOKEN` | OpenAI API key for GPT calls |
| `DATABASE_URL`     | PostgreSQL database URL      |

## Security

- All sensitive information is stored in environment variables or `application.properties`.
- JWT is used for secure communication between the backend and the client.

## Folder Structure

```
src/
├── main/
│   ├── java/com/dialogagent/dialog_agent_backend/
│   │   ├── config/               # Security and JWT configurations
│   │   ├── controller/           # REST API controllers
│   │   ├── dto/                  # Data Transfer Objects
│   │   ├── model/                # Entity models
│   │   ├── repository/           # Repositories for database interaction
│   │   ├── service/              # Business logic and external API clients
│   └── resources/
│       ├── application.properties # Configuration file
│       └── static/               # Static resources (if any)
```

## Testing

To test the backend, you can use **Postman** or **cURL**. Ensure that the backend is running and reachable on `http://localhost:8080`.

### Example cURL Command
```bash
curl -X POST http://localhost:8080/api/v1/education/ask \
-H "Authorization: Bearer YOUR_JWT_TOKEN" \
-H "Content-Type: application/json" \
-d '{
  "subject": "Physics",
  "question": "What is Newton\'s first law of motion?"
}'
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add some feature"`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
```

