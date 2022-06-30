package com.stockmanager.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe de utilidade para o ProductManager
 */
abstract public class ProductManagerUtils {

    public static ProductManager instance = ProductManager.getProductManager();

    /**
     * Método para procurar uma categoria pelo nome, em tempo linear O(n).
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

    /**
     * Método para procurar um produto pelo id, em tempo constante O(1).
     * @param id Id a ser procurado
     */
    public static Product getProductById(String id) {
        return instance.getProducts().get(id);
    }

    /**
     * Método para procurar um produto através do resultado do toString() de um produto, em tempo linear O(n)
     * @param productString String a ser procurada
     * @return Produto ou null se não encontrar
     */
    public static Product getProductByProductString(@NotNull String productString) {
        // Obter id através da productString
        StringBuilder id = new StringBuilder();
        for (char c : productString.toCharArray()) {
            if (c == '(') {
                continue;
            }

            if (c == ')') {
                break;
            }

            id.append(c);
        }

        return instance.getProducts().get(id.toString());
    }

    /**
     * Método para obter uma lista de produtos de uma determinada categoria.
     * Tempo linear O(n).
     * @param category Categoria a procurar
     * @return Lista de produtos com a categoria passada por parametro
     */
    public static LinkedList<Product> getProductsByCategory(@NotNull Category category) {
        return instance.getProducts().values().stream()
                .filter(p -> p.getCategory() == category)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
