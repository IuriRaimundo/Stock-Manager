package com.stockmanager.model.storage;

import com.stockmanager.model.storage.exceptions.InvalidLotExpirationDateException;

import java.util.Calendar;

abstract public class StorageManagerUtils {

    /**
     * Método para validar a entrada de um lote
     * @param lotEntryRecord
     */
    static void validateLotEntry(LotEntryRecord lotEntryRecord) {
        // Validar data de expiração
        Date expirationDate =  lotEntryRecord.getLot().getExpirationDate();
        // Se data de expiração for inferior à data atual, lançar exceção
        if (expirationDate.calendar.compareTo(Calendar.getInstance()) < 0) {
            throw new InvalidLotExpirationDateException(expirationDate);
        }
    }
}
