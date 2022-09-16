package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Manipulate employeedetails and return boolean values.
 * Save the Employee details, read them, update them and delete them.
 * @version 3.0 16-09-2022.
 * @author  Ananth K.
 */ 
public class EmployeeDao implements Dao {
    List<Employee> employees = new ArrayList<Employee>();

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean addEmployee(Employee employee) {
           return employees.add(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override 
    public List<Employee> displayEmployee() {
        return employees;
    }

    /**
     * {@inheritdoc}
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
     * {@inheritdoc}
     */
    @Override
    public boolean deleteEmployee(Employee employee) {

        return employees.remove(employee);
    }
                
}  
