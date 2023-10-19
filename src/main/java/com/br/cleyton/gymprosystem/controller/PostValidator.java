package com.br.cleyton.gymprosystem.controller;

import com.br.cleyton.gymprosystem.exceptions.ApiRequestException;
import com.br.cleyton.gymprosystem.exceptions.CpfLengthException;
import com.br.cleyton.gymprosystem.exceptions.EntityAlreadyExistsException;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;

public class PostValidator {

    private InstructorModel instructorBody;
    private InstructorRepository repository;

    public PostValidator(InstructorModel instructorBody, InstructorRepository repository) {
        this.instructorBody = instructorBody;
        this.repository = repository;
    }

    public InstructorModel postInstructorValidator() {
        InstructorModel isInstructor = repository.findByCpf(instructorBody.getCpf());

        if (instructorBody.getName() == null) {
            throw new ApiRequestException("'name' is missing");
        }
        if (instructorBody.getLastname() == null) {
            throw new ApiRequestException("'lastname' is missing");
        }
        if (instructorBody.getCpf() == null) {
            throw new ApiRequestException("'cpf' is missing");
        }
        if (instructorBody.getYears() == null) {
            throw new ApiRequestException("'years' is missing");
        }
        if(isInstructor != null) {
            throw new EntityAlreadyExistsException("This instructor already exists");
        }
        if(instructorBody.getCpf().length() != 11) {
            throw new CpfLengthException("Cpf must to be 11 characters long");
        }
        return repository.save(instructorBody);
    }
}
