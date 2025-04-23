package com.backend.pilates.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
}
