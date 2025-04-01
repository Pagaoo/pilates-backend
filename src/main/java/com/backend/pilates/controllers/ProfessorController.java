package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.ProfessorRequestDTO;
import com.backend.pilates.dtos.response.ProfessorResponseDTO;
import com.backend.pilates.model.Professor;
import com.backend.pilates.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> create(@Valid @RequestBody ProfessorRequestDTO requestDTO) {
        ProfessorResponseDTO createProfessor = professorService.createProfessor(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProfessor);
    }
}
