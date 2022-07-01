package com.stockmanager.controller.forms;

import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.view.forms.exceptions.InvalidNumericInputException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import java.net.URL;
import java.util.*;

public class AdjustProductFormController extends FormController implements Initializable {
    public ComboBox<Product> cmbProduct;
    public TextField txtName;
    public TextField txtPrice;
    public CheckBox checkBox;

    public AdjustProductFormController() {
        super("Ajustar Produto", "ProductScreen");
    }

    @Override
    public void submitAction() {
        // Obter preço do produto e converter para double.
        double productPrice;

        try {
            productPrice = Double.parseDouble(txtPrice.getText());
        } catch (NumberFormatException e) {
            throw new InvalidNumericInputException();
        }
        Product product = cmbProduct.getValue();
        product.setName(txtName.getText());
        product.setPrice(productPrice);
        product.setActive(checkBox.isSelected());

        ProductManager.getProductManager().updateProduct(product);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inicializar Componentes
        setFields(Arrays.asList(cmbProduct, txtName, txtPrice, checkBox));
        // Popular combo box de produtos
        LinkedList<Product> prodList = new LinkedList<>(ProductManager.getProductManager().getProducts().values());
        ObservableList<Product> observableprodList = FXCollections.observableArrayList(prodList);
        cmbProduct.setItems(observableprodList);

        // Formatar texto da combo box de  produtos
        Callback<ListView<Product>, ListCell<Product>> productComboBoxCellFactory = new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        };

        cmbProduct.setButtonCell(productComboBoxCellFactory.call(null));
        cmbProduct.setCellFactory(productComboBoxCellFactory);
        cmbProduct.setConverter(new StringConverter<>() {
            @Override
            public String toString(Product product) {
                if (product == null) {
                    return null;
                } else {
                    return product.toString();
                }
            }

            @Override
            public Product fromString(String productString) {
                return ProductManagerUtils.getProductByProductString(productString);
            }
        });

    }
}
