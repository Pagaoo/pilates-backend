package com.backend.pilates.dtos.response;

import java.time.Instant;

public record EnrollmentResponseDTO(Long id, Long student_id, Long class_id, Instant created_at, Instant updated_at) {
}
