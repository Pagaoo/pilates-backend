package com.backend.pilates.services;

import com.backend.pilates.dtos.request.student_request.StudentRequestDTO;
import com.backend.pilates.dtos.request.student_request.StudentRequestUpdateNamesDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.mappers.StudentMapper;
import com.backend.pilates.model.Student;
import com.backend.pilates.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Transactional
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = studentMapper.toStudentEntityWithBuilderIsActiveTrue(studentRequestDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDTO(savedStudent);
    }

    @Transactional
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toStudentResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public StudentResponseDTO updateStudentById(Long id, StudentRequestUpdateNamesDTO studentRequestUpdateNamesDTO) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        BeanUtils.copyProperties(studentRequestUpdateNamesDTO, existingStudent, "id", "created_at", "phone", "is_active");
        existingStudent.setUpdated_at(Instant.now());
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toStudentResponseDTO(updatedStudent);
    }
}
