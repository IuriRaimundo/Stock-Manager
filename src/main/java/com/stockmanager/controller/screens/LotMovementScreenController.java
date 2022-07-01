package com.stockmanager.controller.screens;

import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.model.storage.*;
import com.stockmanager.view.components.MainBorderPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class LotMovementScreenController implements Initializable {

    @FXML
    private TableView<MovementRecord> movementRecordTableView;

    @FXML
    private TableColumn<MovementRecord, String> toMovementId;
    @FXML
    private TableColumn<MovementRecord, String> toType;
    @FXML
    private TableColumn<MovementRecord, String> toLot;
    @FXML
    private TableColumn<MovementRecord, String> toProduct;
    @FXML
    private TableColumn<MovementRecord, String> toQuantity;
    @FXML
    private TableColumn<MovementRecord, String> toDate;

    @FXML
    private TextField searchTextField;

    private List<MovementRecord> movementRecordList;
    private ObservableList<MovementRecord> movementRecordObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toMovementId.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getId()));

        toType.setCellValueFactory(p -> {
            MovementRecord movementRecord = p.getValue();
            String type;

            if (movementRecord instanceof LotEntryRecord) {
                type = "Entrada";
            } else if (movementRecord instanceof ProductIssueRecord) {
                type = "Saída";
            } else {
                type = "Quebra";
            }

            return new SimpleStringProperty(type);
        });

        toLot.setCellValueFactory(p -> {
            String id = p.getValue().getLot().getId();
            return new SimpleStringProperty(id);

        });

        toProduct.setCellValueFactory(p -> {
            Product product = p.getValue().getLot().getProduct();
            String output = "(" + product.getId() + ") " + product.getName();
            return new SimpleStringProperty(output);
        });
        toQuantity.setCellValueFactory(new PropertyValueFactory<>("movedAmount"));
        toDate.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getMovementDate().toString()));


        // Juntar listas de movement records
        StorageManager storageManager = StorageManager.getStorageManager();

        LinkedList<MovementRecord> movementRecords = new LinkedList<>();

        movementRecords.addAll(storageManager.getLotEntryRecords());
        movementRecords.addAll(storageManager.getProductIssueRecords());
        movementRecords.addAll(storageManager.getProductBreakageRecords());

        // Popular tabela
        movementRecordList = movementRecords.stream().toList();
        movementRecordObservableList = FXCollections.observableArrayList(movementRecords);
        movementRecordTableView.setItems(movementRecordObservableList);

        // Ordenar tabela por id de movimento
        toMovementId.setSortType(TableColumn.SortType.DESCENDING);
        movementRecordTableView.getSortOrder().add(toMovementId);
        movementRecordTableView.sort();
    }


    public void registerEntryStockButton(ActionEvent event) {
        try {
            MainBorderPane.controller.openForm("RegisterLotEntryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    public void registerExitStockButton(ActionEvent event) {
        try {
            MainBorderPane.controller.openForm("RegisterProductIssueForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    public void registerBrokenStockButton(ActionEvent actionEvent) {
        try {
            MainBorderPane.controller.openForm("RegisterBreakageProductForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }
    }

    @FXML
    private void searchButtonActionHandler(ActionEvent event) {

        String needle = searchTextField.getText();

        if (needle.length() == 0) {
            movementRecordTableView.setItems(movementRecordObservableList);
            return;
        }

        // Filtrar lista por id de lote, id de produto e nome do produto
        LinkedList<MovementRecord> filteredMovementRecordList = movementRecordList.stream()
                .filter(m -> {
                    Lot lot = m.getLot();
                    String lotId = lot.getId();
                    String productId = lot.getProduct().getId();
                    String productName = lot.getProduct().getName();
                    return lotId.equals(needle) ||
                            productId.equals(needle) ||
                            productName.toLowerCase(Locale.ROOT).contains(needle.toLowerCase(Locale.ROOT));

                })
                .collect(Collectors.toCollection(LinkedList::new));

        // Atualizar tabela
        ObservableList<MovementRecord> filteredMovementRecordsObservableList
                = FXCollections.observableArrayList(filteredMovementRecordList.stream().toList());

        movementRecordTableView.setItems(filteredMovementRecordsObservableList);
    }
}


