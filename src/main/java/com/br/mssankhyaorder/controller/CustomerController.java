package com.br.mssankhyaorder.controller;

import com.br.mssankhyaorder.application.dto.CustomerRequest;
import com.br.mssankhyaorder.application.dto.CustomerResponse;
import com.br.mssankhyaorder.application.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente", description = "Endpoint para cadastrar um novo cliente na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação no cliente"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CustomerResponse> registerCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.registerCustomer(customerRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Endpoint para listar todos os clientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<CustomerResponse> listCustomers() {
        return customerService.listCustomers();
    }
}
