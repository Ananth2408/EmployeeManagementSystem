package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;

import java.util.List;

/**
 * @interface EmployeeManagementService.
 * Employee details manipulation were done. 
 * @version 2.0 02-09-2022.
 * @author  Ananth K.
 */
public interface EmployeeManagementService {

    boolean addEmployee(Employee employee);
    
    List<Employee> displayEmployee();

    boolean updateEmployee(Employee employee);

    Employee searchEmployee(String name);

    boolean deleteEmployee(String employeeName);
}