package com.backend.pilates.controllers;


import com.backend.pilates.dtos.request.StudentRequestDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PatchMapping("names/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.updateStudentById(id, studentRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

}
