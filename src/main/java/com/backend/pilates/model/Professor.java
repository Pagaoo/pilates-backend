package com.backend.pilates.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

//não tem o campo id pq o JOINED da superclasse faz o id como fk e pk

@Entity
@Table(name = "professors")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User {
    @Column(name = "professor_bio", nullable = false, columnDefinition = "TEXT", length = 150)
    private String professorBio;
    @Column(name = "professor_specialization", nullable = false, length = 25)
    private String professorSpecialization;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
