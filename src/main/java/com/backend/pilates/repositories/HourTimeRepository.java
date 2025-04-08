package com.backend.pilates.repositories;

import com.backend.pilates.enums.HourTimeEnum;
import com.backend.pilates.model.HourTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourTimeRepository extends JpaRepository<HourTime, Long> {
    boolean existsByHourTime(HourTimeEnum hourTime);
}
