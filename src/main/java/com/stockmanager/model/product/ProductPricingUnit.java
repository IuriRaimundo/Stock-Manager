package com.stockmanager.model.product;

import org.jetbrains.annotations.NotNull;

public enum ProductPricingUnit {
    UN("Unidade"),
    KG("Kilograma"),
    LT("Litro"),
    G("Grana");

    public final String unitName;

    ProductPricingUnit(@NotNull String unitName) {
        this.unitName = unitName;
    }
}
