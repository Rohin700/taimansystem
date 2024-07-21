package com.example.taimansystem.DeliveryPanel;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnectionKlass {

    static Connection con=null;

    public static Connection doConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/tailor", "root", "rohinGARG");
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
        return con;
    }
    public static void main(String args[]) {

        if(doConnect()==null)
            System.out.println("Connection not established");
        else
            System.out.println("connection established");
    }


}
