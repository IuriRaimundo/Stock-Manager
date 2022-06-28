package com.stockmanager.controller.screens;

import com.stockmanager.model.storage.ProductIssueRecord;
import com.stockmanager.model.storage.StorageManager;
import com.stockmanager.view.components.Card;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController {
    @FXML
    private Card totalGainCard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double gainValue = 0;

        for (ProductIssueRecord r : StorageManager.getStockManager().getProductIssueRecords()) {
            gainValue += r.getGainValue();
        }

        totalGainCard.setBodyText(gainValue);

    }
}
