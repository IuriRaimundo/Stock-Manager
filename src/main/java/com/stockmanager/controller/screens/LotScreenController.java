package com.stockmanager.controller.screens;

import com.stockmanager.model.storage.Lot;
import com.stockmanager.model.storage.StorageManager;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LotScreenController implements Initializable {
    @FXML
    private TableView<Lot> LotTable;
    @FXML
    private TableColumn<Lot, String> toID;
    @FXML
    private TableColumn<Lot, String> toProductName;
    @FXML
    private TableColumn<Lot, Integer> toQuantity;
    @FXML
    private TableColumn<Lot, String> toDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toID.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getId()));
        toProductName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProduct().getName()));
        toQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        toDate.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getExpirationDate().toString()));

        ObservableList<Lot> lotList =
                FXCollections.observableArrayList(StorageManager.getStorageManager().getStoredLots().stream().toList());

        LotTable.setItems(lotList);
    }


    public void registerEntryStockButton(ActionEvent event){
        Node source = (Node) event.getSource();

        Scene scene = source.getScene();

        Label topBarLabel = (Label) scene.lookup("#topBarLabel");

        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("com/prototipo/prototipomsi/screens/RegistarEntradaDeLote.fxml"));
            if (topBarLabel != null) {
                topBarLabel.setText("Registar Entrada de Lote");
            }
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void registerExitStockButton(ActionEvent event){
        Node source = (Node) event.getSource();

        Scene scene = source.getScene();

        Label topBarLabel = (Label) scene.lookup("#topBarLabel");

        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/prototipo/prototipomsi/screens/RegistarSaidaDeProdutos.fxml"));
            mainBorderPane.setCenter(fxmlLoader.load());
            if (topBarLabel != null) {
                topBarLabel.setText("Registar Saída de Produtos");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void registerBrokenStockButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();

        Scene scene = source.getScene();

        Label topBarLabel = (Label) scene.lookup("#topBarLabel");

        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/prototipo/prototipomsi/screens/RegistarQuebraDeProduto.fxml"));
            mainBorderPane.setCenter(fxmlLoader.load());
            if (topBarLabel != null) {
                topBarLabel.setText("Registar Quebra de Produto");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

