package com.stockmanager.controller.forms;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.view.forms.Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveCategoryFormController extends FormController implements Initializable {

    public ComboBox<Category> cmbCategory;

    public RemoveCategoryFormController() {
        super("Remover Categoria", "CategoryScreen");
    }
    @Override
    protected void submitAction() {
       ProductManager.getProductManager().removeCategory((Category) cmbCategory.getValue());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inicializar Componentes
        setFields(List.of(cmbCategory));
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

}
}
