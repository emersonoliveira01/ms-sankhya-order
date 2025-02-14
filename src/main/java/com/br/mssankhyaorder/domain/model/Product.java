package com.br.mssankhyaorder.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
