package com.br.mssankhyaorder.application.dto;


import com.br.mssankhyaorder.domain.enums.OrderStatus;
import com.br.mssankhyaorder.domain.model.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMessage {

    private Long id;

    private OrderStatus status;

    public static OrderMessage with(Order order) {
        return OrderMessage.builder()
                .id(order.getId())
                .status(order.getStatus())
                .build();
    }
}
