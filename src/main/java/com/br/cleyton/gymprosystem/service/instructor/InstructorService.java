package com.br.cleyton.gymprosystem.service.instructor;

import com.br.cleyton.gymprosystem.errors.IllegalCpfException;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository repository;

    public ResponseEntity<?> createInstructor (InstructorModel instructorModel){
        try {
            IllegalCpfException.validateCpf(instructorModel.getCpf());
            isInstructorExists(instructorModel);
            InstructorModel instructor = repository.save(instructorModel);

            return new ResponseEntity<>(instructor, HttpStatus.CREATED);
        } catch (IllegalCpfException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getInstructor (Integer id) {
        Optional<InstructorModel> instructorModel = repository.findById(id);
        if(instructorModel.isEmpty()) {
            return new ResponseEntity<>("This instructor does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructorModel, HttpStatus.OK);
    }

    public void isInstructorExists (InstructorModel instructorModel) throws Exception {
        InstructorModel isInstructor = repository.findByCpf(instructorModel.getCpf());
        if(isInstructor != null) {
            throw new Exception("This instructor is already registered");
        }
    }
}
