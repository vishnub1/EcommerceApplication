package org.example.ecommerce;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {

    private final String dbUrl = "jdbc:mysql://localhost:3306/ecommerce";
                    // Connector name, DB name , url, DBName instance
    private final String userName = "root";

    private final String password = "redhat";


    // MAke the actual connections
    private Statement getStatement() {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            return connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getQueryTable(String query) {
        try {
            Statement statement = getStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DbConnection conn = new DbConnection();
        ResultSet rs = conn.getQueryTable("Select * from customer");
        if(rs != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection failed");
        }
    }
}












