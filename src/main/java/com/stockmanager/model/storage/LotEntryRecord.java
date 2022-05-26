package com.stockmanager.model.storage;

public class LotEntryRecord extends MovementRecord {

    public LotEntryRecord(String id, Date movementDate, Lot lot) {
        super(id, movementDate, lot, lot.getQuantity());
    }

}
