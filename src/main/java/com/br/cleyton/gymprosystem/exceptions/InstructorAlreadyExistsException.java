package com.br.cleyton.gymprosystem.exceptions;

import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructorAlreadyExistsException {

    @Autowired
    private InstructorRepository repository;
    private static boolean canBeCreated;


    public void validateInstructor(InstructorModel instructorModel) {
        InstructorModel isInstructor = repository.findByCpf(instructorModel.getCpf());
        if(isInstructor != null) {
            canBeCreated = false;
            isInstructorExists();
        }
    }

    public static void isInstructorExists() {
        if(!canBeCreated) {
            throw new ApiRequestException("This instructor is already registered");
        }
    }
}
