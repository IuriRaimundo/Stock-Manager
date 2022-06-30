package com.stockmanager.controller.forms;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.model.storage.Lot;
import com.stockmanager.model.storage.ProductIssueRecord;
import com.stockmanager.model.storage.StorageManager;
import com.stockmanager.model.storage.StorageManagerUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterProductIssueFormController extends FormController implements Initializable {
    @FXML private ComboBox<Category> cmbCategory;
    @FXML private ComboBox<Lot> cmbLot;
    @FXML private Spinner<Integer> spnQuantity;

    public RegisterProductIssueFormController() {
        super("Registar Saída de Produto", "LotMovementScreen");
    }

    public RegisterProductIssueFormController(String targetScreen ) {
        super("Registar Saída de Produto", "LotMovementScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFields(Arrays.asList(cmbCategory, cmbLot, spnQuantity));

        //Popular combobox Categorias
        LinkedList<Category> catList = new LinkedList<>(ProductManager.getProductManager().getCategories().values());
        ObservableList<Category> observableCatList = FXCollections.observableArrayList(catList);
        cmbCategory.setItems(observableCatList);
        //Formatar Texto da comboBox Categorias
        Callback<ListView<Category>, ListCell<Category>> categoryComboBoxCellFactory = new Callback<>() {
            @Override
            public ListCell<Category> call(ListView<Category> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText("(" + item.getId() + ") " + item.getName());
                        }
                    }
                };
            }
        };

        cmbCategory.setButtonCell(categoryComboBoxCellFactory.call(null));
        cmbCategory.setCellFactory(categoryComboBoxCellFactory);

        cmbCategory.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category category) {
                if (category == null) {
                    return null;
                } else {
                    return category.getName();
                }
            }

            @Override
            public Category fromString(String catName) {
                return ProductManagerUtils.getCategoryByName(catName);
            }
        });

        // Popular combo box de lotes
        LinkedList<Lot> lotLinkedList = new LinkedList<>(StorageManager.getStorageManager().getStoredLots());
        ObservableList<Lot> observablelotList = FXCollections.observableArrayList(lotLinkedList);
        cmbLot.setItems(observablelotList);

        // Formatar texto da combo box de lotes
        Callback<ListView<Lot>, ListCell<Lot>> lotComboBoxCellFactory = new Callback<>() {
            @Override
            public ListCell<Lot> call(ListView<Lot> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(Lot item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        };

        cmbLot.setButtonCell(lotComboBoxCellFactory.call(null));
        cmbLot.setCellFactory(lotComboBoxCellFactory);
        cmbLot.setConverter(new StringConverter<>() {
            @Override
            public String toString(Lot lot) {
                if (lot == null) {
                    return null;
                } else {
                    return lot.toString();
                }
            }

            @Override
            public Lot fromString(String lotstring) {
                return StorageManagerUtils.getStoredLotByLotString(lotstring);
            }
        });

    }

    @Override
    public void submitAction() {
        // Criar registo de saída de produtos
        ProductIssueRecord productIssueRecord = new ProductIssueRecord(
                StorageManager.getStorageManager().movementRecordIdGenerator.generateId(),
                cmbLot.getValue(), spnQuantity.getValue()
        );

        // Adicionar registo ao StorageManager
        StorageManager.getStorageManager().registerProductIssue(productIssueRecord);
    }
}
