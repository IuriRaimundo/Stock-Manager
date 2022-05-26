package com.stockmanager.model.storage;

import java.util.LinkedList;

public class StorageManager {

    private final LinkedList<Lot> storedLots;
    private final LinkedList<Lot> lotHistory;

    private final LinkedList<LotEntryRecord> lotEntryRecords;
    private final LinkedList<ProductIssueRecord> productIssueRecords;
    private final LinkedList<ProductBreakageRecord> productBreakageRecords;

    private static final StorageManager instance = new StorageManager();

    public static StorageManager getStockManager () {
        return instance;
    }

    private StorageManager() {
        this.storedLots = new LinkedList<>();
        this.lotHistory = new LinkedList<>();
        this.lotEntryRecords = new LinkedList<>();
        this.productIssueRecords = new LinkedList<>();
        this.productBreakageRecords = new LinkedList<>();
    }

    public LinkedList<Lot> getStoredLots() {
        return new LinkedList<>(storedLots);
    }

    public LinkedList<Lot> getLotHistory() {
        return new LinkedList<>(lotHistory);
    }

    public LinkedList<LotEntryRecord> getLotEntryRecords() {
        return new LinkedList<>(lotEntryRecords);
    }

    public LinkedList<ProductIssueRecord> getProductIssueRecords() {
        return new LinkedList<>(productIssueRecords);
    }

    public LinkedList<ProductBreakageRecord> getProductBreakageRecords() {
        return new LinkedList<>(productBreakageRecords);
    }

    public void registerLotEntry(LotEntryRecord lotEntryRecord) {
        this.lotEntryRecords.add(lotEntryRecord);
        addLot(lotEntryRecord.getLot());
    }

    public void registerProductIssue(ProductIssueRecord productIssueRecord) {
        this.productIssueRecords.add(productIssueRecord);
        Lot lot = productIssueRecord.getLot();
        int movedAmount = productIssueRecord.getMovedAmount();
        lot.setQuantity(lot.getQuantity() - movedAmount);

        if (lot.getQuantity() == 0) {
            storedLots.remove(lot);
            lotHistory.add(lot);
        }
    }

    public void registerProductBreakage(ProductBreakageRecord productBreakageRecord) {
        this.productBreakageRecords.add(productBreakageRecord);
        Lot lot = productBreakageRecord.getLot();
        int movedAmount = productBreakageRecord.getMovedAmount();
        lot.setQuantity(lot.getQuantity() - movedAmount);

        if (lot.getQuantity() == 0) {
            storedLots.remove(lot);
            lotHistory.add(lot);
        }
    }

    private void addLot(Lot lot) {
        storedLots.add(lot);
    }

}
