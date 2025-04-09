package com.backend.pilates.mappers;

import com.backend.pilates.dtos.response.EnrollmentResponseDTO;
import com.backend.pilates.model.Enrollments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "student_id", source = "student.id")
    @Mapping(target = "class_id", source = "classes.id")
    EnrollmentResponseDTO toEnrollmentResponseDTO(Enrollments enrollments);
}
