package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.UserRequestDTO;
import com.backend.pilates.dtos.response.UserResponseDTO;
import com.backend.pilates.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("v1/users")
@Tag(name = "Students", description = "Students managements operations")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create a new user",
            operationId = "addUser",
            description = """
                    Performs a creation operation of a new user entity in the database.
                  
                    Business Rules:
                    - email must be unique across the system;
                    - Create a new user entity in the database;
                    
                    System Behavior:
                    - Generates unique Professor id;
                    - Encrypt password;
                    - Records creation timestamp;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = UserResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "firstName": "John",
                                                    "lastName": "Doe",
                                                    "email": "email@example.com",
                                                    "role_id": 1,
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro while creating user (error response)")
            })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        URI location = URI.create("/v1/users/" + createdUser.id());
        return ResponseEntity.created(location).body(createdUser);
    }
}
