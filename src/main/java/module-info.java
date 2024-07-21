module com.example.taimansystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.taimansystem to javafx.fxml;
    exports com.example.taimansystem;


    exports com.example.taimansystem.CustomerEnrollment;
    opens com.example.taimansystem.CustomerEnrollment to javafx.fxml;

    exports com.example.taimansystem.Workers;
    opens com.example.taimansystem.Workers to javafx.fxml;

    exports com.example.taimansystem.Measurements;
    opens com.example.taimansystem.Measurements to javafx.fxml;

    exports com.example.taimansystem.WorkersTableView;
    opens com.example.taimansystem.WorkersTableView to javafx.fxml;

    exports com.example.taimansystem.ReadyProduct;
    opens com.example.taimansystem.ReadyProduct to javafx.fxml;

    exports com.example.taimansystem.ExploreHub;
    opens com.example.taimansystem.ExploreHub to javafx.fxml;

    exports com.example.taimansystem.DeliveryPanel;
    opens com.example.taimansystem.DeliveryPanel to javafx.fxml;

    exports com.example.taimansystem.DashboardPanel;
    opens com.example.taimansystem.DashboardPanel to javafx.fxml;


}