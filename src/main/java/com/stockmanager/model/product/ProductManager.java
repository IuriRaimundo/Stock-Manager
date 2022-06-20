package com.stockmanager.model.product;

import com.stockmanager.model.product.exceptions.ProductNotFound;
import org.jetbrains.annotations.NotNull;

import com.stockmanager.model.product.exceptions.*;
import com.stockmanager.model.utils.HashMapUtils;
import com.stockmanager.model.utils.exceptions.*;

import java.util.HashMap;
import java.util.Objects;

public class ProductManager {

    // Categorias registadas, a chave do map é o id da categoria.
    private final HashMap<String, Category> categories;
    // Produtos registados, a chave do map é o id do produto.
    private final HashMap<String, Product> products;

    public static final ProductManager instance = new ProductManager();

    public static ProductManager getProductManager() {
        return instance;
    }

    private ProductManager() {
        categories = new HashMap<>();
        products = new HashMap<>();
    }

    /**
     * @return Cópia do mapa de categorias
     */
    public HashMap<String, Category> getCategories() {
        return new HashMap<>(categories);
    }

    /**
     * Método para adicionar uma categoria
     * @param category Categoria a ser adicionada
     * @throws DuplicateKeyException Se o id da categoria não for único
     * @throws DuplicateValueException Se a categoria já estiver registada
     */
    public void addCategory(@NotNull Category category) throws DuplicateKeyException {
        // Registar categoria
        HashMapUtils.hashMapUniqueInsertion(
                categories,
                category.getId(),
                category);
    }

    /**
     * Método para remover uma categoria das categorias registadas
     * @param category Categoria a ser removida
     * @throws CategoryNotFoundException Se a categoria não estiver registada
     * @throws InvalidCategoryDeletion Se a categoria for usada por um produto registado.
     */
    public void removeCategory(@NotNull Category category) {
        // Verificar se a categoria está registada
        if (categories.get(category.getId()) == null) {
            throw new CategoryNotFoundException(category.getId());
        }

        // Verificar se não existem produtos associados
        for (Product product : products.values()) {
            if (product.getCategory() == category) {
                throw new InvalidCategoryDeletion(category);
            }
        }

        // Remover categoria
        categories.remove(category.getId());
    }

    /**
     * @return Cópia do mapa de produtos
     */
    public HashMap<String, Product> getProducts() {
        return new HashMap<>(products);
    }

    /**
     * Método para adicionar um produto
     * @param product Produto a ser adicionado
     * @throws DuplicateKeyException Se o id do produto não for único.
     * @throws DuplicateValueException Se o produto já estiver registado
     * @throws UnkownCategory Se a categoria do produto não estiver registada
     */
    public void addProduct(@NotNull Product product) throws DuplicateKeyException {

        // Verificar se a categoria do produto se encontra nas categorias registadas no ProductManager
        if (categories.get(product.getCategory().getId()) == null) {
            throw new UnkownCategory(product.getCategory());
        }

        // Registar produto
        HashMapUtils.hashMapUniqueInsertion(
                products,
                product.getId(),
                product);
    }

    /**
     * Método para atualizar um produto
     * @param productId ID do produto a ser atualizado
     * @param newPrice Novo preço do produto
     * @param newName Novo nome do produto
     * @param isActive Novo estado do produto
     * @throws ProductNotFound Se não existir um produto com o productId passado no mapa de produtos.
     * @throws InvalidProductPriceException Se o preço passado por parâmetro for inválido
     * @throws InvalidProductNameException Se o nome passado por parâmetro for inválido
     */
    public void updateProduct(@NotNull String productId,
                              double newPrice,
                              @NotNull String newName,
                              boolean isActive)
        throws ProductNotFound
    {
        // Obter produto a ser atualizado
        Product productToUpdate = products.get(productId);

        if (productToUpdate == null) {
            throw new ProductNotFound(productId);
        }

        // Atualizar produto
        productToUpdate.setPrice(newPrice);
        productToUpdate.setName(Objects.requireNonNull(newName));
        productToUpdate.setActive(isActive);
    }
}
