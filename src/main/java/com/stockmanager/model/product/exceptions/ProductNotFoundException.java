package com.stockmanager.model.product.exceptions;

import org.jetbrains.annotations.NotNull;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(@NotNull  String productId) {
        super("Produto não encontrado. Product Id: " + productId);
    }
}
