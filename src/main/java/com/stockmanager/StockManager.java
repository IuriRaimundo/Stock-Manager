package com.stockmanager;

import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.storage.BreakExpiredLotsBatchJob;
import com.stockmanager.model.storage.StorageManager;
import com.stockmanager.view.components.MainBorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StockManager extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainBorderPane mainBorderPane = new MainBorderPane();
        Scene scene = new Scene(mainBorderPane, 320, 240);
        stage.setTitle("Gestor De Stock");
        stage.setScene(scene);
        stage.setMinWidth(1400);
        stage.setMinHeight(720);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
            System.out.println("Starting the application...");

            try {
                StorageManager.getStorageManager().load();
                ProductManager.getProductManager().load();

                // Executar batch job
                BreakExpiredLotsBatchJob.instance.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Override
    public void stop() {
        System.out.println("Closing the application...");

        try {
            StorageManager.getStorageManager().save();
            ProductManager.getProductManager().save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

