package com.stockmanager.view.components;

import com.stockmanager.controller.MainBorderPaneController;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainBorderPane extends BorderPane {
    private final MainBorderPaneController controller;

    public MainBorderPane() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stockmanager/view/MainBorderPane.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
            controller = (MainBorderPaneController) fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public MainBorderPaneController getController() {
        return controller;
    }
}
