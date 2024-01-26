package org.example.ecommerce;
import java.sql.*;

public class DatabaseConnection {

    String dbURL="jdbc:mysql://localhost:3306/ecommerce";
    String userName="root";
    String password="redhat";

    // Driver Manager Class- to managing the connection
    private Statement getStatement(){
        // statement to execute query
        try{
            // help in creating in connection
            // getConnection- create connection with connection class and using
            // that class to create statement to execute your query
            // driver manager class takes and connect with db and
            // gives object of the connection class
            Connection con= DriverManager.getConnection(dbURL,userName,password);
            return con.createStatement(); //create statement of connection class and return

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //
    public ResultSet getQueryTable(String query){
        Statement statement=getStatement();
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    insert and update
    public boolean insertUpdate(String query){
        Statement statement=getStatement();
        try {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }





//    public static void main(String[] args) {
//        String query="SELECT * from products";
//        DatabaseConnection dbConn=new DatabaseConnection();
//        ResultSet rs=dbConn.getQueryTable(query);
//        if(rs!=null){
//            System.out.println("Connected to database");
//        }
//    }
}