package com.stockmanager.controller.components;


import com.stockmanager.view.components.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    @FXML public Node root;
    @FXML public Label titleLabel;
    @FXML public Label bodyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Card card = (Card) root;
        titleLabel.setText(card.getTitleText());
        bodyLabel.setText(card.getBodyText());
    }

    public void updateBodyLabel (String text)
    {
        bodyLabel.setText(text);
    }

    public void updateTitleLabel (String text)
    {
        titleLabel.setText(text);
    }
}
