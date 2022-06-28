package com.stockmanager.model.storage;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class ProductIssueRecord extends MovementRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final double gainValue;

    /**
     * @param productIssuedLot Lote do movimento
     * @param movedAmount Quantidade de produtos movidos
     */
    public ProductIssueRecord(@NotNull Lot productIssuedLot,
                              int movedAmount)
    {
        super(productIssuedLot, movedAmount );
        this.gainValue = calculateGain();
    }

    /**
     * @return Valor ganho pela saída de produtos
     */
    public double getGainValue() {
        return gainValue;
    }

    /**
     * Método para calcular o valor ganho pela saída de produtos
     * @return
     */
    private double calculateGain() {
        return this.lot.getProduct().getPrice() * this.movedAmount;
    }
}
