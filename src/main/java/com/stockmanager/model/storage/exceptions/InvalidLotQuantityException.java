package com.stockmanager.model.storage.exceptions;

public class InvalidLotQuantityException extends RuntimeException {

    public InvalidLotQuantityException(int quantity) {
        super("Quantidade de produtos de lote inválida. Quantidade: " + quantity);
    }
}
