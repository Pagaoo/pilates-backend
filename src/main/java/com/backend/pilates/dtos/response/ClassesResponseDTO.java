package com.backend.pilates.dtos.response;

import java.time.Instant;

public record ClassesResponseDTO(Long id, Long professor_id, Long day_id, Long hour_id, Instant created_at, Instant updated_at) {
}
