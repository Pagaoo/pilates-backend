package com.backend.pilates.services;

import com.backend.pilates.dtos.request.ProfessorRequestChangePasswordDTO;
import com.backend.pilates.dtos.request.ProfessorRequestDTO;
import com.backend.pilates.dtos.request.ProfessorRequestUpdateDetailsDTO;
import com.backend.pilates.dtos.response.ProfessorResponseChangedPasswordDTO;
import com.backend.pilates.dtos.response.ProfessorResponseDTO;
import com.backend.pilates.mappers.ProfessorMapper;
import com.backend.pilates.model.Professor;
import com.backend.pilates.repositories.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper, PasswordEncoder passwordEncoder) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO requestDTO) {
        Professor professor = professorMapper.toProfessorEntity(requestDTO);
        professorRepository.save(professor);
        return professorMapper.toProfessorResponseDTO(professor);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ProfessorResponseDTO findProfessorById(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return professorMapper.toProfessorResponseDTO(professor);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<ProfessorResponseDTO> findAllProfessors() {
        return professorRepository.findAll().stream().map(professorMapper::toProfessorResponseDTO).toList();
    }

    @Transactional
    public ProfessorResponseDTO updateProfessorDetails(Long id, ProfessorRequestUpdateDetailsDTO professorRequestUpdateDetailsDTO) {
        Professor existingProfessor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        professorMapper.updateProfessorDetailsFromDTO(professorRequestUpdateDetailsDTO, existingProfessor);
        existingProfessor.setUpdatedAt(Instant.now());
        Professor updatedProfessor = professorRepository.save(existingProfessor);
        return professorMapper.toProfessorResponseDTO(updatedProfessor);
    }

    @Transactional
    public ProfessorResponseChangedPasswordDTO changePassword(Long id, ProfessorRequestChangePasswordDTO professorRequestChangePasswordDTO) {
        Professor professor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (!professorRequestChangePasswordDTO.currentPassword().equals(professor.getPassword())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        if (!professorRequestChangePasswordDTO.newPassword().equals(professorRequestChangePasswordDTO.confirmNewPassword())) {
            throw new IllegalArgumentException("Confirmação de senha inválida");
        }

        if (professorRequestChangePasswordDTO.newPassword().equals(professor.getPassword())) {
            throw new IllegalArgumentException("Use uma senha diferente da atual");
        }

        String encodedPassword = passwordEncoder.encode(professorRequestChangePasswordDTO.newPassword());
        professorMapper.updatePassword(encodedPassword, professor);
        professor.setUpdatedAt(Instant.now());
        professorRepository.save(professor);

        return new ProfessorResponseChangedPasswordDTO(
                true,
                "Senha atualizada com sucesso",
                professor.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        professorRepository.delete(professor);
    }
}
