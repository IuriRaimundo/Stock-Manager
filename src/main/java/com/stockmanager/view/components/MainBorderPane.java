package com.stockmanager.view.components;

import com.stockmanager.controller.MainBorderPaneController;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainBorderPane extends BorderPane {

    public static MainBorderPaneController controller;


    public MainBorderPane() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stockmanager/view/MainBorderPane.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
            controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public MainBorderPaneController getController() {
        return controller;
    }


}
