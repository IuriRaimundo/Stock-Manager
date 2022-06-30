package com.stockmanager.controller.screens;
import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.storage.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LotMovementScreenController implements Initializable {

    @FXML
    private TableView<MovementRecord> toMovementLot;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        // Ordenar lista por data, por ordem crescente
        movementRecords.sort(new Comparator<MovementRecord>() {
            @Override
            public int compare(MovementRecord o1, MovementRecord o2) {
                return (int) (o1.getMovementDate().getCalendar().getTimeInMillis() - o2.getMovementDate().getCalendar().getTimeInMillis());
            }
        });

        ObservableList<MovementRecord> categoryList =
                FXCollections.observableArrayList(movementRecords);

        toMovementLot.setItems(categoryList);
    }



    public void registerEntryStockButton(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("RegisterLotEntryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    public void registerExitStockButton(ActionEvent event){
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


}
