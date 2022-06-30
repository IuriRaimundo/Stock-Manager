package com.stockmanager.model.storage.exceptions;

public class InvalidMovementMovedQuantityException extends RuntimeException {

    /**
     * @param movedQuantity Quantidade de produtos inválida.
     */
    public InvalidMovementMovedQuantityException(int movedQuantity) {
        super("Quantidade de produtos inválida. Tem de ser no mínimo 1. Quantidade inválida: " + movedQuantity);
    }
}
