package com.backend.pilates.enums;

import lombok.Getter;

@Getter
public enum HourTimeEnum {
    OITO_HORAS("08:00"),
    NOVE_HORAS("09:00"),
    DEZ_HORAS("10:00"),
    TREZE_HORAS("13:00"),
    QUATORZE_HORAS("14:00"),
    QUINZE_HORAS("15:00"),
    DEZESSEIS_HORAS("16:00"),
    DEZESETE_HORAS("17:00"),
    DEZOITO_HORAS("18:00"),
    DEZENOVE_HORAS("19:00"),
    VINTE_HORAS("20:00");

    private final String displayHourTime;

    HourTimeEnum(String displayHourTime) {
        this.displayHourTime = displayHourTime;
    }
}
