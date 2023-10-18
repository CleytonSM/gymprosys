package com.br.cleyton.gymprosystem.controller;

import com.br.cleyton.gymprosystem.exceptions.EntityNotFoundException;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;

import java.util.Optional;

public class PatchValidator {

    private final int id;
    private final InstructorModel partialInstructor;
    private final InstructorRepository repository;

    public PatchValidator(int id, InstructorModel partialInstructor, InstructorRepository repository) {
        this.id = id;
        this.partialInstructor = partialInstructor;
        this.repository = repository;
    }

    public InstructorModel patchInstructorValidator() {

        Optional<InstructorModel> instructorOptional = repository.findById(id);
        if (instructorOptional.isEmpty()) {
            throw new EntityNotFoundException("This instructor does not exist");
        }
        InstructorModel instructor = instructorOptional.get();
        if (partialInstructor.getName() != null) {
            instructor.setName(partialInstructor.getName());
        }

        if (partialInstructor.getLastname() != null) {
            instructor.setLastname(partialInstructor.getLastname());
        }

        if (partialInstructor.getCpf() != null) {
            instructor.setCpf(partialInstructor.getCpf());
        }

        if (partialInstructor.getYears() != null) {
            instructor.setYears(partialInstructor.getYears());
        }

        return instructor;
    }
}
