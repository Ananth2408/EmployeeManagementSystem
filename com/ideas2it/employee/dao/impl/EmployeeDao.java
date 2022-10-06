package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.util.connectionutil.CustomConnection;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.exception.EmployeeManagementSystemException;
import com.ideas2it.employee.constant.EmployeeManagementConstant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulate employeedetails and return boolean values.
 * Save the Employee details, read them, update them and delete them.
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */ 
public class EmployeeDao implements Dao {
    
    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    @Override
    public boolean addEmployee(Employee employee) throws EmployeeManagementSystemException {
        boolean isAdded= false;
        Connection connection = CustomConnection.getConnection();
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("insert into employee (first_name, last_name, date_of_birth,")
             .append("phone_number, email_id, gender, date_of_joining, salary, role)")
             .append(" values (?,?,?,?,?,?,?,?,?)");
        try {
        PreparedStatement statement = connection.prepareStatement(query.toString());
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
        statement.setLong(4, employee.getPhoneNumber());
        statement.setString(5, employee.getEmail());
        statement.setString(6, employee.getGender());
        statement.setDate(7, Date.valueOf(employee.getDateOfJoining()));
        statement.setFloat(8, employee.getSalary());
        statement.setString(9, employee.getRole());
        count = statement.executeUpdate();

        String idQuery = ("select employee_id from employee where email_id = ?");
        PreparedStatement statementId = connection.prepareStatement(idQuery);
        statementId.setString(1, employee.getEmail());
        ResultSet result = statementId.executeQuery();       
        int employeeId = 0;

        while (result.next()) {
            employeeId = result.getInt(1);
        }
        isAdded = addAddress(employee.getAddress(), employeeId);
        } catch (SQLException e) {
            throw new EmployeeManagementSystemException
            (EmployeeManagementConstant.INSERTION_EXCEPTION, 
              EmployeeManagementConstant.ERROR_CODE101);
        }  
        finally {
            CustomConnection.closeConnection();
        }  
        if (count > 0 && isAdded) {
            isAdded = true;
        }
        return isAdded;  
    }

