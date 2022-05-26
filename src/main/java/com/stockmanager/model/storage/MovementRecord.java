package com.stockmanager.model.storage;

abstract public class MovementRecord {

    protected final String id;
    protected final Date movementDate;
    protected final Lot lot;
    protected final int movedAmount;

    public MovementRecord(String id, Date movementDate, Lot lot, int movedAmount) {
        this.id = id;
        this.movementDate = movementDate;
        this.lot = lot;
        this.movedAmount = movedAmount;
    }

    public String getId() {
        return id;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public Lot getLot() {
        return lot;
    }

    public int getMovedAmount() {
        return movedAmount;
    }
}
