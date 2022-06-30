package com.stockmanager.model.storage;

import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.exceptions.ProductNotFoundException;
import com.stockmanager.model.storage.exceptions.InvalidLotExpirationDateException;
import com.stockmanager.model.storage.exceptions.DuplicateLotEntryException;
import com.stockmanager.model.storage.exceptions.ProductNotActiveException;

import java.util.Calendar;
import java.util.LinkedList;

abstract public class StorageManagerUtils {

    /**
     * Método para validar a entrada de um lote
     * @param lotEntryRecord Registo de entrada do lote
     * @param storedLots Lots armazenados para validar
     * @throws InvalidLotExpirationDateException Se a data de expiração do lote for inferior à data atual
     * @throws DuplicateLotEntryException Se o lote já estiver guardado nos storedLots
     * @throws ProductNotFoundException Se o produto do lote não estiver registado no storage manager
     * @throws ProductNotActiveException Se o produto do lote não estiver ativo
     */
    static void validateLotEntry(LotEntryRecord lotEntryRecord, LinkedList<Lot> storedLots) {

        Lot lot = lotEntryRecord.getLot();

        // Validar data de expiração
        Date expirationDate =  lot.getExpirationDate();
        // Se data de expiração for inferior à data atual, lançar exceção
        if (expirationDate.calendar.compareTo(Calendar.getInstance()) < 0) {
            throw new InvalidLotExpirationDateException(expirationDate);
        }

        // Validar unicidade do lote
        if (storedLots.contains(lot)) {
            throw new DuplicateLotEntryException(lotEntryRecord.getLot());
        }

        // Verificar se o produto existe no ProductManager
        if (!ProductManager.getProductManager().productExists(lot.getProduct())) {
            throw new ProductNotFoundException(lot.getProduct().getId());
        }

        // Verificar se o produto está ativo
        if (!lot.getProduct().getIsActive()) {
            throw new ProductNotActiveException(lot.getProduct());
        }

    }
}
