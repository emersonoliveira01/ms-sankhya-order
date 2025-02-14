package com.br.mssankhyaorder.controller;

import com.br.mssankhyaorder.application.dto.OrderRequest;
import com.br.mssankhyaorder.application.dto.OrderResponse;
import com.br.mssankhyaorder.application.service.OrderService;
import com.br.mssankhyaorder.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private OrderRequest orderRequest;
    private OrderResponse orderResponse;


    @BeforeEach
    void setUp() {

        orderRequest = new OrderRequest();
        orderRequest.setCustomerId(1L);

        orderResponse = new OrderResponse();
        orderResponse.setId(1L);
//        orderResponse.setCustomerId(1L);
//        orderResponse.setProductId(1L);
//        orderResponse.setQuantity(2);
    }

    @Test
    void createOrder_ShouldReturnOrderResponse_WhenSuccessful() {
        when(orderService.createOrder(any(OrderRequest.class))).thenReturn(orderResponse);

        var response = orderController.createOrder(orderRequest);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        //Assertions.assertEquals(1L, response.getBody().getCustomerId());
    }

    @Test
    void createOrder_ShouldThrowException_WhenCustomerNotFound() {
        when(orderService.createOrder(any(OrderRequest.class)))
                .thenThrow(new CustomException(HttpStatus.NOT_FOUND, "CUSTOMER_NOT_FOUND", "Cliente não encontrado"));

        var exception = assertThrows(CustomException.class, () -> {
            orderController.createOrder(orderRequest);
        });

        Assertions.assertEquals("Cliente não encontrado", exception.getMessage());
        Assertions.assertEquals("CUSTOMER_NOT_FOUND", exception.getErrorCode());
    }

    @Test
    void listOrders_ShouldReturnListOfOrderResponse() {
        when(orderService.listOrders()).thenReturn(List.of(orderResponse));

        var responseList = orderController.listOrders();

        Assertions.assertNotNull(responseList);
        Assertions.assertEquals(1, responseList.size());
      //  Assertions.assertEquals(1L, responseList.get(0).getCustomerId());
    }

}