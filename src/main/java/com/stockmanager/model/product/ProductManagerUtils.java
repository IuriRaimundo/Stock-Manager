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

    /**
     * Método para procurar um produto através do resultado do toString() de um produto
     * @param productString String a ser procurada
     * @return Produto ou null se não encontrar
     */
    public static Product getProductByProductString(String productString) {

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
}
