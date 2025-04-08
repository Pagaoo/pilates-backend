package com.backend.pilates.config;

import com.backend.pilates.enums.DaysOfWeekEnum;
import com.backend.pilates.enums.HourTimeEnum;
import com.backend.pilates.model.DaysOfTheWeek;
import com.backend.pilates.model.HourTime;
import com.backend.pilates.repositories.DayOfTheWeekRepository;
import com.backend.pilates.repositories.HourTimeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInitializer {
    private final HourTimeRepository hourTimeRepository;
    private final DayOfTheWeekRepository dayOfTheWeekRepository;

    public DatabaseInitializer(HourTimeRepository hourTimeRepository, DayOfTheWeekRepository dayOfTheWeekRepository) {
        this.hourTimeRepository = hourTimeRepository;
        this.dayOfTheWeekRepository = dayOfTheWeekRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        for (HourTimeEnum value : HourTimeEnum.values()) {
            if (!hourTimeRepository.existsByHourTime(value)) {
                HourTime newHourTime = new HourTime();
                newHourTime.setHourTime(value);
                newHourTime.setDisplayHourTime(value.getDisplayHourTime());
                hourTimeRepository.save(newHourTime);
            }
        }

        for (DaysOfWeekEnum value : DaysOfWeekEnum.values()) {
            if (!dayOfTheWeekRepository.existsByDayOfTheWeek(value)) {
                DaysOfTheWeek newDaysOfTheWeek = new DaysOfTheWeek();
                newDaysOfTheWeek.setDayOfTheWeek(value);
                newDaysOfTheWeek.setDayOfTheWeekDisplay(value.getDisplayDayOfWeek());
                dayOfTheWeekRepository.save(newDaysOfTheWeek);
            }
        }
    }
}
