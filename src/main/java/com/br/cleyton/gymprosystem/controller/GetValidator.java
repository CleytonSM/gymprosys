package com.br.cleyton.gymprosystem.controller;

import com.br.cleyton.gymprosystem.exceptions.ApiRequestException;
import com.br.cleyton.gymprosystem.exceptions.EntityNotFoundException;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GetValidator {

    private Integer id;
    private InstructorRepository repository;
    private Integer pageNumber;

    public GetValidator(Integer id, InstructorRepository repository) {
        this.id = id;
        this.repository = repository;
    }

    public GetValidator(InstructorRepository repository) {
        this.repository = repository;
    }

    public GetValidator(InstructorRepository repository, Integer pageNumber) {
        this.repository = repository;
        this.pageNumber = pageNumber;
    }

    public InstructorModel getInstructorValidator() {
        Optional<InstructorModel> instructorModel = repository.findById(id);
        if(instructorModel.isEmpty()) {
            throw new EntityNotFoundException("This instructor does not exist");
        }

        return instructorModel.get();
    }

    public List<InstructorModel> getAllInstructorValidator() {
        List<InstructorModel> allInstructors = repository.findAll();
        if(allInstructors.isEmpty()) {
            throw new EntityNotFoundException("There are no instructors created yet");
        }

        return allInstructors;
    }

    public Stream<InstructorModel> getAllInstructorPageableValidator() {
        int definedSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, definedSize);
        Page<InstructorModel> instructorsPage = repository.findAll(pageable);
        if(instructorsPage.isEmpty()) {
            throw new ApiRequestException("This page is empty");
        }

        return instructorsPage.stream();
    }
}
