package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record StudentRequestDTO(@NotBlank String first_name,
                                @NotBlank String last_name,
                                @NotBlank String phone) {
}
