package com.br.cleyton.gymprosystem.service.instructor;

import com.br.cleyton.gymprosystem.controller.GetValidator;
import com.br.cleyton.gymprosystem.controller.PatchValidator;
import com.br.cleyton.gymprosystem.controller.PostValidator;
import com.br.cleyton.gymprosystem.controller.PutValidator;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import com.br.cleyton.gymprosystem.model.instructor.UpdateInstructorData;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository repository;


    public ResponseEntity<Object> createInstructor (InstructorModel instructorBody){
        PostValidator postValidator = new PostValidator(instructorBody, repository);
        InstructorModel instructor = postValidator.postInstructorValidator();

        return new ResponseEntity<>(instructor, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getInstructor (Integer id) {
        GetValidator getValidator = new GetValidator(id, repository);
        InstructorModel instructor = getValidator.getInstructorValidator();
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllInstructors() {
        GetValidator getValidator = new GetValidator(repository);
        List<InstructorModel> instructorsList = getValidator.getAllInstructorValidator();

        return new ResponseEntity<>(instructorsList, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllInstructorsPageable (Integer pageNumber) {
        GetValidator getValidator = new GetValidator(repository, pageNumber);
        Stream<InstructorModel> instructorPageList = getValidator.getAllInstructorPageableValidator();

        return new ResponseEntity<>(instructorPageList, HttpStatus.OK);
    }

    public ResponseEntity<Object> partialInstructorUpdate(Integer id, InstructorModel partialInstructor) {
        PatchValidator patchValidator = new PatchValidator(id, partialInstructor, repository);
        InstructorModel instructor = patchValidator.patchInstructorValidator();

        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    public ResponseEntity<Object> fullInstructorUpdate(Integer id, UpdateInstructorData data) {
        PutValidator putValidator = new PutValidator(id, data, repository);
        InstructorModel instructor = putValidator.putInstructorValidator();

        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    //TODO soft delete instructor through hibernate (@SQLDelete), when I stop using h2-console
}
