package com.stockmanager.controller.screens;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductManagerUtils;
import com.stockmanager.view.components.MainBorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryScreenController implements Initializable {

    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> toId;
    @FXML
    private TableColumn<Category, String> toName;

    @FXML
    private TextField searchTextField;

    private List<Category> categoryList;
    private ObservableList<Category> categoryObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toId.setCellValueFactory(new PropertyValueFactory<>("id"));
        toName.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Popular tabela com categorias
        categoryList = ProductManager.getProductManager().getCategories().values().stream().toList();
        categoryObservableList = FXCollections.observableArrayList(categoryList);
        categoryTableView.setItems(categoryObservableList);
    }

    public void addCategoryButton(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("AddCategoryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    public void removeCategoryButton(ActionEvent event){
        try {
            MainBorderPane.controller.openForm("RemoveCategoryForm");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }

    }

    @FXML
    public void searchButtonActionHandler(ActionEvent event) {

        String needle = searchTextField.getText();

        if (needle.length() == 0) {
            categoryTableView.setItems(categoryObservableList);
            return;
        }

        LinkedList<Category> filteredCategoryList = new LinkedList<>();

        // Filtrar categorias por id, com recurso ao HashMap para fazer procura em tempo constante
        Category category = ProductManagerUtils.getCategoryByID(needle);

        // Se não foi encontrado produto por id, fazer procura por nome
        if (category != null) {
            filteredCategoryList.add(category);
        } else {
            filteredCategoryList = categoryList.stream()
                    .filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(needle.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        // Atualizar tabela
        ObservableList<Category> filteredCategoryObservableList = FXCollections.observableArrayList(filteredCategoryList.stream().toList());
        categoryTableView.setItems(filteredCategoryObservableList);
    }
}
