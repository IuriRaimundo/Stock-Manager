package com.stockmanager.model.product;

import com.stockmanager.model.common.ManagerDataLoader;
import com.stockmanager.model.product.exceptions.ProductNotFoundException;
import org.jetbrains.annotations.NotNull;

import com.stockmanager.model.product.exceptions.*;
import com.stockmanager.model.utils.HashMapUtils;
import com.stockmanager.model.utils.exceptions.*;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class ProductManager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String PRODUCT_MANAGER_DATAFILE = "ProductManager.dat";

    // Categorias registadas, a chave do map é o id da categoria.
    private final HashMap<String, Category> categories;
    // Produtos registados, a chave do map é o id do produto.
    private final HashMap<String, Product> products;

    public static ManagerDataLoader<ProductManager> dataLoader = new ManagerDataLoader<>(PRODUCT_MANAGER_DATAFILE);

    private static ProductManager instance;

    static {
        try {
            instance = dataLoader.initialize();

            if (instance == null) {
                instance = new ProductManager();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
     * @throws InvalidCategoryNameException Se o nome de categoria for inválido
     */
    public void addCategory(@NotNull Category category) throws DuplicateKeyException {

        // Validar categoria
        validateNewCategory(category);

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
     * @throws InvalidCategoryDeletionException Se a categoria for usada por um produto registado.
     */
    public void removeCategory(@NotNull Category category) {
        // Verificar se a categoria está registada
        if (categories.get(category.getId()) == null) {
            throw new CategoryNotFoundException(category.getId());
        }

        // Verificar se não existem produtos associados
        for (Product product : products.values()) {
            if (product.getCategory() == category) {
                throw new InvalidCategoryDeletionException(category);
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
     * @throws UnkownCategoryException Se a categoria do produto não estiver registada
     */
    public void addProduct(@NotNull Product product) throws DuplicateKeyException {

        // Validar produto
        validateProduct(product);

        // Registar produto
        HashMapUtils.hashMapUniqueInsertion(
                products,
                product.getId(),
                product);
    }

    /**
     * Método para atualizar um produto
     * @param product Produto a ser atualizado
     * @throws ProductNotFoundException Se não existir um produto com o productId passado no mapa de produtos.
     * @throws InvalidProductPriceException Se o preço passado por parâmetro for inválido
     * @throws InvalidProductNameException Se o nome passado por parâmetro for inválido
     */
    public void updateProduct(@NotNull Product product)
        throws ProductNotFoundException
    {
        // Obter produto a ser atualizado, se não for encontrado levantar exceção
        Product productToUpdate = products.get(product.getId());

        if (productToUpdate == null) {
            throw new ProductNotFoundException(product.getId());
        }

        // Validar produto
        validateProduct(product);

        // Atualizar produto
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setName(product.getName());
        productToUpdate.setActive(product.getIsActive());
    }

    /**
     * Função para validar o preço de um produto
     * @param price Preço a validar
     * @throws InvalidProductPriceException Se o preço for igual ou inferior a 0
     */
    public void validateProductPrice(double price) {
        if (price <= 0) {
            throw new InvalidProductPriceException(price);
        }
    }

    /**
     * Função para validar a categoria de um produto.
     * @param category Categoria a ser validada
     * @throws UnkownCategoryException Se a categoria não estiver registada.
     */
    public void validateProductCategory(Category category) {
        // Verificar se a categoria do produto se encontra nas categorias registadas no ProductManager
        if (categories.get(category.getId()) == null) {
            throw new UnkownCategoryException(category);
        }
    }

    /**
     * Função para validar um produto completo
     * @throws DuplicateKeyException Se o produto já se encontrar registado.
     * @throws InvalidProductNameException Se o nome de produto for inválido.
     * @throws InvalidProductBrandException Se a marca de produto for inválida.
     */
    public void validateProduct(Product product) {

        // Verificar se o produto já se encontra registado
        if (products.get(product.getId()) != null) {
            throw new DuplicateKeyException();
        }

        // Validar categoria
        validateProductCategory(product.getCategory());

        // Validar preço
        validateProductPrice(product.getPrice());

        // Validar nome
        if (product.getName().length() < 2 || product.getName().length() > 20) {
            throw new InvalidProductNameException(product.getName());
        }

        // Validar marca
        if (product.getBrand().length() < 2 || product.getBrand().length() > 20) {
            throw new InvalidProductBrandException(product.getBrand());
        }
    }

    /**
     * Função para validar uma nova categoria, levanta exceções quando alguma regra de modelo de dados é violada.
     * @param category Categoria a validar
     * @throws DuplicateKeyException Se a categoria já estiver indexada no hashmap de categorias
     * @throws InvalidCategoryNameException Se o nome da categoria já existir
     */
    public void validateNewCategory(Category category) {
        // Verificar se a categoria do produto se encontra nas categorias registadas no ProductManager
        if (categories.get(category.getId()) != null) {
            throw new DuplicateKeyException();
        }

        // Verificar se o nome da categoria é único
        for (Category c : categories.values()) {
            if (c.getName().equals(category.getName())) {
                throw new InvalidCategoryNameException(c.getName());
            }
        }
    }

    /**
     * Função para verificar se um determinado produto está registado
     * @param product Produto a ser verificado
     * @return Verdadeiro se existe, falso se não.
     */
    public boolean productExists(Product product) {
        return products.get(product.getId()) != null;
    }
}
