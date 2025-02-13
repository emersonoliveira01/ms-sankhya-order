package com.br.mssankhyaorder.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Long customerId;
    private Long productId;
    private int quantity;
    private LocalDateTime saleDate;
}
