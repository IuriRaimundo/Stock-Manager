package com.stockmanager.model.storage;

import com.stockmanager.model.product.Product;

public class Lot {

    private String id;
    private Product product;
    private Date expirationDate;
    private int quantity;

    public Lot(String id, Product product, Date expirationDate, int quantity) {
        this.id = id;
        this.product = product;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
