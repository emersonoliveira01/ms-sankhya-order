package com.br.mssankhyaorder.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrder {
    private Long id;
    private String name;
}
