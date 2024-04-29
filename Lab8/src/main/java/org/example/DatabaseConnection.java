package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3305/db"; // URL-ul bazei de date
    private static final String username = "root"; // numele de utilizator al bazei de date
    private static final String password = "Bananageo2bucati"; // parola bazei de date
    private static Connection connection;

    private DatabaseConnection() {
        //Creez direct conectiunea in acelasi timp cand instantiez un obiect de tip connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }


    public static Connection getConnection() {
        if(connection == null){
            try{
                connection = DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);
            }catch (SQLException e){
                System.err.println("Database Connection Creation Failed : " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}