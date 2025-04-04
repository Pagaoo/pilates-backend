package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.RolesRequestDTO;
import com.backend.pilates.dtos.response.RolesResponseDTO;
import com.backend.pilates.services.RolesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolesController {
    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public ResponseEntity<RolesResponseDTO> create(@RequestBody @Valid RolesRequestDTO rolesRequestDTO) {
        RolesResponseDTO createRole = rolesService.createRole(rolesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRole);
    }
}
