package com.backend.pilates.model;

import com.backend.pilates.enums.HourTimeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hour_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HourTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Horas do dia que tem aula",
    example = "NOVE_HORAS")
    private HourTimeEnum hourTime;
    @Column(nullable = false, length = 5)
    private String displayHourTime;
}
