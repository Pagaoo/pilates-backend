package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestUpdateDetailsDTO(@NotBlank String firstName,
                                               @NotBlank String lastName,
                                               @NotBlank String professorBio,
                                               @NotBlank String professorSpecialization) {
}
