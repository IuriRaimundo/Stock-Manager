package com.stockmanager.model.storage.exceptions;

import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import org.jetbrains.annotations.NotNull;

public class ProductNotActiveException extends RuntimeException {

    public ProductNotActiveException(@NotNull Product product) {
        super("Registo de lote a produto inativo. Produto: " + product.getId());
    }
}
