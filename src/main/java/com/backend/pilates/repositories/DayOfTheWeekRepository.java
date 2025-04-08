package com.backend.pilates.repositories;

import com.backend.pilates.enums.DaysOfWeekEnum;
import com.backend.pilates.model.DaysOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfTheWeekRepository extends JpaRepository<DaysOfTheWeek, Long> {
    boolean existsByDayOfTheWeek(DaysOfWeekEnum daysOfWeekEnum);
}
