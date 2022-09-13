package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version  2.0 02-09-2022.
 * @author  Ananth K.
 */
public class EmployeeController {
    EmployeeManagementServiceImpl employeementService = new EmployeeManagementServiceImpl();

    /**
     * Get the value from user and create employee detail.
     * @return the boolean value.
     * @param employee object from the view class.
     */
    public boolean addEmployee(Employee employee) {
        return employeementService.addEmployee(employee); 
    }

    /**
     * Display the all employee details from the saved dteails.
     * @return the employeeDeatil from the service class.
     */
    public List<Employee> displayEmployee() {
        return employeementService.displayEmployee();
    }

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee object from the view.
     * @return the employeeDetail.
     */
    public boolean updateEmployee(Employee employee) {
        return employeementService.updateEmployee(employee);
    }

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee fount returns searchemployee else it returns null .
     */
    public Employee searchEmployee(String employeeName) {
        return employeementService.searchEmployee(employeeName);
    }

   /**
     * Delete employee details by employee name,
     * if name found it deletes emplyee deatils else it doesn't.
     * @return the employeeDeatil from the service class.
     */
   public boolean deleteEmployee(String employeeName) {
       return employeementService.deleteEmployee(employeeName);
   }
}