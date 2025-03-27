package com.backend.pilates.model;

import com.backend.pilates.enums.RolesEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "db_roles")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Schema(
            description = "Papel do usuário no sistema",
            example = "ROLE_ADMIN",
            required = true,
            allowableValues = {"ROLE_ADMIN", "ROLE_PROFESSOR"}
    )
    private RolesEnum name;
    @Column(nullable = false, length = 50)
    private String description;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false, updatable = false)
    //como são para fins de auditoria, horários em UTC
    private Instant created_at;
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    //como são para fins de auditoria, horários em UTC
    private Instant updated_at;
}
