package com.backend.pilates.dtos.request.student;

import jakarta.validation.constraints.NotBlank;

public record StudentRequestUpdatePhoneDTO(@NotBlank String phone) {
}
