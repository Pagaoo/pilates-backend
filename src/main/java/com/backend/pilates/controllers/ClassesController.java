package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.ClassesRequestDTO;
import com.backend.pilates.dtos.response.ClassesResponseDTO;
import com.backend.pilates.model.Classes;
import com.backend.pilates.services.ClassesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @PostMapping
    public ResponseEntity<ClassesResponseDTO> saveClass(@RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        ClassesResponseDTO newClass = classesService.createClass(classesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClass);
    }
}
