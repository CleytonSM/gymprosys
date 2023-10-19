package com.br.cleyton.gymprosystem.controller.instructor;

import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.UpdateInstructorData;
import com.br.cleyton.gymprosystem.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "instructor")
public class InstructorController {

    @Autowired
    private InstructorService service;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Object> createInstructor(@RequestBody @Validated InstructorModel instructorModel) {
        return service.createInstructor(instructorModel);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Object> getInstructor(@PathVariable Integer id) {
        return service.getInstructor(id);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllInstructors() {
        return service.getAllInstructors();
    }

    @GetMapping("/page={pageNumber}")
    public ResponseEntity<Object> getAllInstructorsPageable(@PathVariable Integer pageNumber) {
        return service.getAllInstructorsPageable(pageNumber);
    }

    @PatchMapping("/partial_update/id={id}")
    @Transactional
    public ResponseEntity<Object> partialInstructorUpdate(@PathVariable Integer id, @RequestBody InstructorModel instructorModel) {
        return service.partialInstructorUpdate(id, instructorModel);
    }

    @PutMapping("/update/id={id}")
    @Transactional
    public ResponseEntity<Object> fullInstructorUpdate (@PathVariable Integer id, @RequestBody @Validated UpdateInstructorData data) {
        return service.fullInstructorUpdate(id, data);
    }
}
