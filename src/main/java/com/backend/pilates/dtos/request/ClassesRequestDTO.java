package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.NotNull;

public record ClassesRequestDTO(@NotNull Long professor_id, @NotNull Long day_id, @NotNull Long hour_id) {
}
