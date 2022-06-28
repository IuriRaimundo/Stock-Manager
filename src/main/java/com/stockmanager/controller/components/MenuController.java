package com.stockmanager.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;



public class MenuController {

    @FXML private Button homeBtn;
    @FXML private Button productBtn;
    @FXML private Button categoryBtn;
    @FXML private Button stockBtn;
    @FXML private Button movementsBtn;

    @FXML VBox menu;

    private void redirect(Scene scene, String link) {
        BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(link));
            mainBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void homeBtnClickHandler(ActionEvent event) {
        redirect(((Button) event.getSource()).getScene(), "/com/prototipo/prototipomsi/screens/homeScreen.fxml");
    }

    @FXML
    public void productBtnClickHandler(ActionEvent event) {
        redirect(((Button) event.getSource()).getScene(), "/com/prototipo/prototipomsi/screens/produtosScreen.fxml");
    }

    @FXML
    public void categoryBtnClickHandler(ActionEvent event) {
        redirect(((Button) event.getSource()).getScene(), "/com/prototipo/prototipomsi/screens/categoriasScreen.fxml");
    }

    @FXML
    public void stockBtnClickHandler(ActionEvent event) {
        redirect(((Button) event.getSource()).getScene(), "/com/prototipo/prototipomsi/screens/loteScreen.fxml");
    }

    @FXML
    public void movementsBtnClickHandler(ActionEvent event) {
        redirect(((Button) event.getSource()).getScene(), "/com/prototipo/prototipomsi/screens/movementLotScreen.fxml");
    }

}
