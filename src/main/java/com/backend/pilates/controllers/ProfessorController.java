package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.ProfessorRequestDTO;
import com.backend.pilates.dtos.request.ProfessorRequestUpdateDetailsDTO;
import com.backend.pilates.dtos.response.ProfessorResponseDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.model.Professor;
import com.backend.pilates.services.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> getAllProfessors() {
        List<ProfessorResponseDTO> professorResponseDTOList = professorService.findAllProfessors();
        return ResponseEntity.status(HttpStatus.OK).body(professorResponseDTOList);
    }

    @Operation(
            summary = "Get a professor by his id",
            description =
                    """
                    Performs a search to return one professor by his id.
                    
                    System behavior:
                    - Get a professor by his id;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Show the professor found by the id"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found the professor by the id provided"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> getProfessorById(@PathVariable Long id) {
        ProfessorResponseDTO professor = professorService.getProfessorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @Operation(
            summary = "Update professor information",
            operationId = "patchProfessorInfo ",
            description =
                    """
                    Performs a update of an existing professor in the database.
                    
                    System behavior:
                    - Update an existing professor in the database using the id;
                    - Only the first name, last name, professor bio and professor specialization can be updated via this operation;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Professor updated successfully",
                            content = @Content(
                                    schema = @Schema(implementation = ProfessorResponseDTO.class),
                                    examples = @ExampleObject(
                                            name = "success",
                                            value =  """
                                                {
                                                    "id": 123345,
                                                    "first_name": "John",
                                                    "last_name": "Doe",
                                                    "email": "email@example.com",
                                                    "role_id": 1,
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error updating professor, professor not found"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @PatchMapping("/{id}/information")
    public ResponseEntity<ProfessorResponseDTO> updateStudent(
            @Parameter(description = "Professor id", example = "123")
            @PathVariable Long id,
            @RequestBody @Valid ProfessorRequestUpdateDetailsDTO professorRequestUpdateDetailsDTO) {
        ProfessorResponseDTO professor = professorService.updateProfessorDetailsById(id, professorRequestUpdateDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }
}
