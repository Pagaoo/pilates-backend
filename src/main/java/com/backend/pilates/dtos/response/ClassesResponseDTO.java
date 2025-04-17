package com.backend.pilates.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ClassesResponseDTO(Long id, Long professor_id, Long day_id, Long hour_id, @JsonProperty Instant createdAt, @JsonProperty Instant updatedAt) {
}
