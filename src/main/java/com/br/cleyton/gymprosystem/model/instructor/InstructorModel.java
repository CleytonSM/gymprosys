package com.br.cleyton.gymprosystem.model.instructor;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class InstructorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String lastname;
    @Column(length = 11, nullable = false)
    private String cpf;
    @Column(nullable = false)
    private Integer years;
    private Boolean isDeleted = Boolean.FALSE;
    //hora de saída e entrada

    @CreationTimestamp
    private LocalDateTime createdAt;
}
