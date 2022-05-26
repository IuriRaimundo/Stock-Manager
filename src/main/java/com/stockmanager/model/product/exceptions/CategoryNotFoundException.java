package com.stockmanager.model.product.exceptions;

import org.jetbrains.annotations.NotNull;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(@NotNull  String categoryId) {
        super("Categoria não encontrada. Category Id: " + categoryId);
    }
}
