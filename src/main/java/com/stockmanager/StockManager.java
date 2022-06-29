package com.stockmanager;

import com.stockmanager.model.common.DataManager;
import com.stockmanager.model.product.Category;
import com.stockmanager.model.product.Product;
import com.stockmanager.model.product.ProductManager;
import com.stockmanager.model.product.ProductPricingUnit;
import com.stockmanager.model.storage.Date;
import com.stockmanager.model.storage.Lot;
import com.stockmanager.model.storage.LotEntryRecord;
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
        DataManager<StorageManager> storageManagerDL = new DataManager<>(StorageManager.getStorageManager());
        DataManager<ProductManager> productManagerDL = new DataManager<>(ProductManager.getProductManager());

        try {
            storageManagerDL.initialize();
            productManagerDL.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Category c1 = new Category("Legumes");
        Category c2 = new Category("Frutas");
        Category c3 = new Category("Bebidas Espirituosas");

        Category[] categories = {c1, c2, c3};

        Product p1 = new Product("Alface", "Ferrari", c1, 0.99, ProductPricingUnit.KG, true);
        Product p2 = new Product("Maça", "Opel", c2, 1.34, ProductPricingUnit.KG, true);
        Product p3 = new Product("Whiskey", "Shelby Company Ltd.", c3,25.99, ProductPricingUnit.UN, true);

        Product[] products = {p1, p2, p3};

        ProductManager productManager = ProductManager.getProductManager();

        for (Category category : categories) {
            productManager.addCategory(category);
        }

        for (Product product : products) {
            productManager.addProduct(product);
        }

        StorageManager storageManager = StorageManager.getStorageManager();

        Lot l1 = new Lot(p3, new Date(30, 10, 2040), 90);
        Lot l2 = new Lot(p2, new Date(15, 7, 2022), 30);
        Lot l3 = new Lot(p2, new Date(20, 8, 2022), 50);

        LotEntryRecord ler1 = new LotEntryRecord(l1);
        LotEntryRecord ler2 = new LotEntryRecord(l2);
        LotEntryRecord ler3 = new LotEntryRecord(l3);

        Lot[] lots = {l1, l2, l3};

        for (Lot lot : lots) {
            storageManager.registerLotEntry(new LotEntryRecord(lot));
        }

    }

    @Override
    public void stop() {
        System.out.println("Closing the application...");
        DataManager<StorageManager> storageManagerDataManager = new DataManager<>(StorageManager.getStorageManager());
        DataManager<ProductManager> productManagerDL = new DataManager<>(ProductManager.getProductManager());

        try {
            storageManagerDataManager.persist();
            productManagerDL.persist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}