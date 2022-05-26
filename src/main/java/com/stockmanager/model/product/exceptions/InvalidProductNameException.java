package com.stockmanager.model.product.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidProductNameException extends RuntimeException {

    public InvalidProductNameException(@NotNull String name) {
        super("Nome de produto inválido, os nomes dos produtos devem ter entre 2 e 20 carateres. Nome inválido: " + name);
    }

}
