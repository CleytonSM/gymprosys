package com.br.cleyton.gymprosystem.controller.instructor;

import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
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
    public ResponseEntity<?> createInstructor(@RequestBody @Validated InstructorModel instructorModel) {
        return service.createInstructor(instructorModel);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<?> getInstructor(@PathVariable Integer id) {
        return service.getInstructor(id);
    }
}
