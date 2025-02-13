package com.br.mssankhyaorder.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String barcode;
    private double price;
    private int stock;
    private String description;
    private String ncm;
    private String cst;
    private String unitOfMeasure;
    private String cfop;
}
