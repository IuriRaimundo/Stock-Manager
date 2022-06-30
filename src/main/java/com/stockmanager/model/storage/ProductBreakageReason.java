package com.stockmanager.model.storage;

import com.stockmanager.model.product.ProductPricingUnit;
import org.jetbrains.annotations.NotNull;

public enum ProductBreakageReason {
    EMPLOYEE_NEGLIGENE("Negligência do funcionário"),
    PRODUCT_DEFECT("Defeito no produto"),
    TRANSPORT_DAMAGE("Danos em transporte"),
    PRODUCT_EXPIRED("Fora de validade");

    public static final ProductBreakageReason[] productBreakageReasons = {
            EMPLOYEE_NEGLIGENE,
            PRODUCT_DEFECT,
            TRANSPORT_DAMAGE,
            PRODUCT_EXPIRED
    };

    public final String displayMessage;

    ProductBreakageReason(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * Método para encontrar uma ProductPricingUnit através de uma displayMessage.
     * Tempo de execução linear O(n)
     * @param displayMessage Display Message a procurar
     * @return ProductBreakageReason com ou null se não encontrar.
     */
    public static ProductBreakageReason getProductBreakageReasonByDisplayMessage(@NotNull String displayMessage) {
        for (ProductBreakageReason productBreakageReason : productBreakageReasons) {
            if (productBreakageReason.displayMessage.equals(displayMessage)) {
                return productBreakageReason;
            }
        }

        return null;
    }
}
