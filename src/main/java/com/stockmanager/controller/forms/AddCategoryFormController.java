package com.stockmanager.controller.forms;

import com.stockmanager.controller.forms.FormController;
import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.ProductManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
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
        Category newCategory = new Category(categoryNameField.getText());
        ProductManager.getProductManager().addCategory(newCategory);
    }

}
