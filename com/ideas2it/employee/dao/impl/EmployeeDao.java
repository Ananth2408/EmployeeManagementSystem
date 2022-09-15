package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.EmployeeDTO;
import com.ideas2it.employee.dao.Dao;

import java.util.List;
import java.util.ArrayList;

/**
 * Manipulate employeedetails and return boolean values.
 * Save the Employee details, read them, update them and delete them.
 * @version 2.1 15-09-2022.
 * @author  Ananth K.
 */ 
public class EmployeeDao implements Dao {
    List<Employee> employees = new ArrayList<Employee>();

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    @Override
    public boolean addEmployee(Employee employee) {
           return employees.add(employee);
    }

    /**
     * Print emplyoee detail.
     * @return employee details.
     */
    @Override 
    public List<Employee> displayEmployee() {
        return employees;
    }

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee details.
     * @return if employee updated returns true else it returns false .
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isUpdated = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(employee.getName())) {
                employees.set(i,employee);
                isUpdated = true;
            } 
        }
        return isUpdated;
    }

    /**
     * Delete employee details by employee name,
     * if name found it delets emplyee deatils else it doesn't.
     * @param employee name.
     * @return if employee deleted returns true else it returns false.
     */
    @Override
    public boolean deleteEmployee(Employee searchEmployee) {

        return employees.remove(searchEmployee);
    }
                
}  
