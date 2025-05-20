package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.EnrollmentRequestDTO;
import com.backend.pilates.dtos.response.EnrollmentResponseDTO;
import com.backend.pilates.services.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/enrollments")
@Tag(name = "Enrollments", description = "Enrollments managements operations")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(
            summary = "Create new enrollments connecting a class to students",
            operationId = "addEnrollments",
            description = """
                    Performs a creation operation to link a student entity to a class entity.
                    
                    Business Rules:
                    - One or more students can be linked to one class;
                    
                    System Behaviour:
                    - Generates unique Enrollment id;
                    - Records creation timestamp;
                    - Confirmation response (body response)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Enrollment created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "student_id": "123",
                                                    "class_id": "123",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro while creating enrollment (error response)")
            })
    @PostMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> addStudentsToClass(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.addStudentToClass(enrollmentRequestDTO);
        URI location = URI.create("/v1/enrollments");
        return ResponseEntity.created(location).body(enrollments);
    }

    @Operation(
            summary = "Get all enrollments from the database",
            operationId = "getAllEnrollments",
            description =
                    """
                    Performs a get list of all enrollments in the database.
                    
                    System behavior:
                    - List all enrollments in the database;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all enrollments",
                            content = @Content(
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "student_id": "123",
                                                    "class_id": "123",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found any enrollments to return"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllStudentsEnrollments() {
        enrollmentService.findAllEnrollments();
        return ResponseEntity.ok(enrollmentService.findAllEnrollments());
    }

    @Operation(
            summary = "Get a enrollments from the database by the id",
            operationId = "getEnrollmentsById",
            description =
                    """
                    Performs a get of a enrollments in the database by the id.
                    
                    System behavior:
                    - List a enrollments by the id;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List a enrollments by the id",
                            content = @Content(
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "student_id": "123",
                                                    "class_id": "123",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found the enrollments by the id provided"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getStudentEnrollmentById(@PathVariable Long id) {
        EnrollmentResponseDTO enrollmentDTO = enrollmentService.findEnrollmentById(id);
        return ResponseEntity.ok(enrollmentDTO);
    }

    @Operation(
            summary = "Delete student from enrollments",
            operationId = "deleteStudentFromEnrollments",
            description =
                    """
                    Performs a delete of existing students from the enrollment.
                    
                    System behavior:
                    - delete existing students from the enrollment;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Students deleted successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error deleting students, student not found"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @DeleteMapping
    public ResponseEntity<Void> removeStudentsFromClass(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        enrollmentService.removeStudentFromClass(enrollmentRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
