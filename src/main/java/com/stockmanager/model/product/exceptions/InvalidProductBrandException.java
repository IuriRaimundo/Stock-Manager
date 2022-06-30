package com.stockmanager.model.product.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidProductBrandException extends RuntimeException {

    public InvalidProductBrandException(@NotNull String brand) {
        super("Marca de produto inválida, os nomes das marcas devem ter pelo menos 1 caracter. Marca inválido: " + brand);
    }

}
