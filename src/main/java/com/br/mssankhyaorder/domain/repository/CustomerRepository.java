package com.br.mssankhyaorder.domain.repository;

import com.br.mssankhyaorder.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
