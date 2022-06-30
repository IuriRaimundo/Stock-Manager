package com.stockmanager.model.storage;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class ProductBreakageRecord extends MovementRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ProductBreakageReason reason;
    private final String observation;
    private final double lossValue;

    /**
     * @param id Id do movimento
     * @param brokeProductLot Lote do movimento
     * @param movedAmount Quantidade de produtos quebrados
     * @param reason Motivo da quebra
     * @param observation Observações sobre a quebra
     */
    public ProductBreakageRecord(@NotNull String id,
                                 @NotNull Lot brokeProductLot,
                                 int movedAmount,
                                 @NotNull ProductBreakageReason reason,
                                 @NotNull String observation)
    {
        super(id, brokeProductLot, movedAmount);
        this.reason = reason;
        this.observation = observation;
        this.lossValue = calculateLoss();
    }

    /**
     * @return Motivo da quebra
     */
    public ProductBreakageReason getReason() {
        return reason;
    }

    /**
     * @return Observação sobre a quebra
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @return Valor perdido com a quebra
     */
    public double getLossValue() {
        return lossValue;
    }

    /**
     * Método para calcular o valor perdido pela quebra
     * @return
     */
    private double calculateLoss() {
        return this.lot.getProduct().getPrice() * this.movedAmount;
    }
}
