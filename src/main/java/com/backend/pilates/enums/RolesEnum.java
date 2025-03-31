package com.backend.pilates.enums;

import lombok.Getter;

@Getter
public enum RolesEnum {
    ROLE_ADMIN("Administrador"),
    ROLE_PROFESSOR("Professor");

    private final String displayName;

    RolesEnum(String displayName) {
        this.displayName = displayName;
    }

}
