package com.backend.pilates.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record EnrollmentResponseDTO(Long id, Long student_id, Long class_id, @JsonProperty Instant createdAt, @JsonProperty Instant updatedAt) {
}
