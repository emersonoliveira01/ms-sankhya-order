package com.br.mssankhyaorder.service;

import com.br.mssankhyaorder.application.dto.CustomerRequest;
import com.br.mssankhyaorder.application.dto.CustomerResponse;
import com.br.mssankhyaorder.application.mapper.CustomerMapper;
import com.br.mssankhyaorder.application.service.CustomerService;
import com.br.mssankhyaorder.domain.model.Customer;
import com.br.mssankhyaorder.domain.repository.CustomerRepository;
import com.br.mssankhyaorder.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private CustomerRequest customerRequest;
    private Customer customer;
    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest();
        customerRequest.setName("Customer A");
        customerRequest.setEmail("customer.a@example.com");

        customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer A");
        customer.setEmail("customer.a@example.com");

        customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        customerResponse.setName("Customer A");
        customerResponse.setEmail("customer.a@example.com");
    }

    @Test
    void registerCustomer_ShouldReturnCustomerResponse_WhenCustomerIsValid() {
        when(customerMapper.toEntity(any(CustomerRequest.class))).thenReturn(customer);
        when(customerRepository.findByEmail(customerRequest.getEmail())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toResponse(any(Customer.class))).thenReturn(customerResponse);

        CustomerResponse response = customerService.registerCustomer(customerRequest);

        assertNotNull(response);
        assertEquals("Customer A", response.getName());
        assertEquals(1L, response.getId());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void registerCustomer_ShouldThrowCustomException_WhenEmailAlreadyExists() {
        when(customerRepository.findByEmail(customerRequest.getEmail())).thenReturn(Optional.of(customer));

        var exception = assertThrows(CustomException.class, () -> {
            customerService.registerCustomer(customerRequest);
        });

        assertEquals("Já existe um cliente cadastrado com este e-mail.", exception.getMessage());
        assertEquals("EMAIL_ALREADY_EXISTS", exception.getErrorCode());
    }

    @Test
    void listCustomers_ShouldReturnListOfCustomerResponse() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapper.toResponse(any(Customer.class))).thenReturn(customerResponse);

        var responseList = customerService.listCustomers();

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals("Customer A", responseList.get(0).getName());
    }

}