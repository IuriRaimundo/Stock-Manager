package com.stockmanager.model.product.exceptions;

import org.jetbrains.annotations.NotNull;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(@NotNull  String productId) {
        super("Produto não encontrado. Product Id: " + productId);
    }
}
