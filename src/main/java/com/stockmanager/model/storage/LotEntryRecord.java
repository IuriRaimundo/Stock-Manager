package com.stockmanager.model.storage;

import org.jetbrains.annotations.NotNull;

public class LotEntryRecord extends MovementRecord {

    public LotEntryRecord(@NotNull Lot lot) {
        super(lot, lot.getQuantity());
    }

}
