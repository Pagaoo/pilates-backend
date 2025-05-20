package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.ClassesRequestDTO;
import com.backend.pilates.dtos.response.ClassesResponseDTO;
import com.backend.pilates.services.ClassesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/classes")
@Tag(name = "Classes", description = "Classes managements operations")
public class ClassesController {
    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @Operation(
            summary = "Create a new class",
            operationId = "addClasses",
            description = """
                    Performs a creation operation of a new class entity in the database.
                  
                    Business Rules:
                    - professor id must be unique;
                    - Create a new class entity in the database;
                    
                    System Behavior:
                    - Generates unique class id;
                    - Records creation timestamp;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "class created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = ClassesResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "professor_id": "12312",
                                                    "day_id": "12312",
                                                    "hour_id": "12312",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro while creating class (error response)")
            })
    @PostMapping
    public ResponseEntity<ClassesResponseDTO> createClass(@RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        ClassesResponseDTO newClass = classesService.createClass(classesRequestDTO);
        URI location = URI.create("/v1/classes/" + newClass.id());
        return ResponseEntity.created(location).body(newClass);
    }

    @Operation(
            summary = "Get all classes from the database",
            operationId = "getAllClasses",
            description =
                    """
                    Perform a get list of all classes in the database.
                    
                    System behavior:
                    - List all classes in the database;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all classes",
                            content = @Content(
                                    schema = @Schema(implementation = ClassesResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "professor_id": "123",
                                                    "day_id": "1232",
                                                    "hour_id": "123",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found any classes to return"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping
    public ResponseEntity<List<ClassesResponseDTO>> findAllClasses() {
        return ResponseEntity.ok(classesService.findAllClasses());
    }

    @Operation(
            summary = "Get a class by the id",
            operationId = "getClassesById",
            description =
                    """
                    Performs a search to return one class by the id.
                    
                    System behavior:
                    - Get a class by the id;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Show the class found by the id",
                            content = @Content(
                                    schema = @Schema(implementation = ClassesResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                    "id": 123345,
                                                    "professor_id": "123",
                                                    "day_id": "1232",
                                                    "hour_id": "123",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The server could not found the class using the id provided"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @GetMapping("/{id}")
    public ResponseEntity<ClassesResponseDTO> findClassById(@PathVariable Long id) {
        return ResponseEntity.ok(classesService.findClassById(id));
    }

    @Operation(
            summary = "Update classes information",
            operationId = "patchClassesInfo ",
            description =
                    """
                    Performs a update of an existing class in the database.
                    
                    System behavior:
                    - Update an existing class in the database using the id;
                    - Confirmation response (body response);
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Class updated successfully",
                            content = @Content(
                                    schema = @Schema(implementation = ClassesResponseDTO.class),
                                    examples = @ExampleObject(
                                            name = "success",
                                            value =  """
                                                {
                                                    "id": 123345,
                                                    "professor_id": "123",
                                                    "day_id": "1232",
                                                    "hour_id": "123",
                                                    "created_at" "2025-11-21T10:30:00Z"
                                                    "updated_at" "2025-11-21T10:30:00Z"
                                                }
                                                """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error updating the class provided, class not found"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<ClassesResponseDTO> updateClass(@PathVariable Long id, @RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        return ResponseEntity.ok(classesService.updateClass(id, classesRequestDTO));
    }

    @Operation(
            summary = "Delete Classes",
            operationId = "deleteClasses",
            description =
                    """
                    Performs a delete of an existing class in the database.
                    
                    System behavior:
                    - delete an existing class using the id provided;
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "class deleted successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error deleting class, class not found"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The server was not able to process the information")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
