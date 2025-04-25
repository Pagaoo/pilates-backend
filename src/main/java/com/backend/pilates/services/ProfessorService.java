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
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Transactional
    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO requestDTO) {
        Professor professor = professorMapper.toProfessorEntity(requestDTO);
        professorRepository.save(professor);
        return professorMapper.toProfessorResponseDTO(professor);
    }

    @Transactional
    public ProfessorResponseDTO getProfessorById(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return professorMapper.toProfessorResponseDTO(professor);
    }

    @Transactional
    public List<ProfessorResponseDTO> findAllProfessors() {
        return professorRepository.findAll().stream().map(professorMapper::toProfessorResponseDTO).toList();
    }

    @Transactional
    public ProfessorResponseDTO updateProfessorDetailsById(Long id, ProfessorRequestUpdateDetailsDTO professorRequestUpdateDetailsDTO) {
        Professor existingProfessor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        professorMapper.updateProfessorDetailsFromDTO(professorRequestUpdateDetailsDTO, existingProfessor);
        existingProfessor.setUpdatedAt(Instant.now());
        Professor updatedProfessor = professorRepository.save(existingProfessor);
        return professorMapper.toProfessorResponseDTO(updatedProfessor);
    }

    @Transactional
    public ProfessorResponseChangedPasswordDTO changeProfessorPasswordById(Long id, ProfessorRequestChangePasswordDTO professorRequestChangePasswordDTO) {
        Professor existingProfessor = professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        professorMapper.changeProfessorPassword(professorRequestChangePasswordDTO, existingProfessor);
        existingProfessor.setUpdatedAt(Instant.now());
        Professor updatedProfessor = professorRepository.save(existingProfessor);
        return professorMapper.toProfessorChangePasswordDTO(updatedProfessor);
    }

}
