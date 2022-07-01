package com.stockmanager.model.storage;

import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.exceptions.ProductNotFoundException;
import com.stockmanager.model.storage.exceptions.InvalidLotExpirationDateException;
import com.stockmanager.model.storage.exceptions.DuplicateLotEntryException;
import com.stockmanager.model.storage.exceptions.InvalidMovementMovedQuantityException;
import com.stockmanager.model.storage.exceptions.ProductNotActiveException;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.LinkedList;

abstract public class StorageManagerUtils {

    public static StorageManager instance = StorageManager.getStorageManager();

    /**
     * Método para validar a entrada de um lote
     * @param lotEntryRecord Registo de entrada do lote
     * @param storedLots Lots armazenados para validar
     * @throws InvalidLotExpirationDateException Se a data de expiração do lote for inferior à data atual
     * @throws DuplicateLotEntryException Se o lote já estiver guardado nos storedLots
     * @throws ProductNotFoundException Se o produto do lote não estiver registado no storage manager
     * @throws ProductNotActiveException Se o produto do lote não estiver ativo
     * @throws InvalidMovementMovedQuantityException Se a quantidade movida no registo de entrada de lote for <= 0
     */
    static void validateLotEntry(@NotNull LotEntryRecord lotEntryRecord, @NotNull LinkedList<Lot> storedLots) {

        Lot lot = lotEntryRecord.getLot();

        // Validar data de expiração
        Date expirationDate =  lot.getExpirationDate();
        // Se data de expiração for inferior à data atual, lançar exceção
//        if (expirationDate.calendar.compareTo(Calendar.getInstance()) < 0) {
//            throw new InvalidLotExpirationDateException(expirationDate);
//        }

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

        // Validar quantidade
        validateMovementMovedQuantity(lotEntryRecord.movedAmount);

    }

    /**
     * Método para validar a quantidade de produtos movida em um registo de movimento.
     * @param movedQuantity Quantidade de produtos movida
     * @throws InvalidMovementMovedQuantityException Se a quantidade for menor ou igual a 0.
     */
    static void validateMovementMovedQuantity(int movedQuantity) {
        if (movedQuantity <= 0 ) {
            throw new InvalidMovementMovedQuantityException(movedQuantity);
        }
    }

    /**
     * Método para obter um lote armazenado através do id.
     * Tempo linear O(n).
     * @param id Id a procurar
     * @return Stored lot ou null se não for encontrado
     */
    public static Lot getStoredLotById(@NotNull String id) {
        for (Lot storedLot : instance.getStoredLots()) {
            if (storedLot.getId().equals(id)) {
                return storedLot;
            }
        }

        return null;
    }

    /**
     * Método para procurar um lote armazenado através do resultado do toString() de um lote.
     * Utiliza a função de procura getStoredLotById, logo executa em tempo linear O(n).
     * @param lotString String a ser procurada
     * @return Lote armazenado ou null se não encontrar
     */
    public static Lot getStoredLotByLotString(@NotNull String lotString) {
        // Obter id através da lotString
        StringBuilder id = new StringBuilder();
        for (char c : lotString.toCharArray()) {
            if (c == '(') {
                continue;
            }

            if (c == ')') {
                break;
            }

            id.append(c);
        }

        return getStoredLotById(id.toString());
    }
}
