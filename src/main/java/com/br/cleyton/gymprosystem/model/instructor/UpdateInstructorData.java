package com.br.cleyton.gymprosystem.model.instructor;


public record UpdateInstructorData(
        String name,
        String lastname,
        String cpf,
        Integer years
) {
}
