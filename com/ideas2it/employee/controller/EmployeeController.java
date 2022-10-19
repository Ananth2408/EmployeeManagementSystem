package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;
import com.ideas2it.employee.view.EmployeeView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
public class EmployeeController {
    EmployeeManagementService employeeService = new EmployeeManagementServiceImpl();

    /**
     * Get the value from user and create employee detail.
     * @return the boolean value of added employee details.
     * @param employeedto details.
     */
    public int addEmployee(EmployeeDTO employeeDto) throws EMSException {
        return employeeService.addEmployee(employeeDto);
    }

    /**
     * Display the all employee details from the saved details.
     * @return the employeeDeatil.
     */
    public List<EmployeeDTO> displayEmployee() throws EMSException {
        return employeeService.displayEmployee();
    }

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employeedto details.
     * @return the boolean value if updated returns true else false.
     */
    public boolean updateEmployee(EmployeeDTO employeeDto)
                                                     throws EMSException {
        return employeeService.updateEmployee(employeeDto);
    }

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee found returns employeedetails else it returns null.
     */
    public List<EmployeeDTO> searchEmployee(String name) 
                                  throws EMSException {
        return employeeService.searchEmployee(name);
    }

   /**
     * Delete employee details by employee name,
     * if name found it deletes employee deatils else it doesn't.
     * @param employee name from user.
     * @return the boolean value if deletes return true else false.
     */
    public void deleteEmployee(int employeeId)
                                  throws EMSException {
        employeeService.deleteEmployee(employeeId);
    }

    /**
     * Used to validate the given input is valid or not.
     * @param pattern is regex pattern.
     * @param field is values, input from the users.
     * @return if it is valid it returns true else returns false.
     */
    public boolean isValidData(String pattern, String field) {
        return employeeService.isValidData(pattern, field);
    }

     /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns true else false.
     */
    public boolean isValidBirthDate(String birthDate) throws EMSException {
        return employeeService.isValidBirthDate(birthDate);
    }

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns true else false.
     */
    public boolean isValidJoiningDate(LocalDate birthDate, String joiningDate)
                                                           throws EMSException {
        return employeeService.isValidJoiningDate(birthDate, joiningDate);
    }

    /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
   public EmployeeDTO isEmployeeIDExists(int employeeId)
                                    throws EMSException {
        return employeeService.isEmployeeIDExists(employeeId);
    }

    /**
     * Used to validate the given phone number is duplicate or not.
     * @param phonenumber from the user.
     * @return if valid returns true else false.
     */
    public boolean isValidPhoneNumber(String phoneNumber) throws EMSException{
        return employeeService.isValidPhoneNumber(phoneNumber);
    }

    /**
     * Used to validate the given email is duplicate or not.
     * @param email from the user.
     * @return if valid returns true else false.
     */
    public boolean isValidEmail(String email) throws EMSException{
        return employeeService.isValidEmail(email);
    }
}