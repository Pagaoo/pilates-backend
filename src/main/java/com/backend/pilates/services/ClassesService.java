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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
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

    @Transactional
    public ClassesResponseDTO findClassById(Long classId) {
        Classes existingClass = classesRepository.findById(classId).orElseThrow(EntityNotFoundException::new);
        return classesMapper.toClassesResponseDTO(existingClass);
    }

    @Transactional
    public List<ClassesResponseDTO> findAllClasses() {
        return classesRepository.findAll().stream().map(classesMapper::toClassesResponseDTO).toList();
    }

    @Transactional
    public ClassesResponseDTO updateClass(Long classId, ClassesRequestDTO classesRequestDTO) {
        Classes existingClass = classesRepository.findById(classId).orElseThrow(EntityNotFoundException::new);
        classesMapper.updateClassesDetailsFromDTO(classesRequestDTO, existingClass);
        existingClass.setUpdatedAt(Instant.now());
        Classes savedClass = classesRepository.save(existingClass);
        return classesMapper.toClassesResponseDTO(savedClass);
    }

    @Transactional
    public void deleteClass(Long classId) {
        Classes existingClass = classesRepository.findById(classId).orElseThrow(EntityNotFoundException::new);
        classesRepository.delete(existingClass);
    }
}
