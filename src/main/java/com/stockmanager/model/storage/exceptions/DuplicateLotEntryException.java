package com.stockmanager.model.storage.exceptions;

import com.stockmanager.model.storage.Lot;

public class DuplicateLotEntryException extends RuntimeException {
    public DuplicateLotEntryException(Lot lot) {
        super("Entrada de lote inválida, lote já foi registado. Lote: " + lot.getId());
    }
}
