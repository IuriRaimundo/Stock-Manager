package com.stockmanager.controller.forms;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.model.storage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class RegisterBreakageProductFormController extends FormController implements Initializable {

    @FXML private TextField txtObservation;
    @FXML private ComboBox<Category> cmbCategory;
    @FXML private ComboBox<Lot> cmbLot;
    @FXML private Spinner<Integer> spnQuantity;
    @FXML private ComboBox<ProductBreakageReason> cmbReason;

    public RegisterBreakageProductFormController() {
        super("Registar quebra de produto", "LotMovementScreen");
    }

    @Override
    public void submitAction() {
        ProductBreakageRecord productBreakageRecord = new ProductBreakageRecord(cmbLot.getValue(),spnQuantity.getValue(),cmbReason.getValue(),txtObservation.getText()); //todo
        StorageManager.getStorageManager().registerProductBreakage(productBreakageRecord);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFields(Arrays.asList(cmbCategory, cmbLot, spnQuantity, cmbReason));
        // Popular combo box de método de formação de preço
        ObservableList<ProductBreakageReason> observableBreakageReasonUnitList
                = FXCollections.observableArrayList(ProductBreakageReason.values());
        cmbReason.setItems(observableBreakageReasonUnitList);

        cmbReason.setCellFactory(new Callback<>() {
            @Override
            public ListCell<ProductBreakageReason> call(ListView<ProductBreakageReason> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(ProductBreakageReason item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.displayMessage);
                        }
                    }
                };
            }
        });

        cmbReason.setConverter(new StringConverter<>() {
            @Override
            public String toString(ProductBreakageReason productBreakageReason) {
                if (productBreakageReason == null) {
                    return null;
                } else {
                    return productBreakageReason.displayMessage;
                }
            }

            @Override
            public ProductBreakageReason fromString(String id) {
                return ProductBreakageReason.getProductBreakageReasonByDisplayMessage(id);
            }
        });
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

}
