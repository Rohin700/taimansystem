package com.example.taimansystem.ExploreHub;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.taimansystem.WorkersTableView.MySqlConnectionKlass;
import com.example.taimansystem.WorkersTableView.Workersbean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExploreHubController {


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private ComboBox<String> cmncombo;

        @FXML
        private ComboBox<String> stscombo;

        @FXML
        private TableView<ExploreHubbean> tblview;

        @FXML
        private ComboBox<String> wrkcombo;


        Connection con=null;
        PreparedStatement stmt;


        @FXML
        void DoExport(ActionEvent event) {

        }

        @FXML
        void DoShowAll(ActionEvent event) {
            tblview.getItems().clear();
            tblview.setItems(getOrderData());
        }

    public ObservableList<ExploreHubbean> getOrderData() {
        ObservableList<ExploreHubbean> ary = FXCollections.observableArrayList();

        try {
            stmt = con.prepareStatement("select * from measurements ");
            ResultSet records = stmt.executeQuery();


            while (records.next()) {
                String oi = records.getString("Orderid");
                String mno = records.getString("Mobile_no");
                String ds = records.getString("dress");
                Date dd = records.getDate("Deliv_date");
                String qt = records.getString("qty");
                String bl = records.getString("bill");
                String wrks = records.getString("worker_assign");
                String sts = records.getString("status");

                ary.add(new ExploreHubbean(oi, mno, ds, String.valueOf(dd), Integer.valueOf(qt), Integer.valueOf(bl), wrks, sts));
                System.out.println(oi + " " + mno + " " + ds + " " + dd + " " + qt + " " + bl + " " + wrks + " " + sts);
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ary;
    }



//Showing the data where status=?
        @FXML
        void ShowOrderWithS(ActionEvent event) {
            String selectedordrid=stscombo.getValue();
            if(selectedordrid!=null && !selectedordrid.isEmpty())
            {
                ObservableList<ExploreHubbean>orderdata=getOrderDataa();
                tblview.getItems().clear();
                tblview.setItems(orderdata);
                System.out.println("-----------------"+orderdata.size()+"-------------------");
            }
        }


    public ObservableList<ExploreHubbean> getOrderDataa() {
        ObservableList<ExploreHubbean> ary = FXCollections.observableArrayList();

        String selectedSpecc = stscombo.getValue();

        try {
            stmt = con.prepareStatement("select * from measurements where status = ?");
            stmt.setString(1, selectedSpecc);
            ResultSet records = stmt.executeQuery();


            while (records.next()) {
                String oi = records.getString("Orderid");
                String mno = records.getString("Mobile_no");
                String ds = records.getString("dress");
                Date dd = records.getDate("Deliv_date");
                String qt = records.getString("qty");
                String bl = records.getString("bill");
                String wrks = records.getString("worker_assign");
                String sts = records.getString("status");

                ary.add(new ExploreHubbean(oi, mno, ds, String.valueOf(dd), Integer.valueOf(qt), Integer.valueOf(bl), wrks, sts));
                System.out.println(oi + " " + mno + " " + ds + " " + dd + " " + qt + " " + bl + " " + wrks + " " + sts);
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ary;
    }



//Now the turn is for the worker combo boc alog with the status combo box selected
    @FXML
    void DoShowOrderWithW(ActionEvent event) {
            //cols();
        //yh likhne se ek baar aar columns banjawnge jab jab hmm yeh functin ko call krenge;
            String selectedworker=wrkcombo.getValue();
            if(selectedworker!=null && !selectedworker.isEmpty()){
                ObservableList<ExploreHubbean>orderdata=getOrderDataaW();
                tblview.getItems().clear();
                tblview.setItems(getOrderDataaW());
            }
    }
    public ObservableList<ExploreHubbean> getOrderDataaW() {
        ObservableList<ExploreHubbean> aryyy = FXCollections.observableArrayList();

        String selectedWorker = wrkcombo.getValue();

        try {
            stmt = con.prepareStatement("select * from measurements where worker_assign=? and status = ?");
            stmt.setString(1,selectedWorker);

            if(stscombo!=null && !stscombo.getValue().isEmpty()) {
                stmt.setString(2, stscombo.getValue());
            }
            else{
                showMyMsg("Please Select Order Status");
            }
            ResultSet records = stmt.executeQuery();


            while (records.next()) {
                String oi = records.getString("Orderid");
                String mno = records.getString("Mobile_no");
                String ds = records.getString("dress");
                Date dd = records.getDate("Deliv_date");
                String qt = records.getString("qty");
                String bl = records.getString("bill");
                String wrks = records.getString("worker_assign");
                String sts = records.getString("status");

                aryyy.add(new ExploreHubbean(oi, mno, ds, String.valueOf(dd), Integer.valueOf(qt), Integer.valueOf(bl), wrks, sts));
                System.out.println(oi + " " + mno + " " + ds + " " + dd + " " + qt + " " + bl + " " + wrks + " " + sts);
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aryyy;
    }

    @FXML
    void DoShowOrderWithCMN(ActionEvent event) {
        String selectedmobile=cmncombo.getValue();
        if(selectedmobile!=null && !selectedmobile.isEmpty()){
            ObservableList<ExploreHubbean>orderdata=getOrderDataaM();
            tblview.getItems().clear();
            tblview.setItems(getOrderDataaM());
        }
    }
    public ObservableList<ExploreHubbean> getOrderDataaM() {
        ObservableList<ExploreHubbean> aryyyy = FXCollections.observableArrayList();

        String selectedmobile = cmncombo.getValue();

        try {
            stmt = con.prepareStatement("select * from measurements where Mobile_no=?");

            stmt.setString(1,selectedmobile);
            ResultSet records = stmt.executeQuery();


            while (records.next()) {
                String oi = records.getString("Orderid");
                String mno = records.getString("Mobile_no");
                String ds = records.getString("dress");
                Date dd = records.getDate("Deliv_date");
                String qt = records.getString("qty");
                String bl = records.getString("bill");
                String wrks = records.getString("worker_assign");
                String sts = records.getString("status");

                aryyyy.add(new ExploreHubbean(oi, mno, ds, String.valueOf(dd), Integer.valueOf(qt), Integer.valueOf(bl), wrks, sts));
                System.out.println(oi + " " + mno + " " + ds + " " + dd + " " + qt + " " + bl + " " + wrks + " " + sts);
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aryyyy;
    }



    void cols()
    {
        TableColumn<ExploreHubbean,String>oidC=new TableColumn<ExploreHubbean,String>("Order Id");
        oidC.setCellValueFactory(new PropertyValueFactory<>("Orderid"));
        oidC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>mnoC=new TableColumn<ExploreHubbean,String>("Mobile Number");
        mnoC.setCellValueFactory(new PropertyValueFactory<>("Mobile_no"));
        mnoC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>drsC=new TableColumn<ExploreHubbean,String>("Dress");
        drsC.setCellValueFactory(new PropertyValueFactory<>("dress"));
        drsC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>ddC=new TableColumn<ExploreHubbean,String>("Delivery Date");
        ddC.setCellValueFactory(new PropertyValueFactory<>("Deliv_date"));
        ddC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>qtyC=new TableColumn<ExploreHubbean,String>("Quantity");
        qtyC.setCellValueFactory(new PropertyValueFactory<>("qty"));
        qtyC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>blC=new TableColumn<ExploreHubbean,String>("Bill");
        blC.setCellValueFactory(new PropertyValueFactory<>("bill"));
        blC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>wrkC=new TableColumn<ExploreHubbean,String>("Worker Assigned");
        wrkC.setCellValueFactory(new PropertyValueFactory<>("worker_assign"));
        wrkC.setMinWidth(100);

        TableColumn<ExploreHubbean,String>stsC=new TableColumn<ExploreHubbean,String>("Status");
        stsC.setCellValueFactory(new PropertyValueFactory<>("status"));
        stsC.setMinWidth(100);

        tblview.getColumns().addAll(oidC,mnoC,drsC,ddC,qtyC,blC,wrkC,stsC);

        // if(stscombo.getValue()==null) {
          //  tblview.setItems(getOrderData());
        //}
       // else {
           // tblview.setItems(getOrderDataa());
        //}
//upper wala sab likhne se Show All wale button ko press krne pr hi data shoe ho rha th;
    }void showMyMsg(String msg) {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Its Header");
        alert.setContentText(msg);

        alert.showAndWait();

    }

        @FXML
        void initialize() {
            con= MySqlConnectionKlass.doConnect();
            if(con==null)
                System.out.println("Connection Not Established");
            else
                System.out.println("Connection Established");

            cols();

            //Adding 1,2,3 to the combo box;
            try{
                stmt=con.prepareStatement("select distinct status from measurements");
                ResultSet records= stmt.executeQuery();

                while(records.next()){
                    stscombo.getItems().add(records.getString( "status"));
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }


            //Adding distinct workersname from measurements;
            try{
                stmt=con.prepareStatement("select distinct workersname from workers");
                ResultSet records= stmt.executeQuery();

                while(records.next()){
                    wrkcombo.getItems().add(records.getString( "workersname"));
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }


            //Adding customer mobile number to the combo box to fetch data fo the table;
            try{
                stmt=con.prepareStatement("select distinct Mobile_no from measurements");
                ResultSet records= stmt.executeQuery();

                while(records.next()){
                    cmncombo.getItems().add(records.getString( "Mobile_no"));
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

}
