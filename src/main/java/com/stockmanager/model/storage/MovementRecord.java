package com.stockmanager.model.storage;

import com.stockmanager.model.common.IdGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

abstract public class MovementRecord {

    private static final IdGenerator idGenerator = new IdGenerator("M");

    protected final String id;
    protected final Date movementDate;
    protected final Lot lot;
    protected final int movedAmount;

    /**
     * @param lot Lote do movimento
     * @param movedAmount Quantidade de produtos movida
     */
    public MovementRecord(@NotNull Lot lot,
                          int movedAmount)
    {
        this.id =           idGenerator.generateId();
        this.movementDate = new Date();
        this.lot =          Objects.requireNonNull(lot);
        this.movedAmount =  movedAmount;
    }

    /**
     * @return Id do movimento
     */
    public String getId() {
        return id;
    }

    /**
     * @return Data de ocorrência do movimento
     */
    public Date getMovementDate() {
        return movementDate;
    }

    /**
     * @return Lote associado ao movimento
     */
    public Lot getLot() {
        return lot;
    }

    /**
     * @return Quantidade de produtos movida
     */
    public int getMovedAmount() {
        return movedAmount;
    }
}
