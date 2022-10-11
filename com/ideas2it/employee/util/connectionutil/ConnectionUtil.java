package com.ideas2it.employee.util.connectionutil;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.exception.EMSException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This makes the connection between the database and our application.
 * From this connection we can manipulate the data in database.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class ConnectionUtil {
    private static String databaseURL = "jdbc:mysql://localhost:3306/employee_management";
    private static String user = "root";
    private static String password = "Ac@9798@ks";
    private static Connection connection = null;
    private static ConnectionUtil connectionUtil = null;

    private ConnectionUtil() {}

    /**
     * This used to call the connection between the database and application
     * @return connection util
     */
    public static ConnectionUtil getConnectionUtil() {

        if (connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }

    /**
     * This makes the connection between the database and java application.
     * @return connection
     */
    public Connection getConnection() throws EMSException{

        try {

            if (connection == null || connection.isClosed())
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(databaseURL, user, password);
        } catch (ClassNotFoundException e) {
            throw new EMSException(EmployeeManagementConstant.CONNECTION_EXCEPTION,
                                   EmployeeManagementConstant.ERROR_CODE107);
        } catch (SQLException e) {
            throw new EMSException(EmployeeManagementConstant.CONNECTION_EXCEPTION,
                                   EmployeeManagementConstant.ERROR_CODE107);
        }
        return connection;
    }

    /**
     * Used to close the opened connection between the database
     * and java application
     */
    public void closeConnection() throws EMSException {

       try {

           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
           throw new EMSException(EmployeeManagementConstant.CONNECTION_CLOSE_EXCEPTION,
                                  EmployeeManagementConstant.ERROR_CODE108);
       }
    }
}