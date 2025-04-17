package com.backend.pilates.services;

import com.backend.pilates.dtos.request.ClassesRequestDTO;
import com.backend.pilates.dtos.response.ClassesResponseDTO;
import com.backend.pilates.mappers.ClassesMapper;
import com.backend.pilates.model.Classes;
import com.backend.pilates.model.DaysOfTheWeek;
import com.backend.pilates.model.HourTime;
import com.backend.pilates.model.Professor;
import com.backend.pilates.repositories.ClassesRepository;
import com.backend.pilates.repositories.DayOfTheWeekRepository;
import com.backend.pilates.repositories.HourTimeRepository;
import com.backend.pilates.repositories.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClassesService {
    private  final ClassesRepository classesRepository;
    private final ClassesMapper classesMapper;
    private final ProfessorRepository professorRepository;
    private final DayOfTheWeekRepository dayOfTheWeekRepository;
    private final HourTimeRepository hourTimeRepository;

    public ClassesService(ClassesRepository classesRepository, ClassesMapper classesMapper, ProfessorRepository professorRepository, DayOfTheWeekRepository dayOfTheWeekRepository, HourTimeRepository hourTimeRepository) {
        this.classesRepository = classesRepository;
        this.classesMapper = classesMapper;
        this.professorRepository = professorRepository;
        this.dayOfTheWeekRepository = dayOfTheWeekRepository;
        this.hourTimeRepository = hourTimeRepository;
    }

    public ClassesResponseDTO createClass(ClassesRequestDTO classesRequestDTO) {
        Professor professor_id = professorRepository.findById(classesRequestDTO.professor_id())
                .orElseThrow(EntityNotFoundException::new);
        DaysOfTheWeek daysOfTheWeek_id = dayOfTheWeekRepository.findById(classesRequestDTO.day_id())
                .orElseThrow(EntityNotFoundException::new);
        HourTime hourTime_id = hourTimeRepository.findById(classesRequestDTO.hour_id())
                .orElseThrow(EntityNotFoundException::new);

        Classes newClass = classesMapper.toClassesEntity(classesRequestDTO);
        newClass.setProfessor(professor_id);
        newClass.setDaysOfTheWeek(daysOfTheWeek_id);
        newClass.setHourTime(hourTime_id);
        Classes savedClass = classesRepository.save(newClass);
        return classesMapper.toClassesResponseDTO(savedClass);
    }
}
