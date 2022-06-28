package com.stockmanager.controller.screens;

import com.stockmanager.controller.components.CardController;
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
    private Card totalBreakageProduct;
    private Card totalLot;
    private Card totalEntryProduct;
    private Card totalExitProduct;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double totalProductIssueGain = calculateTotalProductIssueGain();
        totalGainCard.getController().updateBodyLabel(String.valueOf(totalProductIssueGain));
    }

 private double calculateTotalProductIssueGain () {
     double gainValue = 0;

     for (ProductIssueRecord r : StorageManager.getStockManager().getProductIssueRecords()) {
         gainValue += r.getGainValue();
     }
     return gainValue;
 }
    private double calculateTotalBreakageProduct() {
        double gainValue = 0;

        for (ProductIssueRecord r : StorageManager.getStockManager().getProductIssueRecords()) {
            gainValue += r.getGainValue();
        }

        return gainValue;
    }
}
