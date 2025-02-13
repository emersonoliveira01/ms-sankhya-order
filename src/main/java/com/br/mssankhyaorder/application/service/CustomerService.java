package com.br.mssankhyaorder.application.service;

import com.br.mssankhyaorder.application.dto.CustomerRequest;
import com.br.mssankhyaorder.application.dto.CustomerResponse;
import com.br.mssankhyaorder.application.mapper.CustomerMapper;
import com.br.mssankhyaorder.domain.repository.CustomerRepository;
import com.br.mssankhyaorder.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {

        var customer = customerMapper.toEntity(customerRequest);

        var existingCustomer = customerRepository.findByEmail(customerRequest.getEmail());

        if (existingCustomer.isPresent()) {
            throw new CustomException(HttpStatus.CONFLICT,
                                      "EMAIL_ALREADY_EXISTS",
                                      "Já existe um cliente cadastrado com este e-mail.");
        }

        var savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    public List<CustomerResponse> listCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }
}
