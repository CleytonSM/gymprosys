package com.br.cleyton.gymprosystem.controller;

import com.br.cleyton.gymprosystem.exceptions.ApiRequestException;
import com.br.cleyton.gymprosystem.exceptions.CpfLengthException;
import com.br.cleyton.gymprosystem.exceptions.EntityAlreadyExistsException;
import com.br.cleyton.gymprosystem.model.customer.CustomerModel;
import com.br.cleyton.gymprosystem.model.customer.CustomerRepository;
import com.br.cleyton.gymprosystem.model.customer.GymPlans;
import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;
import com.br.cleyton.gymprosystem.model.instructor.InstructorRepository;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class PostValidator {

    private InstructorModel instructorBody;
    private InstructorRepository instructorRepository;
    private CustomerRepository customerRepository;
    private CustomerModel customerBody;

    public PostValidator(InstructorModel instructorBody, InstructorRepository instructorRepository) {
        this.instructorBody = instructorBody;
        this.instructorRepository = instructorRepository;
    }

    public PostValidator(CustomerModel customerBody, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerBody = customerBody;
    }

    public InstructorModel postInstructorValidator() {
        InstructorModel isInstructor = instructorRepository.findByCpf(instructorBody.getCpf());

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
        return instructorRepository.save(instructorBody);
    }

    public CustomerModel postCustomerValidator() {
        CustomerModel isCustomer = customerRepository.findByEmail(customerBody.getEmail());

        if(customerBody.getName() == null) {
            throw new ApiRequestException("'name' is missing");
        }
        if (customerBody.getLastname() == null) {
            throw new ApiRequestException("'lastname' is missing");
        }
        if(customerBody.getEmail() == null) {
            throw new ApiRequestException("'email' is missing");
        }
        if(customerBody.getYears() == null) {
            throw new ApiRequestException("'years' is missing");
        }
        if(customerBody.getPlan() == null) {
            throw new ApiRequestException("'plan' is missing");
        }
        if(isCustomer != null) {
            throw new EntityAlreadyExistsException("This customer already exists");
        }
        LocalDateTime expirationDate;
        switch (customerBody.getPlan()) {
            case MONTHLY -> {
                expirationDate = LocalDateTime.now().plusMonths(1);
                customerBody.setExpirationDate(expirationDate);
            }
            case QUARTERLY -> {
                expirationDate = LocalDateTime.now().plusMonths(3);
                customerBody.setExpirationDate(expirationDate);
            }
            case SEMI_ANNUAL ->  {
                expirationDate = LocalDateTime.now().plusMonths(6);
                customerBody.setExpirationDate(expirationDate);
            }
            case ANNUAL -> {
                expirationDate = LocalDateTime.now().plusYears(1);
                customerBody.setExpirationDate(expirationDate);
            }
            default -> throw new RuntimeException("Plano inválido"); //tratar esse erro
        }

        return customerRepository.save(customerBody);
    }
}
