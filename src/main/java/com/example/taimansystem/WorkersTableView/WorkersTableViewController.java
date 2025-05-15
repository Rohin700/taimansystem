package com.example.taimansystem.WorkersTableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class WorkersTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TableView<Workersbean> tblview;



    @FXML
    void doShowAll(ActionEvent event) {
        if (tblview.getColumns().isEmpty()) {

            TableColumn<Workersbean, String> wnameC = new TableColumn<Workersbean, String>("Workers Name");
            wnameC.setCellValueFactory(new PropertyValueFactory<>("workersname"));
            wnameC.setMinWidth(100);

            //wnameC,addC,mnoC are the properties that are needed to be shown in the table;

            TableColumn<Workersbean, String> addC = new TableColumn<Workersbean, String>("Address");
            addC.setCellValueFactory(new PropertyValueFactory<>("address"));
            addC.setMinWidth(100);

            TableColumn<Workersbean, String> mnoC = new TableColumn<Workersbean, String>("Mobile Number");
            mnoC.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
            mnoC.setMinWidth(100);


            TableColumn<Workersbean, String> splzC = new TableColumn<Workersbean, String>("Specialization");
            splzC.setCellValueFactory(new PropertyValueFactory<>("specializations"));
            splzC.setMinWidth(100);

            tblview.getColumns().addAll(wnameC, addC, mnoC, splzC);
        }
        tblview.setItems(getRecords());//This line will help tp fetch record into the tableView
    }


    public ObservableList<Workersbean> getRecords() {
        String selectedSpec = combo.getValue();
        ObservableList<Workersbean> aryy = FXCollections.observableArrayList();

        try {
            stmt = con.prepareStatement("select * from workers where specializations like ?");
            stmt.setString(1, "%" + selectedSpec + "%");
            ResultSet records = stmt.executeQuery();

            while (records.next()) {
                String wn = records.getString("workersname");
                String ad = records.getString("address");
                String mn = records.getString("mobileno");
                String sz = records.getString("specializations");

                aryy.add(new Workersbean(wn,ad,mn,sz));
                //in array we have added an anonymous object which have the properties like em,pwd,bal,age,db,picpath
                //jitni rows utnei objects and jitni properties/columns utne hrr object ke apne array of reference
                System.out.println(wn+" "+ad+" "+mn+" "+sz);
            }
            System.out.println("\n\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return aryy;
    }


    Connection con=null;
    PreparedStatement stmt;
    @FXML
    void initialize() {
        //jaise hi program run hua yeh sara record table mei aagya
        String dress[] = {"Select","Pant", "Coat", "Shirt", "T-shirt"};
        combo.getItems().addAll(Arrays.asList(dress));
        combo.getSelectionModel().select(0);

        con= MySqlConnectionKlass.doConnect();
        if(con==null)
            System.out.println("Connection Not Established");
        else
            System.out.println("Connection Established");
        getRecords();
    }

}
