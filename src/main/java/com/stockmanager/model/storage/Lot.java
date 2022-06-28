package com.stockmanager.model.storage;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import com.stockmanager.model.common.IdGenerator;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.storage.exceptions.*;


public class Lot implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final IdGenerator idGenerator = new IdGenerator("L");

    private final String id;
    private final Product product;
    private final Date expirationDate;
    private int quantity;


    /**
     * @param product Produto do lote
     * @param expirationDate Data de validade do lote
     * @param quantity Quantidade de produtos no lote
     */
    public Lot(@NotNull Product product,
               @NotNull Date expirationDate,
               int quantity) {
        this.id =             idGenerator.generateId();
        this.product =        Objects.requireNonNull(product);
        this.expirationDate = Objects.requireNonNull(expirationDate);
        setQuantity(quantity);
    }

    /**
     * @return ID do lote
     */
    public String getId() {
        return id;
    }

    /**
     * @return Produto associado ao lote
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return Data de expiração dos produtos do lote
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @return Quantidade de produtos do lote
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity Quantidade de produtos do lote
     * @throws InvalidLotQuantityException Se a quantidade de produtos do lote for negativa
     */
    private void setQuantity(int quantity) throws InvalidLotQuantityException {
        if (quantity < 0) {
            throw new InvalidLotQuantityException(quantity);
        } else {
            this.quantity = quantity;
        }
    }

    /**
     * Método para subtrair uma quantidade de produtos da quantidade do lote
     * @param quantityToSubtract Quantidade de produtos a ser subtraida
     * @throws InvalidLotQuantityException Se a quantidade de produtos após subtração for menor que 0
     */
    void subtractQuantity(int quantityToSubtract) throws InvalidLotQuantityException {
        int newQuantity = this.quantity - quantityToSubtract;
        setQuantity(newQuantity);
    }
}
