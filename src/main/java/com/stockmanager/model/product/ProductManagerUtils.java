package com.stockmanager.model.product;

/**
 * Classe de utilidade para o ProductManager
 */
abstract public class ProductManagerUtils {

    public static ProductManager instance = ProductManager.getProductManager();

    /**
     * Método para procurar uma categoria pelo nome
     * @param name Nome a ser procurado
     * @return Categoria ou null se não encontrar
     */
    public static Category getCategoryByName(String name) {
        for (Category c : instance.getCategories().values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }
}
