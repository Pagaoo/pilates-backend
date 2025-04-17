package com.backend.pilates.services;

import com.backend.pilates.dtos.request.EnrollmentRequestDTO;
import com.backend.pilates.dtos.response.EnrollmentResponseDTO;
import com.backend.pilates.mappers.EnrollmentMapper;
import com.backend.pilates.model.Classes;
import com.backend.pilates.model.Enrollments;
import com.backend.pilates.model.Student;
import com.backend.pilates.repositories.ClassesRepository;
import com.backend.pilates.repositories.EnrollmentRepository;
import com.backend.pilates.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final ClassesRepository classesRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, ClassesRepository classesRepository, StudentRepository studentRepository, EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.classesRepository = classesRepository;
        this.studentRepository = studentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    public List<EnrollmentResponseDTO> createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
        Classes classes = classesRepository.findById(enrollmentRequestDTO.class_id())
                .orElseThrow(EntityNotFoundException::new);

        List<Enrollments> enrollmentsList = enrollmentRequestDTO.student_id().stream()
                .map(student_id -> {
                    Student student = studentRepository.findById(student_id)
                            .orElseThrow(EntityNotFoundException::new);

                    boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndClasses(student, classes);
                    if (alreadyEnrolled) return null;

                    Enrollments enrollments = new Enrollments();
                    enrollments.setStudent(student);
                    enrollments.setClasses(classes);
                    enrollments.setCreatedAt(Instant.now());
                    enrollments.setUpdatedAt(Instant.now());
                    return enrollments;
                }).filter(Objects::nonNull).toList();
        List<Enrollments> savedEnrollments = enrollmentRepository.saveAll(enrollmentsList);
        return savedEnrollments.stream()
                .map(enrollmentMapper::toEnrollmentResponseDTO)
                .toList();
    }
}
