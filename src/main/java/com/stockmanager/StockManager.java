package com.stockmanager;

import com.stockmanager.model.common.ManagerDataLoader;
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
        FXMLLoader fxmlLoader = new FXMLLoader(StockManager.class.getResource("main.fxml"));
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

        ManagerDataLoader<StorageManager> storageManagerDL = new ManagerDataLoader<>(StorageManager.getStockManager());
        ManagerDataLoader<ProductManager> productManagerDL = new ManagerDataLoader<>(ProductManager.getProductManager());

        try {
            storageManagerDL.initialize();
            productManagerDL.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}