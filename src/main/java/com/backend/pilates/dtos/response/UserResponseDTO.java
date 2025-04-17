package com.backend.pilates.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record UserResponseDTO(
                              Long id,
                              String firstName,
                              String lastName,
                              String email,
                              String password,
                              Long role_id,
                              @JsonProperty Instant createdAt,
                              @JsonProperty Instant updatedAt) {
}
