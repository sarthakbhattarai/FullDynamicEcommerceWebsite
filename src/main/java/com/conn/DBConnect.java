package com.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn = null;

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/courseworkjava";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Failed to connect to the database!");
            e.printStackTrace();
        }
        return conn;
    }
}
