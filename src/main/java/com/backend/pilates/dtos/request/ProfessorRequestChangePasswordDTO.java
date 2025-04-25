package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfessorRequestChangePasswordDTO(@NotBlank String currentPassword,
                                                @NotBlank @Size(min = 8) String newPassword,
                                                @NotBlank String confirmNewPassword) {
}
