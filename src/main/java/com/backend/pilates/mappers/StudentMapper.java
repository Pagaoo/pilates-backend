package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.student.StudentRequestDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    Student toStudentEntity(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO toStudentResponseDTO(Student student);

    default Student toStudentEntityWithBuilderIsActiveTrue(StudentRequestDTO studentRequestDTO) {
        return Student.builder()
                .first_name(studentRequestDTO.first_name())
                .last_name(studentRequestDTO.last_name())
                .phone(studentRequestDTO.phone())
                .created_at(Instant.now())
                .updated_at(Instant.now()).
                build();
    }
}
