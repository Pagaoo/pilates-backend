package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.StudentRequestDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    Student toStudentEntity(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO toStudentResponseDTO(Student student);
}
