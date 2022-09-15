package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.EmployeeDTO;

import java.util.List;

/**
 * @interface Dao.
 * Employee details manipulation were done. 
 * @version 2.1 15-09-2022.
 * @author  Ananth K.
 */
public interface Dao {

    boolean addEmployee(Employee employee);
    
    List<Employee> displayEmployee();

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(String employeeName);
}