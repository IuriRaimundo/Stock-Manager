package com.stockmanager.controller.screens;

import com.stockmanager.model.common.IdGenerator;
import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductPricingUnit;
import com.stockmanager.model.storage.StorageManager;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductScreenController implements Initializable {
    @FXML
    private TableView<Product> toProduct;
    @FXML
    private TableColumn<Product, IdGenerator> toID;
    @FXML
  private TableColumn<Product, String> toName;
    @FXML
    private TableColumn<Product, Category> toCategory;
    @FXML
    private TableColumn<Product, String>toBrand;
    @FXML
    private TableColumn<Product, Double> toPrice;
    @FXML
    private TableColumn<Product, Integer>toUnity;
    @FXML
    private TableColumn<Product, String> toState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toName.setCellValueFactory(new PropertyValueFactory<>("name"));
        toCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        toBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        toPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        toUnity.setCellValueFactory(new PropertyValueFactory<>("productPricingUnit"));
        toState.setCellValueFactory(new PropertyValueFactory<>("isActive"));


        ObservableList<Product> productList =
                FXCollections.observableArrayList(ProductManager.getProductManager().getProducts().values());

        toProduct.setItems(productList);
    }


    public void addProductButton(ActionEvent event){
        Node source = (Node) event.getSource();
        Scene scene = source.getScene();
        Label topBarLabel = (Label) scene.lookup("#topBarLabel");
        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/prototipo/prototipomsi/screens/addProductForm.fxml"));
            if (topBarLabel != null) {
                topBarLabel.setText("Adicionar Produto");
            }
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void adjustProductButton(ActionEvent event){
        Node source = (Node) event.getSource();

        Scene scene = source.getScene();

        Label topBarLabel = (Label) scene.lookup("#topBarLabel");

        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/prototipo/prototipomsi/screens/adjustProductForm.fxml"));
            mainBorderPane.setCenter(fxmlLoader.load());
            if (topBarLabel != null) {
                topBarLabel.setText("Ajustar Produto");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
