package com.stockmanager.model.product.exceptions;

import com.stockmanager.model.product.Category;
import org.jetbrains.annotations.NotNull;

public class InvalidCategoryDeletion extends RuntimeException {

    public InvalidCategoryDeletion(@NotNull Category category) {
        super("Tentativa de elimnação de categoria usada. Category Id: " + category.getId());
    }
}
