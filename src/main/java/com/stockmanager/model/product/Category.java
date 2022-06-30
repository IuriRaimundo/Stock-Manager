package com.stockmanager.model.product;

import org.jetbrains.annotations.NotNull;

import com.stockmanager.model.common.IdGenerator;
import com.stockmanager.model.product.exceptions.InvalidCategoryNameException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    public static final String ID_PREFIX = ("C");

    private final String id;
    private String name;

    /**
     * @param id Id da categoria
     * @param name Nome da categoria
     */
    public Category(@NotNull String id, @NotNull String name) {
        this.id = id;
        setName(Objects.requireNonNull(name));
    }

    /**
     * @return Id da categoria
     */
    public String getId() {
        return id;
    }

    /**
     * @return Nome da categoria
     */
    public String getName() {
        return name;
    }

    /**
     * Método para definir o nome da categoria
     * @param name Nome da categoria
     * @throws InvalidCategoryNameException Se o nome for inválido
     */
    private void setName(@NotNull String name) throws InvalidCategoryNameException {
        // Validar comprimento do nome
        int nameLen = name.length();
        if (nameLen < 2 || nameLen > 20) {
            throw new InvalidCategoryNameException(name);
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name;
    }
}
