//How to add commas in text field while double clicking the specialization from list view
package com.example.taimansystem.Workers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class WorkersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TextField add;

    @FXML
    private TextArea tasplz;

    @FXML
    private ListView<String> lstsplz;

    @FXML
    private TextField mno;


    @FXML
    private TextField wname;

    PreparedStatement stmt;
    Connection con=null;

    @FXML
    void DoGetSpecializations(MouseEvent event) {
        if(event.getClickCount()==2)
        {
            tasplz.appendText(lstsplz.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void DoCleear(ActionEvent event) {
        wname.clear();
        add.clear();
        mno.clear();
        tasplz.clear();

        lstsplz.getSelectionModel().clearSelection();
    }

    @FXML
    void DoDelete(ActionEvent event) {
        try {
            stmt = con.prepareStatement("delete from workers where workersname=? ");
            stmt.setString(1,wname.getText());

            int count=stmt.executeUpdate();

            if(count==1){
                System.out.println("Record deleted successfully");
                ShowAlert("Success","Record deleted successfully");
                }

            else {
                System.out.println("Record not found invalid workername");
                ShowAlert("Sorry","Record not Found invalid workername");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void DoSave(ActionEvent event) {
                //workersname,address ,mobileno,specializations
        try {
            stmt = con.prepareStatement("insert into workers(workersname,address,mobileno,specializations) values(?,?,?,?)");
            stmt.setString(1,wname.getText());
            stmt.setString(2,add.getText());
            stmt.setString(3,mno.getText());
            stmt.setString(4,tasplz.getText());

            int rowsInserted=stmt.executeUpdate();

            if(rowsInserted>0) {
                System.out.println("A new worker is added");
                ShowAlert("Success","Worker Saved Successfully");
            }
            else {
                ShowAlert("FAilure","Worker Cannot be Saved Successfully");

            }

        }
        catch(SQLException exp)
        {
            exp.printStackTrace();
            ShowAlert("Error","Error occured while saving the worker");
        }
    }

    void ShowAlert(String title,String msg){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText("Its a header");
        alert.setContentText(msg);

        alert.showAndWait();
    }
    @FXML
    void initialize() {
        con=MySqlConnectionKlass.doConnect();
        if(con==null)
        {
            System.out.println("Connection not established");
        }
        else{
            System.out.println("Connection established");
        }
        ObservableList <String> items= FXCollections.observableArrayList("Coat","Pant","Shirt","Blazer");
        lstsplz.setItems(items);
    }

}
