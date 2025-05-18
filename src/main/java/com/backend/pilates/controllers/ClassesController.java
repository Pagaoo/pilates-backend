package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.ClassesRequestDTO;
import com.backend.pilates.dtos.response.ClassesResponseDTO;
import com.backend.pilates.dtos.response.ProfessorResponseDTO;
import com.backend.pilates.model.Classes;
import com.backend.pilates.services.ClassesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/classes")
public class ClassesController {
    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @Operation(
            summary = "Create a new class",
            description = """
                    Performs a creation operation of a new class entity in the database.
                  
                    Business Rules:
                    - professor id must be unique;
                    - Create a new class entity in the database;
                    - Confirmation response (body response);
                    
                    System Behavior:
                    - Generates unique class id;
                    - Records creation timestamp;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "class created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = ClassesResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "professor_id": "12312",
                                                    "day_id": "12312",
                                                    "hour_id": "12312",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro while creating class (error response)")
            })
    @PostMapping
    public ResponseEntity<ClassesResponseDTO> createClass(@RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        ClassesResponseDTO newClass = classesService.createClass(classesRequestDTO);
        URI location = URI.create("/v1/classes/" + newClass.id());
        return ResponseEntity.created(location).body(newClass);
    }

    @GetMapping
    public ResponseEntity<List<ClassesResponseDTO>> findAllClasses() {
        return ResponseEntity.ok(classesService.findAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassesResponseDTO> findClassById(@PathVariable Long id) {
        return ResponseEntity.ok(classesService.findClassById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClassesResponseDTO> updateClass(@PathVariable Long id, @RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        return ResponseEntity.ok(classesService.updateClass(id, classesRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
