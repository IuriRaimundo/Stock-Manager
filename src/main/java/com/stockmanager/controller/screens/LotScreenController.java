package com.stockmanager.controller.screens;

import com.stockmanager.model.product.Product;
import com.stockmanager.model.storage.Lot;
import com.stockmanager.model.storage.StorageManager;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class LotScreenController implements Initializable {

    @FXML
    private TableView<Lot> lotTable;
    @FXML
    private TableColumn<Lot, String> toID;
    @FXML
    private TableColumn<Lot, String> toProduct;
    @FXML
    private TableColumn<Lot, Integer> toQuantity;
    @FXML
    private TableColumn<Lot, String> toDate;

    @FXML
    private TextField searchTextField;

    private LinkedList<Lot> lotLinkedList;
    private ObservableList<Lot> lotObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toID.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getId()));

        // Formatar célula de produto neste formato: (PXX) XXXXX
        toProduct.setCellValueFactory(p -> {
            Product product = p.getValue().getProduct();
            String output = "(" + product.getId() + ") " + product.getName();

            return new SimpleStringProperty(output);

        });

        toQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        toDate.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getExpirationDate().toString()));

        // Modificar estilo da célula da data de expiração
        toDate.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item,  empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {

                    long expDateMs = 0;

                    try {
                        expDateMs = new SimpleDateFormat("dd/MM/yyyy").parse(item).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar now = Calendar.getInstance();
                    long nowInMilisecs = now.getTimeInMillis();

                    long daysBetweenExpDateAndNow = TimeUnit.MILLISECONDS.toDays(expDateMs - nowInMilisecs);

                    if (daysBetweenExpDateAndNow < 30) {
                        setStyle("-fx-text-fill: #C69835");
                    }

                    setText(item);
                }

            }
        });


        // Obter lotes e atualizar tabela
        lotLinkedList = StorageManager.getStorageManager().getStoredLots();

        // Converter lista para uma observable list
        lotObservableList =
                FXCollections.observableArrayList(lotLinkedList.stream().toList());

        lotTable.setItems(lotObservableList);
    }

    public void registerLotEntryBtn(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("RegisterLotEntryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    public void registerProductIssueBtn(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("RegisterProductIssueForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }
    }

    public void registerProductBreakageBtn(ActionEvent actionEvent) {
        try {
            MainBorderPane.controller.openForm("RegisterBreakageProductForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }
    }


}

