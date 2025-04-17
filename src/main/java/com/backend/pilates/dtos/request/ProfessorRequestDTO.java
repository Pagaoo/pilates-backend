package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDTO(@NotBlank String firstName,
                                  @NotBlank String lastName,
                                  @NotBlank @Email String email,
                                  @NotBlank String password,
                                  @NotNull Long role_id,
                                  @NotBlank String professorBio,
                                  @NotBlank String professorSpecialization) {
}
