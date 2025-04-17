package com.backend.pilates.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record StudentResponseDTO(Long id,
                                 String firstName,
                                 String lastName,
                                 String phone,
                                 @JsonProperty Boolean isActive,
                                 @JsonProperty Instant createdAt,
                                 @JsonProperty Instant updatedAt) {
}
