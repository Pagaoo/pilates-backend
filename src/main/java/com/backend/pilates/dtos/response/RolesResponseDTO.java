package com.backend.pilates.dtos.response;

import com.backend.pilates.enums.RolesEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record RolesResponseDTO(Long id,
                               RolesEnum name,
                               String description,
                               @JsonProperty("created_at") Instant createdAt,
                               @JsonProperty("updated_at") Instant updatedAt) {
}
