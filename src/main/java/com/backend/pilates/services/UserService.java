package com.backend.pilates.services;

import com.backend.pilates.dtos.request.UserRequestDTO;
import com.backend.pilates.dtos.response.UserResponseDTO;
import com.backend.pilates.mappers.UserMapper;
import com.backend.pilates.model.Roles;
import com.backend.pilates.model.User;
import com.backend.pilates.repositories.RolesRepository;
import com.backend.pilates.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserMapper userMapper;

    public UserService(UsersRepository usersRepository, RolesRepository rolesRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Roles role_id = rolesRepository.findById(userRequestDTO.role_id()).orElseThrow(EntityNotFoundException::new);

        User newUser = userMapper.toUserEntity(userRequestDTO);
        newUser.setRole(role_id);
        //colocar depois o user.setpassword para encriptar a senha
        User savedUser = usersRepository.save(newUser);
        return userMapper.toUserResponseDTO(savedUser);
    }
}
