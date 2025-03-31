package com.backend.pilates.services;

import com.backend.pilates.dtos.request.RolesRequestDTO;
import com.backend.pilates.dtos.response.RolesResponseDTO;
import com.backend.pilates.mappers.RolesMapper;
import com.backend.pilates.model.Roles;
import com.backend.pilates.repositories.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;
    private final RolesMapper rolesMapper;

    public RolesService(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

    public RolesResponseDTO createRole(RolesRequestDTO rolesRequestDTO) {
        Roles role = rolesMapper.toRolesEntity(rolesRequestDTO);
        Roles newRole = rolesRepository.save(role);
        return rolesMapper.toRolesResponseDTO(newRole);
    }
}
