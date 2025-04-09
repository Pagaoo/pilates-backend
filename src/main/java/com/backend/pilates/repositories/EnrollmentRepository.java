package com.backend.pilates.repositories;

import com.backend.pilates.model.Classes;
import com.backend.pilates.model.Enrollments;
import com.backend.pilates.model.Student;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollments, Long> {
    boolean existsByStudentAndClasses(Student student, Classes classes);
}
