package com.stockmanager.model.storage;

import com.stockmanager.model.storage.exceptions.*;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class StorageManager {

    // Lotes armazenados
    private final LinkedList<Lot> storedLots;
    // Histórico de lotes
    private final LinkedList<Lot> lotHistory;
    // Registos de entrada de lote
    private final LinkedList<LotEntryRecord> lotEntryRecords;
    // Registos de saída de produtos de um lote
    private final LinkedList<ProductIssueRecord> productIssueRecords;
    // Registos de quebra de produtos de um lote
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

    /**
     * @return Cópia da lista de lotes armazenados
     */
    public LinkedList<Lot> getStoredLots() {
        return new LinkedList<>(storedLots);
    }

    /**
     * @return Cópia da lista de histórico de lotes
     */
    public LinkedList<Lot> getLotHistory() {
        return new LinkedList<>(lotHistory);
    }

    /**
     * @return Cópia da lista de registos de entrada de lot
     */
    public LinkedList<LotEntryRecord> getLotEntryRecords() {
        return new LinkedList<>(lotEntryRecords);
    }

    /**
     * @return Cópia da lista de registos de saída de produtos de um lote
     */
    public LinkedList<ProductIssueRecord> getProductIssueRecords() {
        return new LinkedList<>(productIssueRecords);
    }

    /**
     * @return Cópia da lista de registos de quebra de produtos de um lote
     */
    public LinkedList<ProductBreakageRecord> getProductBreakageRecords() {
        return new LinkedList<>(productBreakageRecords);
    }

    /**
     * Método para registar a entrada de um lote
     * @param lotEntryRecord Registo de entrada de um lote
     * @throws InvalidLotExpirationDateException Se a data de expiração de lote for inferior à data atual
     */
    public void registerLotEntry(@NotNull LotEntryRecord lotEntryRecord) throws InvalidLotExpirationDateException {
        // Validar entrada de lote
        StorageManagerUtils.validateLotEntry(lotEntryRecord);

        //  Adicionar lote aos lotes ativos e registar movimento de entrada
        this.storedLots.add(lotEntryRecord.getLot());
        this.lotEntryRecords.add(lotEntryRecord);
    }

    /**
     * Método para registar a saída de produtos de um lote
     * @param productIssueRecord Registo de saída de produtos de um lote
     * @throws InvalidLotQuantityException Se a quantidade do produto após subtração da quantidade movida for negativa
     * @throws LotNotFoundException Se o lote do movimento não se encontra na lista de lotes ativos.
     */
    public void registerProductIssue(@NotNull ProductIssueRecord productIssueRecord)
            throws InvalidLotQuantityException, LotNotFoundException
    {

        // Verificar se o lote está registado nos lotes ativos
        if (!storedLots.contains(productIssueRecord.getLot())) {
            throw new LotNotFoundException(productIssueRecord.getLot());
        }

        // Subtrair quantidade movida do lote
        Lot lot = productIssueRecord.getLot();
        int movedAmount = productIssueRecord.getMovedAmount();
        lot.subtractQuantity(movedAmount);

        // Registar movimento de saída de produtos
        this.productIssueRecords.add(productIssueRecord);

        // Se a quantidade do lote for 0, tirar lote dos lotes ativos e adicionar ao histórico
        if (lot.getQuantity() == 0) {
            storedLots.remove(lot);
            lotHistory.add(lot);
        }
    }

    /**
     * Método para registar a quebra de produtos de um lote
     * @param productBreakageRecord Registo de quebra de produtos de um lote
     * @throws InvalidLotQuantityException Se a quantidade do produto após subtração da quantidade movida for negativa
     * @throws LotNotFoundException Se o lote do movimento não se encontra na lista de lotes ativos.
     */
    public void registerProductBreakage(@NotNull ProductBreakageRecord productBreakageRecord)
            throws InvalidLotQuantityException, LotNotFoundException
    {

        // Verificar se o lote está registado nos lotes ativos
        if (!storedLots.contains(productBreakageRecord.getLot())) {
            throw new LotNotFoundException(productBreakageRecord.getLot());
        }

        // Subtrair quantidade movida do lote
        Lot lot = productBreakageRecord.getLot();
        int movedAmount = productBreakageRecord.getMovedAmount();
        lot.subtractQuantity(movedAmount);

        // Registar movimento de quebra de produtos
        this.productBreakageRecords.add(productBreakageRecord);

        // Se a quantidade do lote for 0, tirar lote dos lotes ativos e adicionar ao histórico
        if (lot.getQuantity() == 0) {
            storedLots.remove(lot);
            lotHistory.add(lot);
        }
    }
}
