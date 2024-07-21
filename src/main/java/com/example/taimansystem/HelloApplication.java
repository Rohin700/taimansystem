package com.example.taimansystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Workerss/WorkerssView.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerEnrollmentt/CustomerEnrollmenttView.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Measurementss/MeasurementssView.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ReadyProductt/ReadyProducttView.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WorkersTableVieww/WorkersTableViewww.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ExploreHubbb/ExploreHubbbView.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeliveryPanell/DeliveryPanellVieww.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboardd/Dashboardd.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME USER!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}