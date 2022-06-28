package com.stockmanager.view.components;

import com.stockmanager.controller.components.CardController;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.nio.channels.Pipe;


public class Card extends GridPane {

    private final String titleText;
    private final String bodyText;
    private final CardController controller;
    public Card(@NamedArg("titleText") String titleText, @NamedArg("bodyText") String bodyText) {

        this.titleText = titleText;
        this.bodyText = bodyText;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Card.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
            controller = (CardController) fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getTitleText() {
        return titleText;
    }

    public String getBodyText() {
        return bodyText;
    }

    public CardController getController() {
        return controller;
    }
}