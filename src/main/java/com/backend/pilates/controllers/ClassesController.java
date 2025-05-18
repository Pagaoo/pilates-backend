package com.backend.pilates.controllers;

import com.backend.pilates.dtos.request.ClassesRequestDTO;
import com.backend.pilates.dtos.response.ClassesResponseDTO;
import com.backend.pilates.model.Classes;
import com.backend.pilates.services.ClassesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/classes")
public class ClassesController {
    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @PostMapping
    public ResponseEntity<ClassesResponseDTO> createClass(@RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        ClassesResponseDTO newClass = classesService.createClass(classesRequestDTO);
        URI location = URI.create("/v1/classes/" + newClass.id());
        return ResponseEntity.created(location).body(newClass);
    }

    @GetMapping
    public ResponseEntity<List<ClassesResponseDTO>> findAllClasses() {
        return ResponseEntity.ok(classesService.findAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassesResponseDTO> findClassById(@PathVariable Long id) {
        return ResponseEntity.ok(classesService.findClassById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClassesResponseDTO> updateClass(@PathVariable Long id, @RequestBody @Valid ClassesRequestDTO classesRequestDTO) {
        return ResponseEntity.ok(classesService.updateClass(id, classesRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
