module com.stockmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires annotations;


    opens com.stockmanager.model.storage to javafx.base;
    opens com.stockmanager.model.product to javafx.base;
    opens com.stockmanager.view.components to javafx.fxml;
    opens com.stockmanager.controller.components to javafx.fxml;
    opens com.stockmanager.controller.screens to javafx.fxml;


    exports com.stockmanager;
    opens com.stockmanager.view to javafx.fxml;
    opens com.stockmanager.controller to javafx.fxml;
}