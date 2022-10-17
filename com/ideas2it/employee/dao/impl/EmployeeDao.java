package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.util.connectionutil.ConnectionUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */ 
public class EmployeeDao implements Dao {
    ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
    Logger logger = LogManager.getLogger(EmployeeDao.class);
    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    @Override
    public boolean addEmployee(Employee employee) throws EMSException {
        boolean isAdded= false;
        int count = 0;
        int employeeId = 0;
        StringBuilder query = new StringBuilder();
        query.append("insert into employee (first_name, last_name, date_of_birth,")
             .append("phone_number, email_id, gender, date_of_joining, salary, role)")
             .append(" values (?,?,?,?,?,?,?,?,?)");

        try {
            Connection connection = connectionUtil.getConnection();
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

            while (result.next()) {
                employeeId = result.getInt(1);
            }
            isAdded = addAddress(employee.getAddress(), employeeId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (EmployeeManagementConstant.INSERTION_EXCEPTION, 
              EmployeeManagementConstant.ERROR_CODE101);
        }  
        finally {
            connectionUtil.closeConnection();
        }

        if (count > 0 && isAdded) {
            isAdded = true;
            logger.info("Employee created EmployeeId =" + employeeId);
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
    public boolean addAddress(List<Address> addresss, int employeeId) 
                              throws EMSException { 
        boolean isAdded= false;
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("insert into employee_address (door_number, street, city, state,")
             .append("pincode, type, employee_id) values (?,?,?,?,?,?,?)");
        
        for (Address address : addresss) {

            try {
                Connection connection = connectionUtil.getConnection();
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
                logger.error(e.getMessage());
                throw new EMSException
                (EmployeeManagementConstant.INSERTION_EXCEPTION,
                 EmployeeManagementConstant.ERROR_CODE101);
            }
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
    public List<Employee> displayEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("select * from employee");

        try {
            Connection connection = connectionUtil.getConnection();
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

                Employee employee = new Employee(id, firstName, lastName, role, dateOfBirth, 
                                                 phoneNumber, dateOfJoining, email, salary, 
                                                 gender, displayAddress(id));
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException 
             (EmployeeManagementConstant.DISPLAYING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE102);
        }
        finally {
           connectionUtil.closeConnection(); 
        }
        return employees;
    }

    /**
     * Employee details were retrived from the database.
     * @param employee id from the employee details.
     * @return employee list were returned.
     */
    @Override 
    public List<Address> displayAddress(int id) throws EMSException {
        List<Address> addresss = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("select * from employee_address where employee_id = ?");

        try {
            Connection connection = connectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String doorNumber = result.getString(2);
                String street = result.getString(3);
                String city = result.getString(4);
                String state = result.getString(5);
                int pincode = result.getInt(6);
                String type = result.getString(7);

                Address address = new Address(doorNumber, street, city, 
                                              state, pincode, type);
                addresss.add(address);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException 
             (EmployeeManagementConstant.DISPLAYING_EXCEPTION,
              EmployeeManagementConstant.ERROR_CODE102);
        }
        finally {
           connectionUtil.closeConnection(); 
        }
        return addresss;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */
    @Override
    public boolean updateEmployee(Employee employee, int employeeId)
                                  throws EMSException {
        boolean isUpdated = false;
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("update employee set first_name = ?,") 
             .append("last_name = ?, date_of_birth = ?, phone_number = ?,")
             .append("email_id = ?, gender = ?, date_of_joining = ?,")
             .append("salary = ?,role = ?")
             .append(" where employee_id = ").append(employeeId);

        try {
            Connection connection = connectionUtil.getConnection();    
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
            logger.error(e.getMessage());
            throw new EMSException
             (EmployeeManagementConstant.UPDATION_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE103);
        } 
        finally{
            connectionUtil.closeConnection();  
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
    public boolean updateAddress(List<Address> addresss, int employeeId)
                                 throws EMSException {
        boolean isUpdate= false;
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("update employee_address set door_number = ?, street = ?,") 
             .append("city = ?, state = ?, pincode = ?, type = ?")
             .append(" where employee_id = ")
             .append(employeeId);

        for (Address address : addresss) {

            try {
                Connection connection = connectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query.toString());
                statement.setString(1, address.getDoorNumber());
                statement.setString(2, address.getStreet());
                statement.setString(3, address.getCity());
                statement.setString(4, address.getState());
                statement.setInt(5, address.getPinCode());
                statement.setString(6, address.getType());
                count = statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new EMSException
                (EmployeeManagementConstant.UPDATION_EXCEPTION,
                 EmployeeManagementConstant.ERROR_CODE103);
        }
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
    public List<Employee> searchEmployee(String name) 
                                   throws EMSException {
        Employee employee = null;
        List<Employee> employees = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("select * from employee  where first_name like")
             .append("'").append(name).append("%").append("'");
             
        try {
            Connection connection = connectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString());
            ResultSet result = statement.executeQuery();

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
                
            employee = new Employee(id, firstName, lastName, role, dateOfBirth,phoneNumber,
                                    dateOfJoining, email, salary, gender, searchAddress(id));
            employees.add(employee);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException
             (EmployeeManagementConstant.SEARCHING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE104);
        }
        finally {
           connectionUtil.closeConnection(); 
        }
        return employees;
    }

    /**
     * Find the employee details from the database 
     * with the help of given name from the employee.
     * @param employee name from the user. 
     */
    public List<Address> searchAddress(int id) throws EMSException {
        List<Address> addresss = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("select * from employee_address where employee_id = ")
             .append(id);

        try {
            Connection connection = connectionUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());

             while (result.next()) {
                 String doorNumber = result.getString(2);
                 String street = result.getString(3);
                 String city = result.getString(4);
                 String state = result.getString(5);
                 int pincode = result.getInt(6);
                 String type = result.getString(7);

                Address address = new Address(doorNumber, street, city, state,
                                              pincode, type);

                addresss.add(address);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException 
             (EmployeeManagementConstant.DISPLAYING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE102);
        }
        finally {
           connectionUtil.closeConnection(); 
        }
        return addresss;
    }

    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true else false.
     */
    @Override
    public boolean deleteEmployee(int employeeId) 
                                  throws EMSException {
        boolean isDeleted= false;
        int count = 0;
        StringBuilder query = new StringBuilder();
        query.append("delete from employee ")
             .append(" where employee_id = ").append (employeeId);

        try {
            Connection connection = connectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException
             (EmployeeManagementConstant.DELETING_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE105);
        }
        finally {
            connectionUtil.closeConnection();
        }

        if (count > 0) {
             isDeleted = true;
        }
        return isDeleted;
    }

     /**
     * Used to validate the given employee present in the data or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
    @Override
    public boolean isEmployeeIDExists(int employeeId)
                                      throws EMSException {
        boolean isExists= false;
        String query = "select employee_id from employee where employee_id = ?";

        try {
            Connection connection = connectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                isExists = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new EMSException
             (EmployeeManagementConstant.IDNOTEXISTS_EXCEPTION,
               EmployeeManagementConstant.ERROR_CODE106);
        }
        finally {
            connectionUtil.closeConnection();
        }
        return isExists;
    }      
                
}  
