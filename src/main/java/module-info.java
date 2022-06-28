module com.stockmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires annotations;


    opens com.stockmanager.view.components to javafx.fxml;
    opens com.stockmanager.controller.components to javafx.fxml;
    exports com.stockmanager;
}