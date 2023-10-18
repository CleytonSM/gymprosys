package com.br.cleyton.gymprosystem.service.instructor;

import com.br.cleyton.gymprosystem.controller.PatchValidator;
import com.br.cleyton.gymprosystem.exceptions.*;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository repository;
    @Autowired
    private InstructorAlreadyExistsException instructorAlreadyExistsException; // verificar opção melhor para resolver isso

    public ResponseEntity<Object> createInstructor (InstructorModel instructorModel){
        try {
            IllegalCpfException.validateCpf(instructorModel.getCpf());
            instructorAlreadyExistsException.validateInstructor(instructorModel);
            InstructorModel instructor = repository.save(instructorModel);

            return new ResponseEntity<>(instructor, HttpStatus.CREATED);
        } catch (IllegalCpfException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Object> getInstructor (Integer id) {
        Optional<InstructorModel> instructorModel = repository.findById(id);
        if(instructorModel.isEmpty()) {
            return new ResponseEntity<>("This instructor does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructorModel, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllInstructors() {
        List<InstructorModel> allInstructors = repository.findAll();
        if(allInstructors.isEmpty()) {
            return new ResponseEntity<>("There isn't instructors created yet", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allInstructors, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllInstructorsPageable (int pageNumber) {
        int definedSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, definedSize);
        Page<InstructorModel> instructorsPage = repository.findAll(pageable);
        if(instructorsPage.isEmpty()) {
            throw new ApiRequestException("This page is empty");
        }

        return new ResponseEntity<>(instructorsPage.stream(), HttpStatus.OK);
    }

    public ResponseEntity<Object> partialInstructorUpdate(int id, InstructorModel partialInstructor) {
        PatchValidator patchValidator = new PatchValidator(id, partialInstructor, repository);
        InstructorModel instructor = patchValidator.patchInstructorValidator();

        repository.save(instructor);

        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    public ResponseEntity<Object> fullInstructorUpdate(int id, InstructorModel instructorModel) {
        Optional<InstructorModel> optionalInstructorModel = repository.findById(id);

        if(optionalInstructorModel.isEmpty()) {
            throw new EntityNotFoundException("This instructor does not exist");
        }

        InstructorModel instructor = optionalInstructorModel.get();
        instructor.set
        return new ResponseEntity<>(optionalInstructorModel.stream(), HttpStatus.OK);

    }
}
