package com.stockmanager.controller.forms;

import com.stockmanager.model.product.*;
import com.stockmanager.model.storage.Date;
import com.stockmanager.model.storage.Lot;
import com.stockmanager.model.storage.LotEntryRecord;
import com.stockmanager.model.storage.StorageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterLotEntryFormController extends FormController implements Initializable {

    @FXML private ComboBox<Category> cmbCategory;
    @FXML private ComboBox<Product> cmbProduct;
    @FXML private Spinner<Integer> spnQuantity;
    @FXML private DatePicker datePicker;

    public RegisterLotEntryFormController() {
        super("Registrar Entrada de Lote", "LotScreen");
    }

    @Override
    public void submitAction() {

        Date date = new Date(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getYear());
        Lot lot = new Lot(cmbProduct.getValue(), date, spnQuantity.getValue());
        LotEntryRecord lotEntryRecord = new LotEntryRecord(lot);
        StorageManager.getStorageManager().registerLotEntry(lotEntryRecord);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inicializar Componentes
        setFields(Arrays.asList(cmbCategory, cmbProduct, spnQuantity, datePicker));
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

        // Popular combo box de produtos
        LinkedList<Product> prodList = new LinkedList<>(ProductManager.getProductManager().getProducts().values());
        ObservableList<Product> observableprodList = FXCollections.observableArrayList(prodList);
        cmbProduct.setItems(observableprodList);

        // Formatar texto da combo box de  produtos
        Callback<ListView<Product>, ListCell<Product>> productComboBoxCellFactory = new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> l) {
                return new ListCell<>() {

                    @Override
                    protected void updateItem(Product item, boolean empty) {
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

        cmbProduct.setButtonCell(productComboBoxCellFactory.call(null));
        cmbProduct.setCellFactory(productComboBoxCellFactory);
        cmbProduct.setConverter(new StringConverter<>() {
            @Override
            public String toString(Product product) {
                if (product == null) {
                    return null;
                } else {
                    return product.toString();
                }
            }

            @Override
            public Product fromString(String productString) {
                return ProductManagerUtils.getProductByProductString(productString);
            }
        });

        // Adicionar listener a combobox de categoria para adicionar valores a combobox de produtos
        cmbCategory.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                cmbProduct.getItems().clear();
                cmbProduct.setDisable(true);
            } else {
                // sample code, adapt as needed:
                List<Product> states = ProductManagerUtils.getProductsByCategory(newValue);
                cmbProduct.getItems().setAll(states);
                cmbProduct.setDisable(false);
            }
        });


    }
}
