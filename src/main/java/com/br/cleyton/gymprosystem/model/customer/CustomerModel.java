package com.br.cleyton.gymprosystem.model.customer;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String lastname;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private Integer years;
    @Column(nullable = false)
    private GymPlans plan;
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
