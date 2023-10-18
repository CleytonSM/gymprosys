package com.br.cleyton.gymprosystem.model.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorModel, UUID> {
    InstructorModel findByCpf(String cpf);
    Optional<InstructorModel> findById(Integer id);
}
