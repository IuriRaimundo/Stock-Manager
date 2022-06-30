package com.stockmanager.controller.screens;

import com.stockmanager.model.common.IdGenerator;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.view.components.MainBorderPane;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductScreenController implements Initializable {
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, IdGenerator> toID;
    @FXML
    private TableColumn<Product, String> toName;
    @FXML
    private TableColumn<Product, String> toCategory;
    @FXML
    private TableColumn<Product, String> toBrand;
    @FXML
    private TableColumn<Product, String> toPrice;
    @FXML
    private TableColumn<Product, Integer> toProductUnit;
    @FXML
    private TableColumn<Product, String> toState;

    @FXML
    private TextField searchTextField;

    private List<Product> productList;
    private ObservableList<Product> productObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toID.setCellValueFactory(new PropertyValueFactory<>("id"));
        toName.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Construir coluna com a propriedade nome da categoria do produto
        toCategory.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCategory().getName()));

        toBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        toPrice.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPrice() + "€"));
        toProductUnit.setCellValueFactory(new PropertyValueFactory<>("productPricingUnit"));

        // Construir coluna de estado do produto
        toState.setCellValueFactory(p -> {
            Product product = p.getValue();
            if (product.getIsActive()) {
                return new SimpleStringProperty("Ativo");
            } else {
                return new SimpleStringProperty("Inativo");
            }
        });

        // Popular tabela com produtos
        productList = ProductManager.getProductManager().getProducts().values().stream().toList();
        productObservableList = FXCollections.observableArrayList(productList);
        productTableView.setItems(productObservableList);
    }


    public void addProductButton(ActionEvent event){

        Node source = (Node) event.getSource();
        Scene scene = source.getScene();
        Label topBarLabel = (Label) scene.lookup("#topBarLabel");
        MainBorderPane mainBorderPane = (MainBorderPane) scene.lookup("#mainBorderPane");

        try {
            topBarLabel.setText("Adicionar Produto");
            mainBorderPane.getController().openForm("AddProductForm");
        } catch (Exception e) {
            e.printStackTrace();
            mainBorderPane.getController().showError(e);
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

    @FXML
    private void searchButtonActionHandler(ActionEvent event) {

        String needle = searchTextField.getText();

        if (needle.length() == 0) {
            productTableView.setItems(productObservableList);
            return;
        }

        LinkedList<Product> filteredProductList = new LinkedList<>();

        // Filtrar produtos por id, com recurso ao HashMap para fazer procura em tempo constante
        Product product = ProductManagerUtils.getProductById(needle);

        // Se não foi encontrado produto por id, fazer procura por nome
        if (product != null) {
            filteredProductList.add(product);
        } else {
            filteredProductList = productList.stream()
                    .filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(needle.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        // Atualizar tabela
        ObservableList<Product> filteredLotObservableList = FXCollections.observableArrayList(filteredProductList.stream().toList());
        productTableView.setItems(filteredLotObservableList);
    }

}
