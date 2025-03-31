package com.backend.pilates.dtos.request;

import com.backend.pilates.enums.RolesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RolesRequestDTO(
                              @Schema(description = "Papel do usuário no sistema",
                                      example = "ROLE_ADMIN",
                                      allowableValues = {"ROLE_ADMIN", "ROLE_PROFESSOR"})
                              RolesEnum name,
                              @NotBlank String description) {
}
