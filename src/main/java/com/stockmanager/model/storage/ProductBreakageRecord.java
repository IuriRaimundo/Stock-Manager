package com.stockmanager.model.storage;

public class ProductBreakageRecord extends MovementRecord {

    private final ProductBreakageReason reason;
    private final String observation;
    private final double lossValue;

    public ProductBreakageRecord(String id, Date movementDate, Lot brokeProductLot, int movedAmount, ProductBreakageReason motive, String observation) {
        super(id, movementDate, brokeProductLot, movedAmount);
        this.reason = motive;
        this.observation = observation;
        this.lossValue = calculateLoss();
    }

    public ProductBreakageReason getReason() {
        return reason;
    }

    public String getObservation() {
        return observation;
    }

    public double getLossValue() {
        return lossValue;
    }

    private double calculateLoss() {
        return this.lot.getProduct().getPrice() * this.movedAmount;
    }
}
