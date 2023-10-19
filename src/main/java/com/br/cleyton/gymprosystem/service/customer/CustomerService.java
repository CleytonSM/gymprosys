package com.br.cleyton.gymprosystem.service.customer;

import com.br.cleyton.gymprosystem.controller.PostValidator;
import com.br.cleyton.gymprosystem.model.customer.CustomerModel;
import com.br.cleyton.gymprosystem.model.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public ResponseEntity<Object> createCustomer (CustomerModel customerBody) {
        PostValidator postValidator = new PostValidator(customerBody, repository);
        CustomerModel customer = postValidator.postCustomerValidator();

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
