package com.backend.pilates.controllers;


import com.backend.pilates.dtos.request.StudentRequestDTO;
import com.backend.pilates.dtos.response.StudentResponseDTO;
import com.backend.pilates.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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

    //quando implementar as exceções voltar aqui e complementar a documentação com os response das exceções

    @Operation(
            summary = "Create new student",
            description = """
                    Performs a creation of a new student entity in the database.
                    
                    Business Rules:
                    - Phone must be unique across the system;
                    - Create a new Student entity in the database;
                    - Confirmation response (body response);
                    
                    System Behavior:
                    - Generates unique Student id;
                    - Sets default status as is_active=true;
                    - Records creation timestamp;
                    """,
            responses = {
                    @ApiResponse(
                        responseCode = "201",
                        description = "Student created successfully",
                        content = @Content(
                                schema = @Schema(implementation = StudentResponseDTO.class),
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "id": 123345,
                                                    "first_name": "John",
                                                    "last_name": "Doe",
                                                    "phone": "12312312",
                                                    "is_active": true,
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro while creating student (error response)")
            })
    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @Operation(
            summary = "Get all students from the database",
            description =
                    """
                    Performs a get list of all students in the database.
                    
                    System behavior:
                    - List all students in the database even the ones deactivated;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all students"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found any students to return"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @Operation(
            summary = "Get a student by his id",
            description =
                    """
                    Performs a search to return one student by his id.
                    
                    System behavior:
                    - Get a student by his id;
                    - Get the student even if the student is deactivated;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Show the student found by the id"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found the student by the id provided"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @Operation(
            summary = "Update student information",
            operationId = "patchStudentInfo ",
            description =
                    """
                    Performs a update of an existing student in the database.
                    
                    System behavior:
                    - Update an existing student in the database using the id;
                    - Only the first name, last name and phone can be updated;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student updated successfully",
                            content = @Content(
                                    schema = @Schema(implementation = StudentResponseDTO.class),
                                    examples = @ExampleObject(
                                            name = "success",
                                            value =  """
                                                {
                                                    "id": 123345,
                                                    "first_name": "John",
                                                    "last_name": "Doe",
                                                    "phone": "12312312",
                                                    "is_active": true,
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error updating student, student not found"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @PatchMapping("/{id}/information")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @Parameter(description = "Student id", example = "123")
            @PathVariable Long id,
            @RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.updateStudentById(id, studentRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @Operation(
            summary = "Update student status",
            description =
                        """
                        Performs a logical deletion of an existing student by setting is_active=false.
                    
                        System behavior:
                        - Data remains in the database;
                        - A scheduled job perform a permanent deletion of inactive students after 90 days;
                        - Confirmation response (body response);

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
