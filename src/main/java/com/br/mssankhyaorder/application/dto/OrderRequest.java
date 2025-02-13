package com.br.mssankhyaorder.application.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "O ID do cliente não pode ser nulo.")
    private Long customerId;

    @NotNull(message = "O ID do produto não pode ser nulo.")
    private Long productId;

    @Positive(message = "A quantidade deve ser um número positivo.")
    private int quantity;

    @NotNull(message = "A data da venda não pode ser nula.")
    @FutureOrPresent(message = "A data da venda deve ser no futuro ou no presente.")
    private LocalDateTime saleDate;
}
