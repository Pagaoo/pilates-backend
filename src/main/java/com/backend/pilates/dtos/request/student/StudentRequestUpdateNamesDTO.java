package com.backend.pilates.dtos.request.student;

import jakarta.validation.constraints.NotBlank;

public record StudentRequestUpdateNamesDTO(@NotBlank String first_name,
                                           @NotBlank String last_name) {
}