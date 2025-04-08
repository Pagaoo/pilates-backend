package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.ClassesRequestDTO;
import com.backend.pilates.dtos.response.ClassesResponseDTO;
import com.backend.pilates.model.Classes;
import com.backend.pilates.model.DaysOfTheWeek;
import com.backend.pilates.model.HourTime;
import com.backend.pilates.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "professor", source = "professor_id")
    @Mapping(target = "day_of_the_week", source = "day_id")
    @Mapping(target = "hour_time", source = "hour_id")
    Classes toClassesEntity(ClassesRequestDTO classesRequestDTO);

    @Mapping(target = "professor_id", source = "professor.id")
    @Mapping(target = "day_id", source = "day_of_the_week.id")
    @Mapping(target = "hour_id", source = "hour_time.id")
    ClassesResponseDTO toClassesResponseDTO(Classes classes);

    default Professor mapProfessors(Long professor_id) {
        if (professor_id != null) {
            Professor professor = new Professor();
            professor.setId(professor_id);
            return professor;
        }
        else return null;
    }

    default DaysOfTheWeek mapDaysOfTheWeek(Long day_id) {
        if (day_id != null) {
            DaysOfTheWeek dayOfTheWeek = new DaysOfTheWeek();
            dayOfTheWeek.setId(day_id);
            return dayOfTheWeek;
        } else return null;
    }

    default HourTime mapHourTime(Long hour_id) {
        if (hour_id != null) {
            HourTime hourTime = new HourTime();
            hourTime.setId(hour_id);
            return hourTime;
        } else return null;
    }
}
