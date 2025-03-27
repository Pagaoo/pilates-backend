package com.backend.pilates.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum RolesEnum {
    @Schema(description = "Administrador do sistema", example = "ROLE_ADMIN")
    ROLE_ADMIN,
    @Schema(description = "Professor", example = "ROLE_PROFESSOR")
    ROLE_PROFESSOR
}
