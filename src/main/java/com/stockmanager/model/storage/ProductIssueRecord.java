package com.stockmanager.model.storage;

public class ProductIssueRecord extends MovementRecord {

    private final double gainValue;

    public ProductIssueRecord(String id, Date movementDate, Lot productIssuedLot, int movedAmount) {
        super(id, movementDate, productIssuedLot, movedAmount );
        this.gainValue = calculateGain();
    }

    public double getGainValue() {
        return gainValue;
    }

    private double calculateGain() {
        return this.lot.getProduct().getPrice() * this.movedAmount;
    }
}
