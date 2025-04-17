package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.StudentRequestDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Student toStudentEntity(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO toStudentResponseDTO(Student student);

    @Mapping(target = "firstName",
            expression = "java(shouldUpdateStudent(studentRequestUpdateNamesDTO.firstName(), student.getFirstName()))")
    @Mapping(target = "lastName",
            expression = "java(shouldUpdateStudent(studentRequestUpdateNamesDTO.lastName(), student.getLastName()))")
    @Mapping(target = "phone",
            expression = "java(shouldUpdateStudent(studentRequestUpdateNamesDTO.phone(), student.getPhone()))")
    void updateStudentNamesFromDTO(StudentRequestDTO studentRequestUpdateNamesDTO, @MappingTarget Student student);

    default Student toStudentEntityWithBuilderIsActiveTrue(StudentRequestDTO studentRequestDTO) {
        return Student.builder()
                .firstName(studentRequestDTO.firstName())
                .lastName(studentRequestDTO.lastName())
                .phone(studentRequestDTO.phone())
                .createdAt(Instant.now())
                .updatedAt(Instant.now()).
                build();
    }

    default String shouldUpdateStudent(String newValue, String currentValue) {
        return (newValue != null && !newValue.equals("string") && !newValue.trim().isEmpty()) ? newValue : currentValue;
    }
}
