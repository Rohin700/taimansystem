//hot to assign datepicker the current date;
//How to update the primary key i.e. mobile number
package com.example.taimansystem.CustomerEnrollment;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CustomerEnrollmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private DatePicker doorder;

    @FXML
    private TextField city;

    @FXML
    private TextField cname;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField state;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField mobileno;


    @FXML
    void doClear(ActionEvent event) {
        mobileno.setText("");
        cname.setText("");
        address.setText("");//textfield
        city.setText("");
        gender.setValue(null);//combobox
        dob.setValue(null);//datepicker
        state.setText("");
        doorder.setValue(null);

    }

    @FXML
    void doFetch(ActionEvent event) {
        try{
            stmt=con.prepareStatement("select * from customerenrollment where mobileno=?");
            stmt.setString(1, mobileno.getText());
            ResultSet records=stmt.executeQuery();
            //mobileno,cname,address,city,gender,dob,state
            while (records.next()){
                String mno= records.getString("mobileno");//col name
                String cn= records.getString("cname");
                String ad= records.getString("address");
                String ct= records.getString("city");
                String st= records.getString("state");
                String gnn= records.getString("gender");
                Date db= records.getDate("dob");
                Date doo=records.getDate("doorder");

                System.out.println(mno+" "+cn+" "+ad+" "+ct+" "+st+" "+gnn+" "+db+" "+doo);

                cname.setText(cn);
                address.setText(ad);
                city.setText(ct);
                state.setText(st);
                gender.getEditor().setText(gnn);

                dob.setValue(((java.sql.Date) db).toLocalDate());
                state.setText(st);
                doorder.setValue(((java.sql.Date) db).toLocalDate());

            }

        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }
        PreparedStatement stmt;
        Connection con=null;

    @FXML
    void doRegister(ActionEvent event) {
        //mobileno,cname , address, city ,state,gender ,dob ,doorder
        try {
            stmt = con.prepareStatement("insert into customerenrollment values(?,?,?,?,?,?,?,?)");
            stmt.setString(1,mobileno.getText());
            stmt.setString(2, cname.getText());
            stmt.setString(3, address.getText());

            stmt.setString(4, city.getText());
            stmt.setString(5, state.getText());
            stmt.setString(6,gender.getEditor().getText());

            LocalDate local=dob.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(7,date);

            LocalDate locall=doorder.getValue();
            java.sql.Date datee=java.sql.Date.valueOf(locall);
            stmt.setDate(8,datee);

//            Calendar c= Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            //set current date into datepicker
//            doorder.(year,month,day);

            stmt.executeUpdate();
            System.out.println("Record added successfully");
            showMyMsg("Record added successfully");
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }

    @FXML
    void doUpdate(ActionEvent event) {
        //mobileno,cname , address, city ,state,gender ,dob ,doorder

        try {
            stmt = con.prepareStatement("update customerenrollment set cname=?,address=?,city=?,state=?,gender=?,dob=?,doorder=? where mobileno=?");
            stmt.setString(1,cname.getText());
            stmt.setString(2,address.getText());
            stmt.setString(3,city.getText());
            stmt.setString(4,state.getText());
            stmt.setString(5,gender.getEditor().getText());

            LocalDate local=dob.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(6,date);

            LocalDate locall=doorder.getValue();
            java.sql.Date datee=java.sql.Date.valueOf(locall);
            stmt.setDate(7,datee);
            stmt.setString(8, mobileno.getText());


            stmt.executeUpdate();
            showMyMsg("Record updated successfullyyyyy");
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }

    void showMyMsg(String msg) {
         Alert alert=new Alert(Alert.AlertType.WARNING);
         alert.setTitle("Information Dialog");
         alert.setHeaderText("Its Header");
         alert.setContentText(msg);

         alert.showAndWait();

    }


    @FXML
    void initialize() {

        String []gndr={"Select","Male","Female","Others"};
        gender.getItems().addAll(gndr);
        gender.getSelectionModel().select(0);//fix the 0Th element i.e. select in the combobox;


       con=MySqlConnectionKlass.doConnect();
       if(con==null)
           System.out.println("connection did not established");
       else
           System.out.println("Connection established");

    }

}