    /**
     * Save the address details.
     * @param address details.
     * @param employee id from addEmployee
     * @return if employee details added it returns true else it returns false.
     */
    @Override
    public boolean addAddress(Address address, int employeeId) 
                              throws EmployeeManagementSystemException {
        boolean isAdded= false;
        Connection connection = CustomConnection.getConnection();
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("insert into employee_address (door_number, street, city, state,")
             .append("pincode, type, employee_id) values (?,?,?,?,?,?,?)");

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setString(1, address.getDoorNumber());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setInt(5, address.getPinCode());
            statement.setString(6, address.getType());
            statement.setInt(7, employeeId);
            count = statement.executeUpdate();
        } catch (SQLException e) {
             throw new EmployeeManagementSystemException
             (EmployeeManagementConstant.INSERTION_EXCEPTION,
              EmployeeManagementConstant.ERROR_CODE101);
        }
        finally {
           CustomConnection.closeConnection(); 
        }  
        if (count > 0) {
            isAdded = true;
        }
        return isAdded;    
    }
        
    /**
     * Employee details were retrived from the database.
     * @return employee list were returned.
     */
    @Override 
    public List<Employee> displayEmployee() throws EmployeeManagementSystemException {
        List<Employee> employees = new ArrayList();
        Connection connection = CustomConnection.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("select * from employee e, employee_address a where e.employee_id = a.employee_id ");

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());

             while (result.next()) {
                int id = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                LocalDate dateOfBirth = result.getDate(4).toLocalDate();
                long phoneNumber = result.getLong(5);
                String email = result.getString(6);
                String gender = result.getString(7);
                LocalDate dateOfJoining = result.getDate(8).toLocalDate();
                float salary = result.getFloat(9);
                String role = result.getString(10);
                String doorNumber = result.getString(12);
                String street = result.getString(13);
                String city = result.getString(14);
                String state = result.getString(15);
                int pincode = result.getInt(16);
                String type = result.getString(17);

                Address address = new Address(doorNumber, street, city, state, pincode, type);
                Employee employee = new Employee(id, firstName, lastName, role, dateOfBirth, 
                                                 phoneNumber, dateOfJoining, email, salary, 
                                                 gender, address);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new EmployeeManagementSystemException 
             (EmployeeManagementConstant.DISPLAYING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE102);
        }
        finally {
           CustomConnection.closeConnection(); 
        }
        return employees;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */
    @Override
    public boolean updateEmployee(Employee employee, int employeeId)
                                  throws EmployeeManagementSystemException {
        boolean isUpdated = false;
        Connection connection = CustomConnection.getConnection();
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("update employee set first_name = ?,") 
             .append("last_name = ?, date_of_birth = ?, phone_number = ?,")
             .append("email_id = ?, gender = ?, date_of_joining = ?, salary = ?,role = ?")
             .append(" where employee_id = ").append(employeeId);

        try {    
            PreparedStatement statement = connection.prepareStatement(query.toString()); 
            statement.setString(1, employee.getFirstName());  
            statement.setString(2, employee.getLastName());
            statement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            statement.setLong(4, employee.getPhoneNumber());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getGender());
            statement.setDate(7, Date.valueOf(employee.getDateOfJoining()));
            statement.setFloat(8, employee.getSalary());
            statement.setString(9, employee.getRole());
            count = statement.executeUpdate();
            isUpdated = updateAddress(employee.getAddress(), employeeId);
        } catch (SQLException e) {
            throw new EmployeeManagementSystemException
             (EmployeeManagementConstant.UPDATION_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE103);
        } 
        finally{
            CustomConnection.closeConnection();  
        }     
        if (count > 0 && isUpdated) {
            isUpdated = true;
        }  
        return isUpdated;
    }

    /**
     * The address details were update by the given employeeid.
     * @param address details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */
    @Override
    public boolean updateAddress(Address address, int employeeId)
                                 throws EmployeeManagementSystemException {
        boolean isUpdate= false;
        Connection connection = CustomConnection.getConnection();
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("update employee_address set door_number = ?, street = ?,") 
             .append("city = ?, state = ?, pincode = ?, type = ?")
             .append(" where employee_id = ")
             .append(employeeId);

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setString(1, address.getDoorNumber());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setInt(5, address.getPinCode());
            statement.setString(6, address.getType());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            throw new EmployeeManagementSystemException
             (EmployeeManagementConstant.UPDATION_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE103);
        }
        finally {
           CustomConnection.closeConnection(); 
        }
        if (count > 0) {
            isUpdate = true;
        }
        return isUpdate;   
    }

    /**
     * Find the employee details from the database 
     * with the help of given name from the employee.
     * @param employee name from the user. 
     */
    @Override 
    public Employee searchEmployee(String name) 
                                   throws EmployeeManagementSystemException {
        Employee employee = null;
        Connection connection = CustomConnection.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("select * from employee e, employee_address a where e.first_name = ")
             .append("'").append(name).append("'") 
             .append ("and e.employee_id = a.employee_id ");

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());

             while (result.next()) {
                int id = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                LocalDate dateOfBirth = result.getDate(4).toLocalDate();
                long phoneNumber = result.getLong(5);
                String email = result.getString(6);
                String gender = result.getString(7);
                LocalDate dateOfJoining = result.getDate(8).toLocalDate();
                float salary = result.getFloat(9);
                String role = result.getString(10);
                String doorNumber = result.getString(12);
                String street = result.getString(13);
                String city = result.getString(14);
                String state = result.getString(15);
                int pincode = result.getInt(16);
                String type = result.getString(17);

                Address address = new Address(doorNumber, street, city, state, pincode, type);
                employee = new Employee(id, firstName, lastName, role, dateOfBirth,phoneNumber,
                                        dateOfJoining, email, salary, gender, address);
            }
        } catch (SQLException e) {
            throw new EmployeeManagementSystemException
             (EmployeeManagementConstant.SEARCHING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE104);
        }
        finally {
           CustomConnection.closeConnection(); 
        }
        return employee;
    }

    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true else false.
     */
    @Override
    public boolean deleteEmployee(int employeeId) 
                                  throws EmployeeManagementSystemException {
        boolean isDeleted= false;
        Connection connection = CustomConnection.getConnection();
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("delete from employee ")
             .append(" where employee_id = ").append (employeeId);

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            throw new EmployeeManagementSystemException
             (EmployeeManagementConstant.DELETING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE105);
        }
        finally {
            CustomConnection.closeConnection();
        }
        if (count > 0) {
             isDeleted = true;
        }
        return isDeleted;
    }

     /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
    @Override
    public boolean isEmployeeIDExists(int employeeId)
                                      throws EmployeeManagementSystemException {
        boolean isExists= false;
        Connection connection = CustomConnection.getConnection();
        String query = "select employee_id from employee where employee_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                isExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeManagementSystemException
             (EmployeeManagementConstant.IDNOTEXISTS_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE106);
        }
        finally {
            CustomConnection.closeConnection();
        }
        return isExists;
    }      
                
}  
