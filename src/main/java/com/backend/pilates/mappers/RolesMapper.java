package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.RolesRequestDTO;
import com.backend.pilates.dtos.response.RolesResponseDTO;
import com.backend.pilates.model.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Roles toRolesEntity(RolesRequestDTO dto);

    RolesResponseDTO toRolesResponseDTO(Roles roles);
}
