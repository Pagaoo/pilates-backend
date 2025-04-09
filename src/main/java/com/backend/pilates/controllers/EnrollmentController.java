package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.EnrollmentRequestDTO;
import com.backend.pilates.dtos.response.EnrollmentResponseDTO;
import com.backend.pilates.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> createEnrollment(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.createEnrollment(enrollmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollments);
    }
}
