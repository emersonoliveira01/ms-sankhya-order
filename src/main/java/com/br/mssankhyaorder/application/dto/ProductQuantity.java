package com.br.mssankhyaorder.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuantity {

    @NotNull(message = "O ID do cliente não pode ser nulo.")
    private Long productId;

    @Positive(message = "A quantidade deve ser um número positivo.")
    private int quantity;
}
