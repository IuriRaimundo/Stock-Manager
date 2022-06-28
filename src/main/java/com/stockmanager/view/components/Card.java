package com.stockmanager.view.components;

import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;


import java.io.IOException;


public class Card extends GridPane {

    private final String titleText;
    private final String bodyText;

    public Card(@NamedArg("titleText") String titleText, @NamedArg("bodyText") String bodyText) {

        this.titleText = titleText;
        this.bodyText = bodyText;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
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

}
