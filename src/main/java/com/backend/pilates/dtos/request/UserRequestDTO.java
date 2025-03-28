package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(@NotBlank String first_name,
                             @NotBlank String last_name,
                             @NotBlank @Email String email,
                             @NotBlank String password,
                             @NotNull Long role_id) {
}
