package com.backend.pilates.dtos.response;

import com.backend.pilates.enums.RolesEnum;

import java.time.Instant;

public record RolesResponseDTO(Long id,
                               RolesEnum name,
                               String description,
                               Instant created_at,
                               Instant updated_at) {
}
