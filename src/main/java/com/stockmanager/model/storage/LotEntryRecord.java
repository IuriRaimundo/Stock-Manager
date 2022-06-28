package com.stockmanager.model.storage;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class LotEntryRecord extends MovementRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public LotEntryRecord(@NotNull Lot lot) {
        super(lot, lot.getQuantity());
    }

}
