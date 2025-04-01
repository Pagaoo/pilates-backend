package com.backend.pilates.dtos.response;

import java.time.Instant;

public record ProfessorResponseDTO(Long id,
                                   String first_name,
                                   String last_name,
                                   String email,
                                   Long role_id,
                                   Instant created_at,
                                   Instant updated_at) {
}
