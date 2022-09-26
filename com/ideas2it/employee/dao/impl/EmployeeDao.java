package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.model.address;
import com.ideas2it.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;

/**
 * Manipulate employeedetails and return boolean values.
 * Save the Employee details, read them, update them and delete them.
 * @version 3.0 16-09-2022.
 * @author  Ananth K.
 */ 
public class EmployeeDao implements Dao {
    
    private Connection connection;

    private EmployeeDao() {}

    private static EmployeeDao getConnection() {
        private String databaseURL = "jdbc:mysql://localhost:3306/employee_management";
        private String user = "root";
        private String password = "Ac@9798@ks";

        try {
            if (connection == null)
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(databaseURL, user, password);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean addEmployee(Employee employee) {
        boolean isadded= false;
        PreparedStatement statement = null;
        int count = 0;

        try {    
            statement = EmployeeDao.getConnection.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?)");  
            statement.setString(2, employee.getFirstName());  
            statement.setString(3, employee.getLastName());
            statement.setDate(4, employee.getDateOfBirth());
            statement.setlong(5, employee.getPhoneNumber());
            statement.setString(6, employee.getEmail());
            statement.setString(7, employee.getGender());
            statement.setDate(8, employee.getDateOfJoining());
            statement.setfloat(9, employee.getSalary());
            statement = EmployeeDao.getConnection.prepareStatement("insert into employee_address values(?,?,?,?,?,?,?)");         
            statement.setString(2, address.getDoorNumber());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getState());
            statement.setint(6, address.getPinCode());
            statement.setString(7, address.getType());
            count = statement.executeUpdate();  
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        finally {
            if (statement!=null){  
                statement.close();  
            }if(connection!=null){  
                connection.close();  
            }   
         }  
        if (count > 0) {
            isadded = true;
        } else {
            is added = false;
        }    
    }

    /**
     * {@inheritdoc}
     */
    @Override 
    public List<Employee> displayEmployee() {
        List<Employee> employees = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("select employee_id, first_name, last_name, date_of_birth, phone_number")
             .append("email_id, gender, date_of_joining, salary, door_number, street, city")
             .append("pincode, type");

        try {
            Statement statement = EmployeeDao.getConnection.createStatement();
            ResultSet result = statement.executeQuery(query);

             while (result.next()) {
                long id = result.getlong(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                Date dateOfBirth = result.getString(4);
                long phoneNumber = result.getlong(5);
                String email = result.getString(6);
                String gender = result.getString(7);
                Date dateOfJoining = result.getDate(8);
                float salary = result.getfloat(9);
                int doorNumber = result.getString(10);
                String street = result.getString(11);
                String city = result.getString(12);
                String state = result.getString(13);
                int pincode = result.getint(14);
                String type = result.getString(15);
                employee.setAddress(new Address(doorNumber, street, city,
                                          state, pincode, type));
                employee.setEmployee(new Employee(id, firstName, lastName,dateOfBirth,phoneNumber,dateOfJoining,
                                                  email, salary, gender, address));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isUpdated = false;
        PreparedStatement statement = null;
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("update employee set employee_id, first_name, last_name, date_of_birth, phone_number")
             .append("email_id, gender, date_of_joining, salary, door_number, street, city")
             .append("pincode, type");

        try {    
            statement = EmployeeDao.getConnection.prepareStatement();  
            statement.setString(2, employee.getFirstName());  
            statement.setString(3, employee.getLastName());
            statement.setDate(4, employee.getDateOfBirth());
            statement.setlong(5, employee.getPhoneNumber());
            statement.setString(6, employee.getEmail());
            statement.setString(7, employee.getGender());
            statement.setDate(8, employee.getDateOfJoining());
            statement.setDecimal(9, employee.getSalary());
            statement = EmployeeDao.getConnection.prepareStatement("insert into employee_address values(?,?,?,?,?,?,?)");         
            statement.setString(2, address.getDoorNumber());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getState());
            statement.setint(6, address.getPinCode());
            statement.setString(7, address.getType());
            count = statement.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrace(); 
        } 
        finally{
            if (statement!=null){  
                statement.close();  
            }if(connection!=null){  
                connection.close();  
            }   
         }  
        if (count > 0) {
            isadded = true;
        } else {
            is added = false;
        }    

        return isUpdated;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteEmployee(Employee employee) {

        return employees.remove(employee);
    }
                
}  
