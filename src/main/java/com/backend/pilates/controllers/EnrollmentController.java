package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.EnrollmentRequestDTO;
import com.backend.pilates.dtos.response.EnrollmentResponseDTO;
import com.backend.pilates.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> createEnrollment(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.addStudentToClass(enrollmentRequestDTO);
        URI location = URI.create("/v1/enrollments");
        return ResponseEntity.created(location).body(enrollments);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeStudentFromClass(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        enrollmentService.removeStudentFromClass(enrollmentRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        enrollmentService.findAllEnrollments();
        return ResponseEntity.ok(enrollmentService.findAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long id) {
        EnrollmentResponseDTO enrollmentDTO = enrollmentService.findEnrollmentById(id);
        return ResponseEntity.ok(enrollmentDTO);
    }
}
