package com.stockmanager.controller.forms;

import com.stockmanager.model.product.Category;
import com.stockmanager.model.storage.Lot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RegisterBreakageProductFormController extends FormController implements Initializable {

    @FXML private ComboBox<Category> cmbCategory;
    @FXML private ComboBox<Lot> cmbLot;
    @FXML private Spinner<Integer> spnQuantity;
    @FXML private ComboBox<String> txtObservation;

    public RegisterBreakageProductFormController() {
        super("Registar quebra de produto", "");
    }

    @Override
    public void submitAction() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFields(Arrays.asList(cmbCategory, cmbLot, spnQuantity, txtObservation));

    }

}
