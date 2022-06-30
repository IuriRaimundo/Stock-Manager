package com.stockmanager.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum ProductPricingUnit {
    UN("UN", "Unidade"),
    KG("KG", "Kilograma"),
    LT("LT", "Litro"),
    G("G", "Grama");


    public static final ProductPricingUnit[] productPricingUnits = {UN, KG, LT, G};

    public final String id;
    public final String unitName;

    ProductPricingUnit(@NotNull String id, @NotNull String unitName) {
        this.id = id;
        this.unitName = unitName;
    }

    /**
     * Método para encontrar uma ProductPricingUnit através de um id
     * @param id Id a procurar
     * @return ProductPricingUnit com o id passado por parametro ou null se não existir
     */
    public static ProductPricingUnit getProductPricingUnitById(@NotNull String id) {
        for (ProductPricingUnit productPricingUnit : productPricingUnits) {
            if (productPricingUnit.id.equals(id)) {
                return productPricingUnit;
            }
        }

        return null;
    }
}
