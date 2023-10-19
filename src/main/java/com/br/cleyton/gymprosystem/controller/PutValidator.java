package com.br.cleyton.gymprosystem.controller;

import com.br.cleyton.gymprosystem.exceptions.ApiRequestException;
import com.br.cleyton.gymprosystem.exceptions.CpfLengthException;
import com.br.cleyton.gymprosystem.exceptions.EntityNotFoundException;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import com.br.cleyton.gymprosystem.model.instructor.UpdateInstructorData;

import java.util.Optional;

public class PutValidator {

    private Integer id;
    private UpdateInstructorData data;

    private InstructorRepository repository;

    public PutValidator(Integer id, UpdateInstructorData data, InstructorRepository repository) {
        this.id = id;
        this.data = data;
        this.repository = repository;
    }

    public InstructorModel putInstructorValidator() {

        Optional<InstructorModel> optionalInstructorModel = repository.findById(id);

        if(optionalInstructorModel.isEmpty()) {
            throw new EntityNotFoundException("This instructor does not exist");
        }

        if (data.name() == null) {
            throw new ApiRequestException("'name' is missing");
        }
        if (data.lastname() == null) {
            throw new ApiRequestException("'lastname' is missing");
        }
        if (data.cpf() == null) {
            throw new ApiRequestException("'cpf' is missing");
        }
        if (data.years() == null) {
            throw new ApiRequestException("'years' is missing");
        }
        if(data.cpf().length() != 11) {
            throw new CpfLengthException("Cpf must to be 11 characters long");
        }

        InstructorModel instructor = optionalInstructorModel.get();
        instructor.setName(data.name());
        instructor.setLastname(data.lastname());
        instructor.setCpf(data.cpf());
        instructor.setYears(data.years());

        return instructor;
    }
}
