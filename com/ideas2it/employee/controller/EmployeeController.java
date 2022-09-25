package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;
import com.ideas2it.employee.view.EmployeeView;

import java.util.ArrayList;
import java.util.List;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version  3.0 16-09-2022.
 * @author  Ananth K.
 */
public class EmployeeController {
    EmployeeManagementService employeeService = new EmployeeManagementServiceImpl();

    /**
     * Get the value from user and create employee detail.
     * @return the boolean value of added employee details.
     * @param employeedto details.
     */
    public boolean addEmployee(EmployeeDTO employeeDto) {
        return employeeService.addEmployee(employeeDto); 
    }

    /**
     * Display the all employee details from the saved details.
     * @return the employeeDeatil.
     */
    public List<EmployeeDTO> displayEmployee() {
        return employeeService.displayEmployee();
    }

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employeedto details.
     * @return the boolean value if updated returns true else false.
     */
    public boolean updateEmployee(EmployeeDTO employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee found returns employeedetails else it returns null .
     */
    public EmployeeDTO searchEmployee(String employeeName) {
        return employeeService.searchEmployee(employeeName);
    }

   /**
     * Delete employee details by employee name,
     * if name found it deletes employee deatils else it doesn't.
     * @param employee name from user.
     * @return the boolean value if deletes return true else false.
     */
   public boolean deleteEmployee(String employeeName) {
       return employeeService.deleteEmployee(employeeName);
   }
}