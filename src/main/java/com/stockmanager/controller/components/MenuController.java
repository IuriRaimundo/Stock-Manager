package com.stockmanager.controller.components;

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

        // Obter referencia para o mainBorderPane
        Scene scene = menu.getScene();
        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");

        try {
            // Alterar center do mainBorderPane para o ecrã passado por parametro
            String screenPath = "/com/stockmanager/view/screens/" + screenName + ".fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screenPath));
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
