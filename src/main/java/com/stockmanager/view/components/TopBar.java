package com.stockmanager.view.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TopBar extends HBox {


    public TopBar() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopBar.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
