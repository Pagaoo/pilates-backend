package com.backend.pilates.repositories;

import com.backend.pilates.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
