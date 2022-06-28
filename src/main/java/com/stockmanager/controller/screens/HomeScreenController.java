package com.stockmanager.controller.screens;

import com.stockmanager.controller.components.CardController;
import com.stockmanager.model.storage.ProductBreakageRecord;
import com.stockmanager.model.storage.ProductIssueRecord;
import com.stockmanager.model.storage.StorageManager;
import com.stockmanager.view.components.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {
    @FXML
    private Card totalGainCard;
    @FXML
    private Card totalBreakageProductCard;
    private Card totalLotCard;
    private Card totalEntryProductCard;
    private Card totalExitProductCard;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double totalProductIssueGain = calculateTotalProductIssueGain();
        totalGainCard.getController().updateBodyLabel(String.valueOf(totalProductIssueGain) + "€");

        double totalBreakageProduct = calculateTotalBreakageProduct();
        totalBreakageProductCard.getController().updateBodyLabel(String.valueOf(totalBreakageProduct) + "€");
    }

 private double calculateTotalProductIssueGain () {
     double gainValue = 0;

     for (ProductIssueRecord r : StorageManager.getStorageManager().getProductIssueRecords()) {
         gainValue += r.getGainValue();
     }
     return gainValue;
 }

    private double calculateTotalBreakageProduct() {
        double lossValue = 0;

        for (ProductBreakageRecord r : StorageManager.getStorageManager().getProductBreakageRecords()) {
            lossValue += r.getLossValue();
        }

        return lossValue;
    }
}
