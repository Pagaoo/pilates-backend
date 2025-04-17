package com.backend.pilates.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "students")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @NotBlank
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    @NotBlank
    @Column(nullable = false, length = 15, unique = true)
    private String phone;
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
    @OneToMany(mappedBy = "student")
    private List<Enrollments> enrollments;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Instant updatedAt;
}
