package com.stockmanager.model.storage.exceptions;

import com.stockmanager.model.storage.Lot;
import org.jetbrains.annotations.NotNull;

public class LotNotFoundException extends RuntimeException {
    public LotNotFoundException(@NotNull Lot lot) {
        super("Lote não encontrado. Lote: " + lot.getId());
    }
}
