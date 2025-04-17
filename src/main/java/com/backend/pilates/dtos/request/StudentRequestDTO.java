package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record StudentRequestDTO(@NotBlank String firstName,
                                @NotBlank String lastName,
                                @NotBlank String phone) {
}
