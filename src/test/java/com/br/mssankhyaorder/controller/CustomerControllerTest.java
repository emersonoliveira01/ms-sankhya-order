package com.br.mssankhyaorder.controller;

import com.br.mssankhyaorder.application.dto.CustomerRequest;
import com.br.mssankhyaorder.application.dto.CustomerResponse;
import com.br.mssankhyaorder.application.service.CustomerService;
import com.br.mssankhyaorder.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private CustomerRequest customerRequest;
    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {

        customerRequest = new CustomerRequest();
        customerRequest.setName("Customer A");
        customerRequest.setEmail("customer.a@example.com");

        customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        customerResponse.setName("Customer A");
        customerResponse.setEmail("customer.a@example.com");
    }

    @Test
    void registerCustomer_ShouldReturnCustomerResponse_WhenSuccessful() {
        when(customerService.registerCustomer(any(CustomerRequest.class))).thenReturn(customerResponse);

        ResponseEntity<CustomerResponse> response = customerController.registerCustomer(customerRequest);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Customer A", response.getBody().getName());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void registerCustomer_ShouldThrowException_WhenEmailAlreadyExists() {
        when(customerService.registerCustomer(any(CustomerRequest.class)))
                .thenThrow(new CustomException(HttpStatus.CONFLICT, "EMAIL_ALREADY_EXISTS", "Já existe um cliente cadastrado com este e-mail."));

        var exception = assertThrows(CustomException.class, () -> {
            customerController.registerCustomer(customerRequest);
        });

        assertEquals("Já existe um cliente cadastrado com este e-mail.", exception.getMessage());
        assertEquals("EMAIL_ALREADY_EXISTS", exception.getErrorCode());
    }

    @Test
    void listCustomers_ShouldReturnListOfCustomerResponse() {
        when(customerService.listCustomers()).thenReturn(List.of(customerResponse));

        var responseList = customerController.listCustomers();

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals("Customer A", responseList.get(0).getName());
    }

}