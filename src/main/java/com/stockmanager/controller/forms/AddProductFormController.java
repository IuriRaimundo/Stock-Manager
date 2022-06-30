package com.stockmanager.controller.forms;

import com.stockmanager.model.product.*;

import com.stockmanager.view.forms.NumberTextField;
import com.stockmanager.view.forms.exceptions.InvalidNumericInputException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.*;

public class AddProductFormController extends FormController implements Initializable {

    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField brandTextField;
    @FXML
    private NumberTextField priceTextField;
    @FXML
    private ComboBox<ProductPricingUnit> pricingUnitComboBox;
    @FXML
    private CheckBox activeCheckbox;



    public AddProductFormController() {
        super("Adicionar Produto", "ProductScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Definir campos a ser verificados pelo isFormFilled
        setFields(Arrays.asList(categoryComboBox, nameTextField, brandTextField, priceTextField, pricingUnitComboBox, activeCheckbox));

        // Popular combo box de categorias
        LinkedList<Category> catList = new LinkedList<>(ProductManager.getProductManager().getCategories().values());
        ObservableList<Category> observableCatList = FXCollections.observableArrayList(catList);
        categoryComboBox.setItems(observableCatList);

        Callback<ListView<Category>, ListCell<Category>> categoryComboBoxCellFactory = new Callback<>() {
            @Override
            public ListCell<Category> call(ListView<Category> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText("(" + item.getId() + ") " + item.getName());
                        }
                    }
                };
            }
        };

        categoryComboBox.setButtonCell(categoryComboBoxCellFactory.call(null));
        categoryComboBox.setCellFactory(categoryComboBoxCellFactory);

        categoryComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category category) {
                if (category == null) {
                    return null;
                } else {
                    return category.getName();
                }
            }

            @Override
            public Category fromString(String catName) {
                return ProductManagerUtils.getCategoryByName(catName);
            }
        });

        // Popular combo box de método de formação de preço
        ObservableList<ProductPricingUnit> observableProductPricingUnitList
                = FXCollections.observableArrayList(ProductPricingUnit.values());
        pricingUnitComboBox.setItems(observableProductPricingUnitList);

        pricingUnitComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<ProductPricingUnit> call(ListView<ProductPricingUnit> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(ProductPricingUnit item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText("(" + item + ") " + item.unitName);
                        }
                    }
                };
            }
        });

        pricingUnitComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(ProductPricingUnit productPricingUnit) {
                if (productPricingUnit == null) {
                    return null;
                } else {
                    return productPricingUnit.id;
                }
            }

            @Override
            public ProductPricingUnit fromString(String id) {
                return ProductPricingUnit.getProductPricingUnitById(id);
            }
        });
    }

    @Override
    public void submitAction() {

        // Obter preço do produto e converter para double.
        double productPrice;

        try {
            productPrice = Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException e) {
            throw new InvalidNumericInputException();
        }

        // Criar objeto produto
        Product newProduct = new Product(nameTextField.getText(),
                brandTextField.getText(),
                categoryComboBox.getValue(),
                productPrice,
                pricingUnitComboBox.getValue(),
                activeCheckbox.isSelected());

        // Adicionar produto ao product manager e voltar para o ecrã dos produtos
        ProductManager.getProductManager().addProduct(newProduct);
    }

}
