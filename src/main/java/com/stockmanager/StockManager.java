package com.stockmanager;

import com.stockmanager.model.common.DataManager;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.storage.StorageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StockManager extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StockManager.class.getResource("view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        System.out.println("Starting the application...");
        DataManager<StorageManager> storageManagerDL = new DataManager<>(StorageManager.getStockManager());
        DataManager<ProductManager> productManagerDL = new DataManager<>(ProductManager.getProductManager());

        try {
            storageManagerDL.initialize();
            productManagerDL.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Closing the application...");
        DataManager<StorageManager> storageManagerDataManager = new DataManager<>(StorageManager.getStockManager());
        DataManager<ProductManager> productManagerDL = new DataManager<>(ProductManager.getProductManager());

        try {
            storageManagerDataManager.persist();
            productManagerDL.persist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}