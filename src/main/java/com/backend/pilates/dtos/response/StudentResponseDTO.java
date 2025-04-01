package com.backend.pilates.dtos.response;

import java.time.Instant;

public record StudentResponseDTO(Long id,
                                 String first_name,
                                 String last_name,
                                 String phone,
                                 Boolean is_active,
                                 Instant created_at,
                                 Instant updated_at) {
}
