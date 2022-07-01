package com.stockmanager.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TopBarController {

    @FXML
    public HBox topBar;
    @FXML
    Label topBarLabel;

    public void setTopBarLabelText(String text) {
        topBarLabel.setText(text);
    }

    public String getTopBarLabelText() {
        return topBarLabel.getText();
    }

}
