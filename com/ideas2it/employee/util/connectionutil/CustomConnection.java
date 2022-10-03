package com.ideas2it.employee.util.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This makes the connection between the database and our application.
 * From this connection we can manipulate the data in database.
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
public class CustomConnection {
    private static String databaseURL = "jdbc:mysql://localhost:3306/employee_management";
    private static String user = "root";
    private static String password = "Ac@9798@ks";
    private static Connection connection = null;

    private CustomConnection() {}

    /**
     * This makes the connection between the database and java application.
     */
    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed())
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(databaseURL, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Something went wrong tyr again.");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Used to close the opened connection between the database
     * and java application
     */
    public static void closeConnection() {

       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
}