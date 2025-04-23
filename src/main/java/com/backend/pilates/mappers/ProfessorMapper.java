package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.ProfessorRequestDTO;
import com.backend.pilates.dtos.request.ProfessorRequestUpdateDetailsDTO;
import com.backend.pilates.dtos.response.ProfessorResponseDTO;
import com.backend.pilates.model.Professor;
import com.backend.pilates.model.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", source = "role_id", qualifiedByName = "idToRole")
    Professor toProfessorEntity(ProfessorRequestDTO professorRequestDTO);

    @Mapping(target = "role_id", source = "role.id")
    ProfessorResponseDTO toProfessorResponseDTO(Professor professor);

    @Mapping(target = "firstName",
            expression = "java(shouldUpdateProfessorNamesAndBioAndSpecialization(professorRequestUpdateDetailsDTO.firstName(), professor.getFirstName()))")
    @Mapping(target = "lastName",
            expression = "java(shouldUpdateProfessorNamesAndBioAndSpecialization(professorRequestUpdateDetailsDTO.lastName(), professor.getLastName()))")
    @Mapping(target = "professorBio",
            expression = "java(shouldUpdateProfessorNamesAndBioAndSpecialization(professorRequestUpdateDetailsDTO.professorBio(), professor.getProfessorBio()))")
    @Mapping(target = "professorSpecialization",
            expression = "java(shouldUpdateProfessorNamesAndBioAndSpecialization(professorRequestUpdateDetailsDTO.professorSpecialization(), professor.getProfessorSpecialization()))")
    void updateProfessorDetailsFromDTO(ProfessorRequestUpdateDetailsDTO professorRequestUpdateDetailsDTO, @MappingTarget Professor professor);

    @Named("idToRole")
    default Roles idToRole(Long id) {
        if (id == null) return null;
        Roles role = new Roles();
        role.setId(id);
        return role;
    }

    default String shouldUpdateProfessorNamesAndBioAndSpecialization(String newValue, String currentValue) {
        return (newValue != null && !newValue.equals("string") && !newValue.trim().isEmpty()) ? newValue : currentValue;
    }
}

