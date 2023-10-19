package com.br.cleyton.gymprosystem.model.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    CustomerModel findByEmail(String email);
}
