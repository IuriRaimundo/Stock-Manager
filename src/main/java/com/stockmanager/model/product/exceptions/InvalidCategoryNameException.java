package com.stockmanager.model.product.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidCategoryNameException extends RuntimeException {

    public InvalidCategoryNameException(@NotNull String name) {
        super("Nome de categoria inválido, os nomes das categorias devem ter entre 2 e 20 carateres. Nome inválido: " + name);
    }

}
