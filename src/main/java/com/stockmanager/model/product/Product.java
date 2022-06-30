package com.stockmanager.model.product;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    public static final String ID_PREFIX = "P";

    private final String id;
    private final Category category;
    private final ProductPricingUnit productPricingUnit;
    private final String brand;
    private double price;
    private String name;
    private boolean isActive;

    /**
     * @param id Id do produto
     * @param name Nome do produto
     * @param brand Marca do produto
     * @param category Categoria do produto
     * @param price Preço do produto
     * @param productPricingUnit Undidade de formação de preço do produto
     * @param isActive Estado do produto, verdadeiro = ativo, falso = desativo
     */
    public Product(
            @NotNull String id,
            @NotNull String name,
            @NotNull String brand,
            @NotNull Category category,
            double price,
            @NotNull ProductPricingUnit productPricingUnit,
            boolean isActive)
    {
        this.id =                 id;
        this.brand =              Objects.requireNonNull(brand);
        this.category =           Objects.requireNonNull(category);
        this.productPricingUnit = Objects.requireNonNull(productPricingUnit);

        setName(Objects.requireNonNull(name));
        setPrice(price);
        setActive(isActive);
    }

    /**
     * @return Id do produto
     */
    public String getId() {
        return id;
    }

    /**
     * @return Nome do produto
     */
    public String getName() {
        return name;
    }

    /**
     * @return Marca do produto
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param name Novo nome do produto
     */
    void setName(@NotNull String name) {
        this.name = name;
    }

    /**
     * @return Categoria do produto
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @return Preço do produto por unidade
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Novo preço
     */
    void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return Método de formação de preço
     */
    public ProductPricingUnit getProductPricingUnit() {
        return productPricingUnit;
    }

    /**
     * @return Estado do produto, verdadeiro se está ativo e falso se estiver desativado.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param active Novo estado do produto, verdadeiro = ativo, falso = desativado
     */
    void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name + ", " + brand;
    }
}
