package com.stockmanager.model.storage.exceptions;

import com.stockmanager.model.storage.Date;

public class InvalidLotExpirationDateException extends RuntimeException {
    public InvalidLotExpirationDateException(Date expirationDate) {
        super("Data de expiração de lote inválida, deve ser superior à data atual. Data de expiração inválida: " + expirationDate.toString());
    }
}
