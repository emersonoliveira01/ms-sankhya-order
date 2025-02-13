package com.br.mssankhyaorder.application.service;

import com.br.mssankhyaorder.application.dto.OrderRequest;
import com.br.mssankhyaorder.application.dto.OrderResponse;
import com.br.mssankhyaorder.application.mapper.OrderMapper;
import com.br.mssankhyaorder.domain.repository.CustomerRepository;
import com.br.mssankhyaorder.domain.repository.OrderRepository;
import com.br.mssankhyaorder.domain.repository.ProductRepository;
import com.br.mssankhyaorder.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final  CustomerRepository customerRepository;

    private final OrderMapper orderMapper;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        var order = orderMapper.toEntity(orderRequest);

        var customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() ->
                        new CustomException(HttpStatus.NOT_FOUND, "CUSTOMER_NOT_FOUND", "Cliente não encontrado"));

        var product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() ->
                        new CustomException(HttpStatus.NOT_FOUND, "PRODUCT_NOT_FOUND", "Produto não encontrado"));

        order.setCustomer(customer);
        order.setProduct(product);
        order.setSaleDate(orderRequest.getSaleDate());

        var savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    public List<OrderResponse> listOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }
}
