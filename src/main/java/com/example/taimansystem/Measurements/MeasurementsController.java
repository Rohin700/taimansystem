//CURRENT DATE kaise?
//all order ids in combo
//dates and order id clear nhi ho rhi new pr click rknei pr
//Add Email here to send the information ki order received;
package com.example.taimansystem.Measurements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.taimansystem.Workers.MySqlConnectionKlass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.w3c.dom.ls.LSOutput;

import javax.swing.text.Style;


public class MeasurementsController {

    @FXML
    private ComboBox<String> orderid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bill;

    @FXML
    private DatePicker doorder;

    @FXML
    private ComboBox<String> status;

    @FXML
    private ComboBox<String> cdresses;

    @FXML
    private DatePicker dodel;

    @FXML
    private TextField mobileno;

    @FXML
    private ImageView imgPrev;

    @FXML
    private TextField ppu;

    @FXML
    private ComboBox<String> qty;

    @FXML
    private TextArea measurement;

    @FXML
    private ComboBox<String> workers;

    PreparedStatement stmt;
    Connection con=null;

    @FXML
    void doSearch(ActionEvent event) {
        try {
            //Mobile_no ,dress ,pic ,Deliv_date,qty,bill,measurements,worker_assign ,doorder
            stmt = con.prepareStatement("select * from measurements where Orderid=?");
            stmt.setString(1, orderid.getValue());
            ResultSet records = stmt.executeQuery();
            while(records.next())
            {
                String mno=records.getString("Mobile_no");//column name
                String drs=records.getString("dress");//column name
                String pc=records.getString("pic");//column name
                Date dd=records.getDate("Deliv_date");//column name
                Integer qt=records.getInt("qty");//col name
                Integer pp=records.getInt("Price_per_unit");
                Integer bl=records.getInt("Bill");//col name
                String msmt=records.getString("measurements");//col name
                String wrk=records.getString("worker_assign");//col name


                System.out.println(mno+" "+drs+" "+pc+" "+dd+" "+qt+" "+pp+" "+bl+" "+msmt+" "+wrk);

                mobileno.setText(mno);
                cdresses.getEditor().setText(String.valueOf(drs));
                if (pc != null) {
                    imgPrev.setImage(new Image(new FileInputStream(pc)));
                }
                qty.getEditor().setText(String.valueOf(qt));

                ppu.setText(String.valueOf(pp));
                bill.setText(String.valueOf(bl));
                measurement.setText(msmt);
                workers.getEditor().setText(wrk);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void DoCancelOrder(ActionEvent event) {
        if(orderid!=null) {
            try {
                stmt = con.prepareStatement("delete from measurements where Orderid=?");
                stmt.setString(1, orderid.getSelectionModel().getSelectedItem());

                int count = stmt.executeUpdate();

                if (count == 1) {
                    System.out.println("Order cancelled");
                    ShowMyMsg("Bad news", "Order Cancelled");
                    doclear();
                } else
                    System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DoClear(ActionEvent event) {
        doclear();
    }

    void doclear(){
        //Mobile_no ,dress ,pic ,Deliv_date,qty,bill,measurements,worker_assign ,doorder
        mobileno.setText("");
        cdresses.setValue(null);
        imgPrev.setImage(null);
        dodel.setPromptText("");
        qty.setValue(null);
        ppu.setText("");
        bill.setText("");
        measurement.setText("");
        workers.setValue("");
        status.setValue(null);
    }

    @FXML
    void DoCloseOrder(ActionEvent event) {
        try {
            stmt = con.prepareStatement("delete from measurements where Orderid=?");
            stmt.setString(1, orderid.getValue());

            int count = stmt.executeUpdate();

            if (count == 1) {
                System.out.println("Order Completed successfully");
                ShowMyMsg("Congratulations","Order Completed");
            }
            else
                System.out.println("Record not found INVALID Order_ID");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

//Mobile_no ,dress ,pic ,Deliv_date,qty,bill,measurements,worker_assign ,doorderL
@FXML
void DoSave(ActionEvent event) {
        String mobilenumber=mobileno.getText();
        String dress=cdresses.getSelectionModel().getSelectedItem();
        String dodelivery=dodel.getValue().toString();

        int quantity=Integer.parseInt(qty.getSelectionModel().getSelectedItem());
        int priceperunit=Integer.parseInt(ppu.getText());
        int totalbill=Integer.parseInt(bill.getText());
        String measurements=(measurement.getText());
        String wokers=workers.getSelectionModel().getSelectedItem();

        String doorder=String.valueOf(java.time.LocalDate.now());
        String workstatus=status.getSelectionModel().getSelectedItem();

        String query="INSERT INTO measurements(Mobile_no,dress,pic,Deliv_date,qty,Price_per_unit,bill,measurements,worker_assign,doorder,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        //,PreparedStatement.RETURN_GENERATED_KEYS\"); "

        try(PreparedStatement pst=con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1,mobilenumber);
            pst.setString(2,dress);
            pst.setString(3,filepath);
            pst.setString(4,dodelivery);
            pst.setInt(5,quantity);
            pst.setInt(6,priceperunit);
            pst.setInt(7,totalbill);
            pst.setString(8,measurements);
            pst.setString(9,wokers);
            pst.setString(10,doorder);
            pst.setString(11,workstatus);
            pst.executeUpdate();

            ResultSet generatedKeys=pst.getGeneratedKeys();
            if(generatedKeys.next()){
                int ORDERID=generatedKeys.getInt(1);
                orderid.getItems().add(String.valueOf(ORDERID));
                orderid.getSelectionModel().select(String.valueOf(ORDERID));

                System.out.println("New order id successfull added with id"+ORDERID);
                ShowMyMsg("Success","Added Successfully");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DoUpdate(ActionEvent event) {
        try {
            //Mobile_no ,dress ,pic ,Deliv_date,qty,bill,measurements,worker_assign ,doorder

            stmt = con.prepareStatement("update measurements set Mobile_no=?,dress=?,pic=?,Deliv_date=?,qty=?,bill=?,measurements=?,worker_assign=?,status=? where Orderid=?");
            stmt.setString(1,mobileno.getText());
            stmt.setString(2,cdresses.getEditor().getText());
            stmt.setString(3,filepath);

            LocalDate local=dodel.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(4,date);

            stmt.setInt(5,Integer.parseInt(qty.getEditor().getText()));

            stmt.setInt(6,Integer.parseInt(bill.getText()));
            stmt.setInt(7,Integer.parseInt(ppu.getText()));
            stmt.setString(8,measurement.getText());
            stmt.setString(9,workers.getEditor().getText());
            stmt.setString(10,status.getValue());
            //stmt.setString(10,status.getSelectionModel().getSelectedItem());

            stmt.executeUpdate();
            ShowMyMsg("Success","Record updated successfullyyyyy");
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }
    String filepath="nopic.jpg";//this is for upload image   
    @FXML
    void DoUploadDesign(ActionEvent event) {
        FileChooser chooser = new FileChooser();

        chooser.setTitle("Select profile pic");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"), new FileChooser.ExtensionFilter("*.*", "*.*"));

        File file=chooser.showOpenDialog(null);

        if (file != null) {
            String absolutePath = file.getAbsolutePath();
        }

//        filepath=file.getAbsolutePath();


        try {
            imgPrev.setImage(new Image(new FileInputStream(file)));
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void DofillWorkers(ActionEvent event) {

        workers.getItems().clear();
        String selectedDress=cdresses.getSelectionModel().getSelectedItem();

        if (selectedDress != null && !selectedDress.isEmpty()) {
            try {
                //stmt = con.prepareStatement("SELECT DISTINCT workersname FROM workers where specialization=?");
                stmt = con.prepareStatement("SELECT DISTINCT workersname FROM workers");

                ArrayList<String> aryy = new ArrayList<String>();
                //stmt.setString(1,  selectedDress );
                    ResultSet records = stmt.executeQuery();

                    while (records.next()) {
                        //workers.getItems().add(records.getString("workersname"));
                        String wrkrs = records.getString("workersname");//col name
                        aryy.add(wrkrs);
                    }

                workers.getItems().addAll(aryy);
                stmt.executeQuery();

            } catch (Exception e) {
                e.getMessage();
            }
        }

    }


    @FXML
    void CalcBill(ActionEvent event) {
        try {
            int price = Integer.parseInt(ppu.getText());
            String qtystring=qty.getSelectionModel().getSelectedItem();

            int qtyy = qtystring!=null?Integer.parseInt(qtystring):0;
            int tbill = price * qtyy;
            bill.setText(String.valueOf(tbill));
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }
    }

    void ShowMyMsg (String title, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText("Its a header");
        alert.setContentText(msg);

        alert.showAndWait();
    }
    @FXML
    void initialize() {

        //status combobox fill
        status.getItems().addAll("1","2","3");

        //combobox cdresses
        String dress[] = {"Select", "Pant","Blazer", "Coat", "Shirt", "T-shirt"};
        cdresses.getItems().addAll(Arrays.asList(dress));
        cdresses.getSelectionModel().select(0);


        //combobox qty

        int[] ary = new int[1000];
        for (int i = 0; i < ary.length; i++) {
            ary[i] = i + 1;
        }

        for (int i = 0; i < ary.length; i++) {
            qty.getItems().add(String.valueOf(ary[i]));
        }


        qty.getSelectionModel().selectPrevious();

        //connection check
        con = MySqlConnectionKlass.doConnect();
        if (con == null) {
            System.out.println("Connection not established");
        } else {
            System.out.println("Connection established");
        }

    }
}
