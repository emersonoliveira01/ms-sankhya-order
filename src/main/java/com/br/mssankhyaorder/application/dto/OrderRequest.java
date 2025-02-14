package com.br.mssankhyaorder.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "O ID do cliente não pode ser nulo.")
    private Long customerId;

    @NotNull(message = "A data da venda não pode ser nula.")
    private LocalDateTime saleDate;

    private List<ProductQuantity> products;
}
