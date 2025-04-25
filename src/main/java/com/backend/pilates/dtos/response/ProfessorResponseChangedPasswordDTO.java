package com.backend.pilates.dtos.response;

import java.time.Instant;

public record ProfessorResponseChangedPasswordDTO(boolean success,
                                                  String message,
                                                  Instant updatedAt) {
}
