package com.stockmanager.controller.screens;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.view.components.MainBorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.MissingFormatArgumentException;
import java.util.ResourceBundle;

public class CategoryScreenController implements Initializable {
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Category> toCategory;
    @FXML
    private TableColumn<Category, String> toId;
    @FXML
    private TableColumn<Category, String> toName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toId.setCellValueFactory(new PropertyValueFactory<>("id"));
        toName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<Category> categoryList =
                FXCollections.observableArrayList(ProductManager.getProductManager().getCategories().values());

        toCategory.setItems(categoryList);
    }

    public void addCategoryButton(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("AddCategoryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    public void removeCategoryButton(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("RemoveCategoryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }
}
