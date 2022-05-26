package com.stockmanager.model.product.exceptions;

public class InvalidProductPriceException extends RuntimeException {

    public InvalidProductPriceException(double price) {
        super("Preço de produto inválido, os preços dos produtos devem ser superiores a 0. Preço inválido: " + price);
    }

}
