package com.br.mssankhyaorder.application.service;

import com.br.mssankhyaorder.application.dto.OrderMessage;
import com.br.mssankhyaorder.application.dto.OrderRequest;
import com.br.mssankhyaorder.application.dto.OrderResponse;
import com.br.mssankhyaorder.application.mapper.OrderMapper;
import com.br.mssankhyaorder.application.producer.OrderProducer;
import com.br.mssankhyaorder.domain.enums.OrderStatus;
import com.br.mssankhyaorder.domain.model.Order;
import com.br.mssankhyaorder.domain.model.OrderProduct;
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

    private final OrderProducer orderProducer;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        var order = new Order();
        order.setCustomer(customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() ->
                        new CustomException(HttpStatus.NOT_FOUND,
                                            "CUSTOMER_NOT_FOUND",
                                            "Cliente não encontrado.")));

        order.setSaleDate(orderRequest.getSaleDate());
        order.setStatus(OrderStatus.PENDENTE_DE_SEPARACAO);

        List<OrderProduct> orderProducts = orderRequest.getProducts().stream()
                .map(productQuantity -> {
                    var orderProduct = new OrderProduct();
                    var product = productRepository.findById(productQuantity.getProductId())
                            .orElseThrow(() ->
                                    new CustomException(HttpStatus.NOT_FOUND,
                                                        "PRODUCT_NOT_FOUND",
                                                        "Produto não encontrado."));
                    orderProduct.setProduct(product);
                    orderProduct.setQuantity(productQuantity.getQuantity());
                    orderProduct.setOrder(order);
                    return orderProduct;
                })
                .collect(Collectors.toList());

        order.setProducts(orderProducts);

        var savedOrder = orderRepository.save(order);
        var message = OrderMessage.with(savedOrder);
        orderProducer.sendOrderMessage(message);
        return OrderResponse.with(savedOrder);
    }

    public List<OrderResponse> listOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }
}
