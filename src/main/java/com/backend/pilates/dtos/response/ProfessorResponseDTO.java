package com.backend.pilates.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ProfessorResponseDTO(Long id,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   Long role_id,
                                   String professorBio,
                                   String professorSpecialization,
                                   @JsonProperty Instant createdAt,
                                   @JsonProperty Instant updatedAt) {
}
