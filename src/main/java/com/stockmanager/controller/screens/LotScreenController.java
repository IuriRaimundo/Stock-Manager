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

                    // Se o período entre a data de expiração e a data atual for menor de 30 dias destacar célula.
                    if (daysBetweenExpDateAndNow < 30) {
                        setStyle("-fx-text-fill: #C69835");
                    } else {
                        setStyle("-fx-text-fill: #000000");
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

    @FXML
    private void registerEntryStockButton(ActionEvent event){
        Node source = (Node) event.getSource();

        Scene scene = source.getScene();

        Label topBarLabel = (Label) scene.lookup("#topBarLabel");

        MainBorderPane mainBorderPane = (MainBorderPane) scene.lookup("#mainBorderPane");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("com/prototipo/prototipomsi/screens/RegistarEntradaDeLote.fxml"));
            if (topBarLabel != null) {
                topBarLabel.setText("Registar Entrada de Lote");
            }
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
            mainBorderPane.getController().showError(e);
        }

    }

    @FXML
    private void registerExitStockButton(ActionEvent event){
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

    @FXML
    private void registerBrokenStockButton(ActionEvent actionEvent) {
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

    @FXML
    private void searchButtonActionHandler(ActionEvent event) {

        String needle = searchTextField.getText();

        if (needle.length() == 0) {
            lotTable.setItems(lotObservableList);
            return;
        }

        // Filtrar lista por id de lote, id de produto, ou por nome do produto
        LinkedList<Lot> filteredLotLinkedList = lotLinkedList.stream()
                .filter(lot -> lot.getId().equals(needle) ||
                        lot.getProduct().getId().equals(needle) ||
                        lot.getProduct().getName().toLowerCase(Locale.ROOT)
                                .contains(needle.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toCollection(LinkedList::new));

        // Atualizar tabela
        ObservableList<Lot> filteredLotObservableList = FXCollections.observableArrayList(filteredLotLinkedList.stream().toList());

        lotTable.setItems(filteredLotObservableList);
    }




}

