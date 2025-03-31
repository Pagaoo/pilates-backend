package com.backend.pilates.dtos.request;

import com.backend.pilates.enums.RolesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RolesRequestDTO(
                              @Schema(description = "Tipo do perfil no sistema",
                                      example = "ROLE_ADMIN",
                                      allowableValues = {"ROLE_ADMIN", "ROLE_PROFESSOR"},
                                      implementation = String.class,
                                      type = "string")
                              RolesEnum name,
                              @Schema(description = "Descrição do perfil")
                              @NotBlank String description) {
}
