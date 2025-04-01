package com.backend.pilates.services;

import com.backend.pilates.dtos.request.ProfessorRequestDTO;
import com.backend.pilates.dtos.response.ProfessorResponseDTO;
import com.backend.pilates.mappers.ProfessorMapper;
import com.backend.pilates.model.Professor;
import com.backend.pilates.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO requestDTO) {
        Professor professor = professorMapper.toProfessorEntity(requestDTO);
        professorRepository.save(professor);
        return professorMapper.toProfessorResponseDTO(professor);
    }
}
