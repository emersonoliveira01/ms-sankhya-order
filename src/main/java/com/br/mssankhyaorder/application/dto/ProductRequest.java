package com.br.mssankhyaorder.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "O nome do produto não pode estar em branco.")
    private String name;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive(message = "O preço deve ser um número positivo.")
    private double price;

    @NotNull(message = "O estoque não pode ser nulo.")
    @Positive(message = "A quantidade em estoque deve ser um número positivo.")
    private int stock;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String description;

    @NotBlank(message = "O NCM não pode estar em branco.")
    private String ncm;

    @NotBlank(message = "O CST não pode estar em branco.")
    private String cst;

    @NotBlank(message = "A unidade de medida não pode estar em branco.")
    private String unitOfMeasure;

    @NotBlank(message = "O CFOP não pode estar em branco.")
    private String cfop;
}
