package com.br.mssankhyaorder.application.dto;

import com.br.mssankhyaorder.domain.enums.OrderStatus;
import com.br.mssankhyaorder.domain.model.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String clientName;
    private OrderStatus status;
    private LocalDateTime saleDate;
    private List<ProductOrder> products;

    public static OrderResponse with(Order order) {
        List<ProductOrder> productOrders = order.getProducts().stream()
                .map(orderProduct -> {
                    return ProductOrder.builder()
                            .name(orderProduct.getProduct().getName())
                            .id(orderProduct.getId())
                            .build();
                }).toList();
        return OrderResponse.builder()
                .id(order.getId())
                .products(productOrders)
                .saleDate(order.getSaleDate())
                .status(order.getStatus())
                .clientName(order.getCustomer().getName())
                .build();
    }
}
