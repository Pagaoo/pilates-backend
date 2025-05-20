package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.RolesRequestDTO;
import com.backend.pilates.dtos.response.RolesResponseDTO;
import com.backend.pilates.services.RolesService;
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
@RequestMapping("v1/roles")
@Tag(name = "Roles", description = "Roles managements operations")
public class RolesController {
    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Operation(
            summary = "Create a new role",
            operationId = "addRoles",
            description = """
                    Performs a creation operation of a new role entity in the database.
                  
                    Business Rules:
                    - Create a new role entity in the database;

                    
                    System Behavior:
                    - Generates unique role id;
                    - Records creation timestamp;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Role created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = RolesResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "name": "example",
                                                    "description": "example"
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro while creating roles (error response)")
            })
    @PostMapping
    public ResponseEntity<RolesResponseDTO> create(@RequestBody @Valid RolesRequestDTO rolesRequestDTO) {
        RolesResponseDTO createRole = rolesService.createRole(rolesRequestDTO);
        URI location = URI.create("v1/roles/" + createRole.id());
        return ResponseEntity.created(location).body(createRole);
    }
}
