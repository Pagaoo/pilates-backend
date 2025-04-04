package com.backend.pilates.enums;

import lombok.Getter;

@Getter
public enum DaysOfWeekEnum {
    SEGUNDA("Segunda-feira"),
    TERCA("Terça-feira"),
    QUARTA("Quarta-feira"),
    QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira");

    private final String displayDayOfWeek;

    DaysOfWeekEnum(String displayDayOfWeek) {
        this.displayDayOfWeek = displayDayOfWeek;
    }
}
