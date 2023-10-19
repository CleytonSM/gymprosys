package com.br.cleyton.gymprosystem.controller.customer;

import com.br.cleyton.gymprosystem.model.customer.CustomerModel;
import com.br.cleyton.gymprosystem.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createCustomer
            (@RequestBody @Validated CustomerModel customerBody) {
        return service.createCustomer(customerBody);
    }
}
