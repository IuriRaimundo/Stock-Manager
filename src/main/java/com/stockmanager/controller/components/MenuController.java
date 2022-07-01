package com.stockmanager.controller.components;

import com.stockmanager.view.components.MainBorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class MenuController {
    @FXML VBox menu;

    /**
     * Função para alterar o ecrã mostrado na aplicação
     * @param screenName Nome do ecrã a ser apresentado
     */
    private void redirect(String screenName) {
        MainBorderPane.controller.changeScreen(screenName);
    }

    @FXML
    public void homeBtnClickHandler() {
        redirect("HomeScreen");
    }

    @FXML
    public void productBtnClickHandler() {
        redirect( "ProductScreen");
    }

    @FXML
    public void categoryBtnClickHandler() {
        redirect("CategoryScreen");
    }

    @FXML
    public void stockBtnClickHandler() {
        redirect("LotScreen");
    }

    @FXML
    public void movementsBtnClickHandler() {
        redirect("LotMovementScreen");
    }

}
