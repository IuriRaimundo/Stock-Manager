package com.stockmanager.model.product;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import com.stockmanager.model.common.IdGenerator;
import com.stockmanager.model.product.exceptions.*;


public class Product {

    private static final IdGenerator idGenerator = new IdGenerator("P");

    private final String id;
    private final Category category;
    private final ProductPricingUnit productPricingUnit;
    private final String brand;
    private double price;
    private String name;
    private boolean isActive;

    /**
     * @param name Nome do produto
     * @param brand Marca do produto
     * @param category Categoria do produto
     * @param price Preço do produto
     * @param productPricingUnit Undidade de formação de preço do produto
     * @param isActive Estado do produto, verdadeiro = ativo, falso = desativo
     */
    public Product(@NotNull String name,
            @NotNull String brand,
            @NotNull Category category,
            double price,
            @NotNull ProductPricingUnit productPricingUnit,
            boolean isActive)
    {
        this.id =                 idGenerator.generateId();
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
     * @throws InvalidProductNameException Se o nome passado for inválido.
     */
    void setName(@NotNull String name) {
        // Validar comprimento do nome
        int nameLen = name.length();
        if (nameLen < 2 || nameLen > 20) {
            throw new InvalidProductNameException(name);
        }

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
     * @throws UnkownCategory Se o preço for inferior ou igual a 0
     */
    void setPrice(double price) throws UnkownCategory {
        if (price >= 0) {
            throw new InvalidProductPriceException(price);
        }
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
    public Boolean getActive() {
        return isActive;
    }

    /**
     * @param active Novo estado do produto, verdadeiro = ativo, falso = desativado
     */
    void setActive(boolean active) {
        isActive = active;
    }
}
