package com.br.mssankhyaorder.service;

import com.br.mssankhyaorder.application.dto.OrderRequest;
import com.br.mssankhyaorder.application.dto.OrderResponse;
import com.br.mssankhyaorder.application.dto.ProductQuantity;
import com.br.mssankhyaorder.application.mapper.OrderMapper;
import com.br.mssankhyaorder.application.producer.OrderProducer;
import com.br.mssankhyaorder.application.service.OrderService;
import com.br.mssankhyaorder.domain.model.Customer;
import com.br.mssankhyaorder.domain.model.Order;
import com.br.mssankhyaorder.domain.model.OrderProduct;
import com.br.mssankhyaorder.domain.model.Product;
import com.br.mssankhyaorder.domain.repository.CustomerRepository;
import com.br.mssankhyaorder.domain.repository.OrderRepository;
import com.br.mssankhyaorder.domain.repository.ProductRepository;
import com.br.mssankhyaorder.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private  OrderProducer orderProducer;

    private OrderRequest orderRequest;
    private Order order;
    private OrderResponse orderResponse;
    private Customer customer;
    private Product product;
    private OrderProduct orderProduct;
    private ProductQuantity productQuantity;

    @BeforeEach
    void setUp() {

        productQuantity = new ProductQuantity();
        productQuantity.setQuantity(10);
        productQuantity.setProductId(1L);

        orderRequest = new OrderRequest();
        orderRequest.setCustomerId(1L);
        orderRequest.setProducts(List.of(productQuantity));

        customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer A");
        customer.setEmail("customer.a@example.com");

        product = new Product();
        product.setId(1L);
        product.setName("Product A");
        product.setPrice(10.0);
        product.setStock(100);

        orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        orderProduct.setId(1L);
        orderProduct.setQuantity(1);

        order = new Order();
        order.setId(1L);
        order.setCustomer(customer);
        order.setProducts(List.of(orderProduct));

        orderResponse = new OrderResponse();
        orderResponse.setId(1L);
        orderResponse.setClientName(customer.getName());
        orderResponse.setProducts(List.of());
    }

    @Test
    void createOrder_ShouldReturnOrderResponse_WhenOrderIsValid() {
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        var response = orderService.createOrder(orderRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Customer A", response.getClientName());
        Assertions.assertEquals(1L, response.getId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void createOrder_ShouldThrowCustomException_WhenProductNotFound() {
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        var exception = assertThrows(CustomException.class, () -> {
            orderService.createOrder(orderRequest);
        });

        Assertions.assertEquals("Produto não encontrado.", exception.getMessage());
        Assertions.assertEquals("PRODUCT_NOT_FOUND", exception.getErrorCode());
    }

    @Test
    void listOrders_ShouldReturnListOfOrderResponse() {
        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(orderMapper.toResponse(any())).thenReturn(orderResponse);

       var responseList = orderService.listOrders();

        Assertions.assertNotNull(responseList);
        Assertions.assertEquals(1, responseList.size());
        Assertions.assertEquals(1L, responseList.get(0).getId());
    }

    @Test
    void createOrder_ShouldThrowCustomException_WhenCustomerNotFound() {

        var exception = assertThrows(CustomException.class,
                () -> orderService.createOrder(orderRequest));

        Assertions.assertEquals("Cliente não encontrado.", exception.getMessage());
        Assertions.assertEquals("CUSTOMER_NOT_FOUND", exception.getErrorCode());
    }
}