package com.br.mssankhyaorder.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "O nome do cliente não pode estar em branco.")
    private String name;

    @Email(message = "O e-mail deve ser válido.")
    @NotBlank(message = "O e-mail não pode estar em branco.")
    private String email;
}
