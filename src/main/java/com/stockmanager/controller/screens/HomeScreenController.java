package com.stockmanager.controller.screens;

import com.stockmanager.model.storage.*;
import com.stockmanager.view.components.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {
    @FXML
    private Card totalGainCard;
    @FXML
    private Card totalProductBreakageLossCard;
    @FXML
    private Card totalLotCard;
    @FXML
    private Card totalEntryProductCard;
    @FXML
    private Card totalExitProductCard;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double totalProductIssueGain = calculateTotalProductIssueGain();
        totalGainCard.getController().updateBodyLabel(decimalFormat.format(totalProductIssueGain) + "€");

        int totalLot = (int) calculateTotalLot();
        totalLotCard.getController().updateBodyLabel(String.valueOf(totalLot));

        int totalEntryProduct = (int) calculateTotalEntryProduct();
        totalEntryProductCard.getController().updateBodyLabel(String.valueOf(totalEntryProduct));

        totalEntryProductCard.getController().updateTitleLabel("Entrada de produtos " + formatDate.format(LocalDateTime.now()));

        int totalProductIssueRecord = (int) calculateTotalProductIssueRecord();
        totalExitProductCard.getController().updateBodyLabel(String.valueOf(totalProductIssueRecord));

        totalExitProductCard.getController().updateTitleLabel("Saída de produtos " + formatDate.format(LocalDateTime.now()));

        double totalProductBreakageLoss = calculateTotalProductBreakageLoss();
        totalProductBreakageLossCard.getController().updateBodyLabel(decimalFormat.format(totalProductBreakageLoss) + "€");

    }

    /**
     * Método para calcular o total da soma da saída de produtos (ganho)
     * @return Valor total da receita
     */
 private double calculateTotalProductIssueGain () {
     double gainValue = 0;

     for (ProductIssueRecord r : StorageManager.getStorageManager().getProductIssueRecords()) {
         gainValue += r.getGainValue();
     }
     return gainValue;
 }

    /**
     * Método para calcular o total da soma da quebra de produtos
     * @return Valor total da quebra de produtos
     */
    private double calculateTotalProductBreakageLoss() {
        double lossValue = 0;

        for (ProductBreakageRecord r : StorageManager.getStorageManager().getProductBreakageRecords()) {
            lossValue += r.getMovedAmount();
        }

        return lossValue;
    }

    /**
     * Método para calcular o total de produtos da lista de saída de produtos
     * @return Valor total da saída de produtos
     */
    private double calculateTotalProductIssueRecord() {
        double gainValue = 0;

        for (ProductIssueRecord r : StorageManager.getStorageManager().getProductIssueRecords()) {
            gainValue += r.getMovedAmount();
        }
        return gainValue;
    }
    /**
     * Método para calcular o total de lotes da lista de lotes
     * @return Valor total do número de lotes lote
     */
    private double calculateTotalLot() {
        int lot;
        LinkedList<Lot> lotList = StorageManager.getStorageManager().getStoredLots();
        lot = lotList.size();
        return lot;
    }

    /**
     * Método para calcular a soma da entrada de produtos
     * @return Valor total da entrada de produtos
     */
    private double calculateTotalEntryProduct (){
        double gainValue = 0;

        for (LotEntryRecord r : StorageManager.getStorageManager().getLotEntryRecords()) {
            gainValue += r.getMovedAmount();
        }
        return gainValue;
    }
}
