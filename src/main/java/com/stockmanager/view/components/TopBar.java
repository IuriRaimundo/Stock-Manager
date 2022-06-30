package com.stockmanager.view.components;

import com.stockmanager.controller.components.TopBarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TopBar extends HBox {

    public TopBarController controller;

    public TopBar() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopBar.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
            controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
