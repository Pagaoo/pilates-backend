package com.backend.pilates.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EnrollmentRequestDTO(@NotEmpty List<Long> student_id, @NotNull Long class_id) {
}
