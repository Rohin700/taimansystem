//Ready product ki mail bhejni h yaha se
package com.example.taimansystem.ReadyProduct;

import com.example.taimansystem.Workers.MySqlConnectionKlass;
import com.example.taimansystem.WorkersTableView.Workersbean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class ReadyProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> lstdress;

    @FXML
    private ListView<String> lstorderid;


    @FXML
    private ComboBox<String> wcombo;

    PreparedStatement stmt;
    Connection con = null;

    @FXML
    void doReceiveAll(ActionEvent event) {
        if (lstorderid.getSelectionModel().getSelectedItem() != null && wcombo.getSelectionModel().getSelectedItem() != null) {
            try {
                stmt = con.prepareStatement("UPDATE measurements SET status=2 WHERE Orderid=? AND worker_assign=?");
                stmt.setString(1, lstorderid.getSelectionModel().getSelectedItem());
                stmt.setString(2, wcombo.getSelectionModel().getSelectedItem());

                int rowsAffected = stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void doFill(ActionEvent event) {

        lstorderid.getItems().clear();
        lstdress.getItems().clear();

        try {
            stmt = con.prepareStatement("select distinct Orderid,dress from measurements where worker_assign=?");
            stmt.setString(1,wcombo.getSelectionModel().getSelectedItem());
            ResultSet records=stmt.executeQuery();
            ArrayList<String> ary=new ArrayList<String>();
            ArrayList<String> aryy=new ArrayList<String>();

            while(records.next())
            {
                String ordrids=records.getString("Orderid");//col name
                String drs=records.getString("dress");//col name
                ary.add(ordrids);
                aryy.add(drs);

                lstorderid.getItems().addAll(ary);
                lstdress.getItems().addAll(aryy);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        //Checking connection
        con= MySqlConnectionKlass.doConnect();
        if(con==null)
        {
            System.out.println("Connection not established");
        }
        else{
            System.out.println("Connection established");
        }

        try {
            stmt = con.prepareStatement("select distinct workersname from workers");

            ResultSet records=stmt.executeQuery();
            ArrayList<String> ary=new ArrayList<String>();

            while(records.next())
            {
                String wrkrs=records.getString("workersname");//col name
                ary.add(wrkrs);
            }
            wcombo.getItems().addAll(ary);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
 }


