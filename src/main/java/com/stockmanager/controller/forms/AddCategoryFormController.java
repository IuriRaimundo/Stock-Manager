package com.stockmanager.controller.forms;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.ProductManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCategoryFormController extends FormController implements Initializable {

    @FXML private TextField categoryNameField;

    public AddCategoryFormController() {
        super("Adicionar Categoria", "CategoryScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFields(List.of(categoryNameField));
    }

    @Override
    public void submitAction() {
        Category newCategory = new Category(
                ProductManager.getProductManager().categoryIdGenerator.generateId(),
                categoryNameField.getText()
        );
        ProductManager.getProductManager().addCategory(newCategory);
    }

}
