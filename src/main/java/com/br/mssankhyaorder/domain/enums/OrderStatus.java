package com.br.mssankhyaorder.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDENTE_DE_SEPARACAO("PENDENTE DE SEPARAÇÃO"),
    FINALIZADO("FINALIZADO"),
    CANCELADO("CANCELADO"),
    DEVOLUCAO("DEVOLUÇÃO");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
