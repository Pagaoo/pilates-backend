package com.backend.pilates.repositories;

import com.backend.pilates.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
}
