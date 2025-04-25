package com.backend.pilates.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ProfessorResponseChangedPasswordDTO(Long id,
                                                  String password,
                                                  String email,
                                                  @JsonProperty Instant createdAt,
                                                  @JsonProperty Instant updatedAt) {
}
