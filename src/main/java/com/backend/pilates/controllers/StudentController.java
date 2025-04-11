package com.backend.pilates.controllers;


import com.backend.pilates.dtos.request.StudentRequestDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@Tag(name = "Students", description = "Student management operations")
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

    @Operation(
            summary = "Update student status",
            description = """
                        Performs a logical deletion of an existing student by setting is_active=false.
                        
                        Important notes:
                        - Data remains in the database;
                        - A scheduled job perform a permanent deletion of inactive students after 90 days;
                        - No confirmation response (silent operation)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student deactivated (body response)"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found")
            })
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StudentResponseDTO> deactivateStudent(
            @Parameter(description = "Student id", example = "123")
            @PathVariable Long id) {
        StudentResponseDTO studentToBeDeactivated = studentService.deactivateStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentToBeDeactivated);
    }
}
