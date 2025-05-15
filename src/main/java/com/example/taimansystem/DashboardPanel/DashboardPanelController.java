//How to add icon to the desktop
package com.example.taimansystem.DashboardPanel;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.taimansystem.DashboardPanel.MySqlConnectionKlass;

import com.example.taimansystem.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardPanelController {

    Connection con = null;
    PreparedStatement stmt = null;

    @FXML
    private ImageView imgprev;

    @FXML
    private TextField odlvd;

    @FXML
    private TextField oiprcs;

    @FXML
    private TextField orcvd;

    @FXML
    private Button csenmt;

    @FXML
    private Button delcnsl;

    @FXML
    private Button exphb;

    @FXML
    private PasswordField login;

    @FXML
    private Button msmnt;

    @FXML
    private PieChart pieprod;

    @FXML
    private PieChart piewrkr;

    @FXML
    private Button rdyprd;

    @FXML
    private Button tvwrkr;

    @FXML
    private Button wrkr;

    @FXML
    void DoExit(MouseEvent event) {
        Stage stage = (Stage) imgprev.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doClickOnSite(MouseEvent event) {
        new Thread(() -> {// because if i will directly call the desktop with the same thread
            // it may freeze the application
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("http://www.realJavaOnline.com"));
                    //Desktop.getDesktop().browse(new URI("http://www.realJavaOnline.com"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();//This approach ensures that the desktop operation runs on a separate thread, keeping the JavaFX UI responsive.
    }

    @FXML
    void doLogin(ActionEvent event) {
        try {
            String enteredpass=login.getText();
            String pass = "rohinGARG";

            stmt = con.prepareStatement("select * from login where password=?");
            stmt.setString(1, pass);
            ResultSet records = stmt.executeQuery();

            if (!records.next()) {
                stmt = con.prepareStatement("INSERT INTO login (password) VALUES (?)");
                stmt.setString(1, pass);
                stmt.executeUpdate();
                System.out.println("Password inserted successfully.");
            } else {
                    System.out.println("Password already exists. Skipping insertion.");
                }

            stmt = con.prepareStatement("SELECT password FROM login WHERE password = ?");
            stmt.setString(1, enteredpass);
            ResultSet loginCheck = stmt.executeQuery();

            if (loginCheck.next()) {
                csenmt.setDisable(false);
                delcnsl.setDisable(false);
                exphb.setDisable(false);
                msmnt.setDisable(false);
                rdyprd.setDisable(false);
                tvwrkr.setDisable(false);
                wrkr.setDisable(false);

                System.out.println("Login successful");
            }
            else{
                System.out.println("Incorrect Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

@FXML
    void DoCallExploreHub(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ExploreHubbb/ExploreHubbbView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO EXPLORE HUB PAGE!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void DoCallMeasurements(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Measurementss/MeasurementssView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO EXPLORE HUB PAGE!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void DoCallReadyProduct(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ReadyProductt/ReadyProducttView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO READY PRODUCT PAGE!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void DoCallTabelView(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WorkersTableVieww/WorkersTableViewww.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("WELCOME TO TABLE VIEW!");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void DoCallWorkers(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Workerss/WorkerssView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO WORKERS VIEW PAGE!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void DocallDeliveryConsole(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeliveryPanell/DeliveryPanellVieww.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO DELIVERY PAGE!");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void DoCallCustomer(ActionEvent event)throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerEnrollmentt/CustomerEnrollmenttView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO CUSTOMER ENROLL PAGE!");
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<PieChart.Data> getWorkerData() throws SQLException {
        ObservableList<PieChart.Data> workerData = FXCollections.observableArrayList();
        try {
            con = MySqlConnectionKlass.doConnect();
            if (con == null) {
                System.out.println("Connection not established");
                return workerData;
            }

            stmt = con.prepareStatement("SELECT worker_assign, COUNT(*) AS count FROM measurements GROUP BY worker_assign");
            ResultSet records = stmt.executeQuery();

            while (records.next()) {
                String workerName = records.getString("worker_assign");
                int count = records.getInt("count");
                workerData.add(new PieChart.Data(workerName, count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workerData;
    }

    public ObservableList<PieChart.Data> getProductData() throws SQLException {
        ObservableList<PieChart.Data> productData = FXCollections.observableArrayList();
        try {
            con = MySqlConnectionKlass.doConnect();
            if (con == null) {
                System.out.println("Connection not established");
                return productData;
            }

            stmt = con.prepareStatement("SELECT dress, COUNT(*) AS count FROM measurements GROUP BY dress");
            ResultSet records = stmt.executeQuery();

            while (records.next()) {
                String dressType = records.getString("dress");
                int count = records.getInt("count");
                productData.add(new PieChart.Data(dressType, count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productData;
    }

    void fetchdata()
    {
        try {
            //String query="select status from measurements";
            int count=0,count2=0,count3=0;
            stmt = con.prepareStatement("select status from measurements");
            ResultSet records=stmt.executeQuery();
            while (records.next()) {
                int status = records.getInt("status");

                // Use switch-case or if-else if based on the status value
                switch (status) {
                    case 1:
                        count++;
                        orcvd.setText(String.valueOf(count));
                        break;
                    case 2:
                        count2++;
                        oiprcs.setText(String.valueOf(count2));
                        break;
                    case 3:
                        count3++;
                        odlvd.setText(String.valueOf(count3));
                        break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        con = MySqlConnectionKlass.doConnect();
        if (con == null) {
            System.out.println("Connection not established");
        }
            else{
            System.out.println("Connection established");
        }
        fetchdata();

        //setting password for the Dashboard
        try{
            String pass="rohinGARG";
        stmt=con.prepareStatement("INSERT INTO login (password) VALUES(?)");
        stmt.setString(1,pass);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Password inserted successfully.");
            } else {
                System.out.println("Failed to insert password.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        try {
            piewrkr.setData(getWorkerData());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pieprod.setData(getProductData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
