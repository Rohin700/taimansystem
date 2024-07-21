package com.example.taimansystem.DeliveryPanel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.taimansystem.CustomerEnrollment.MySqlConnectionKlass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DeliveryPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> lstBill;

    @FXML
    private ListView<String> lstDress;

    @FXML
    private ListView<String> lstOid;

    @FXML
    private ListView<String> lstSts;

    @FXML
    private ComboBox<String> mnocombo;

    @FXML
    private TextField tbill;

    PreparedStatement stmt;
    Connection con=null;
    @FXML
    void DoBill(ActionEvent event) {

    }

    @FXML
    void DoDeliverAll(ActionEvent event) {
        if (!lstSts.getItems().isEmpty()) {
            ObservableList<String> sts = lstSts.getSelectionModel().getSelectedItems();

            try {
                String query = "UPDATE measurements SET status = ? WHERE Orderid = ?";
                PreparedStatement stmt = con.prepareStatement(query);

                for (String status : sts) {
                    int statusValue = Integer.parseInt(status);

                    stmt.setInt(1, 3); // Set status to 3
                    stmt.setInt(2, statusValue);

                    stmt.executeUpdate();
                }
                showMyMsg("Status updated successfully!");

            }
            catch (SQLException e) {
                e.printStackTrace();
                showMyMsg("Error updating status: " + e.getMessage());
            }
        }
        else {
            showMyMsg("No items selected to update status.");
        }
    }

    @FXML
    void DoFetchAll(ActionEvent event) {
        lstOid.getItems().clear();
        lstBill.getItems().clear();
        lstDress.getItems().clear();
        lstSts.getItems().clear();

        if(mnocombo!=null && !mnocombo.getValue().isEmpty())
        {
            try{
                stmt=con.prepareStatement("select Orderid,dress,bill,status from measurements where Mobile_no=?");
                stmt.setString(1,mnocombo.getValue());

                ResultSet records=stmt.executeQuery();
                ArrayList<String> aryO=new ArrayList<String>();
                ArrayList<String> aryD=new ArrayList<String>();
                ArrayList<String> aryB=new ArrayList<String>();
                ArrayList<String> aryS=new ArrayList<String>();

                while(records.next())
                {
                    String ordrids=records.getString("Orderid");//col name
                    String drs=records.getString("dress");//col name
                    String bl=records.getString("bill");//col name
                    String sts=records.getString("status");//col name

                    aryO.add(ordrids);
                    aryD.add(drs);
                    aryB.add(bl);
                    aryS.add(sts);

                    lstOid.getItems().addAll(aryO);
                    lstDress.getItems().addAll(aryD);
                    lstBill.getItems().addAll(aryB);
                    lstSts.getItems().addAll(aryS);
                }

            }
            catch (Exception e){
            e.printStackTrace();
        }
        }
    }

    void showMyMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Its Header");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    @FXML
    void initialize() {
        con = MySqlConnectionKlass.doConnect();
        if (con == null)
            System.out.println("connection did not established");
        else
            System.out.println("Connection established");

        //Adding mobile numbers to the Combo box
        try{
            stmt=con.prepareStatement("select distinct Mobile_no from measurements");
            ResultSet records= stmt.executeQuery();

            while(records.next()){
                mnocombo.getItems().add(records.getString( "Mobile_no"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
