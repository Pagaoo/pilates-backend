package com.backend.pilates.mappers;

import com.backend.pilates.dtos.request.UserRequestDTO;
import com.backend.pilates.dtos.response.UserResponseDTO;
import com.backend.pilates.model.Roles;
import com.backend.pilates.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "role_id")
    User toUserEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "role_id", source = "role.id")
    UserResponseDTO toUserResponseDTO(User user);

    default Roles mapRoles(Long role_id) {
        if (role_id != null) {
            Roles role = new Roles();
            role.setId(role_id);
            return role;
        }
        else return null;
    }
}
