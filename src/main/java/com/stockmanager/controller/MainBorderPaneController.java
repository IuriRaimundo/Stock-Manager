package com.stockmanager.controller;

import com.stockmanager.view.components.TopBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class MainBorderPaneController {
    @FXML private BorderPane mainBorderPane;
    @FXML TopBar topBar;


    /**
     * Método para apresentar um popup de erro.
     * @param e Exceção
     */
    public void showError(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro na melhor aplicação.");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * Método para alterar o ecrã renderizado no MainBorderPane
     * @param screenName Nome do ecrã
     */
    public void changeScreen(String screenName) {
        try {
            // Alterar center para o ecrã passado por parametro
            String screenPath = "/com/stockmanager/view/screens/" + screenName + ".fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screenPath));
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
            showError(e);
        }
    }

    /**
     * Método para abrir um formulário
     * @param formName Nome do formulário
     */
    public void openForm(String formName) {
        try {
            // Alterar center para o ecrã passado por parametro
            String screenPath = "/com/stockmanager/view/forms/" + formName + ".fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screenPath));
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
            showError(e);
        }
    }

    public void setTopBarLabelText(String text) {
        topBar.controller.setTopBarLabelText(text);
    }
}
