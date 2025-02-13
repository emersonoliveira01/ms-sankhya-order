package com.br.mssankhyaorder.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse implements Serializable {
    private Long id;
    private String name;
    private String email;
}
