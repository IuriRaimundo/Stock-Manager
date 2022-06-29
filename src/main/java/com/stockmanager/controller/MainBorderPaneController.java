package com.stockmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class MainBorderPaneController {
    @FXML private BorderPane mainBorderPane;

    public void showError(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro na melhor aplicação.");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
    }
}
