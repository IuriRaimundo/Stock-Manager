package com.stockmanager.model.storage;

import com.stockmanager.model.common.BatchJob;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * Batch job para registar lotes expirados como quebra
 */
public class BreakExpiredLotsBatchJob extends BatchJob {

    public static BreakExpiredLotsBatchJob instance = new BreakExpiredLotsBatchJob();

    BreakExpiredLotsBatchJob() {
        super(BreakExpiredLotsBatchJob.class.getSimpleName());
    }

    @Override
    protected void run() {

        /*
         * Percorrer lista de lotes armazenados e verificar se a data de expiração é menor ou igual à data atual.
         * Se for menor registar uma quebra de produto com o lote inteiro.
         */
        StorageManager storageManager = StorageManager.getStorageManager();
        LinkedList<Lot> storedLots = storageManager.getStoredLots();

        for (Lot lot : storedLots) {
            if (lot.getExpirationDate().calendar.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                ProductBreakageRecord pbr = new ProductBreakageRecord(
                        storageManager.movementRecordIdGenerator.generateId(),
                        lot,
                        lot.getQuantity(),
                        ProductBreakageReason.PRODUCT_EXPIRED,
                        "Processado automaticamente."
                );

                storageManager.registerProductBreakage(pbr);
            }
        }

    }
}
