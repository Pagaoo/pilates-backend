package com.backend.pilates.model;

import com.backend.pilates.enums.DaysOfWeekEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "day_of_the_week")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DaysOfTheWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Dias das semanas que tem aula",
            example = "SEGUNDA",
            allowableValues = {"SEGUNDA", "TERCA", "QUARTA", "QUINTA", "SEXTA"})
    private DaysOfWeekEnum dayOfTheWeek;
    @Column(nullable = false, length = 15)
    private String dayOfTheWeekDisplay;
}
