package com.stockmanager.model.product.exceptions;

import com.stockmanager.model.product.Category;
import org.jetbrains.annotations.NotNull;

public class UnkownCategoryException extends RuntimeException {

    public UnkownCategoryException(@NotNull Category category) {
        super("Unkown Category. Category Id: " + category.getId());
    }

}
