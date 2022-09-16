package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Employee;

import java.util.List;

/**
 * @interface Dao.
 * Employee details manipulation were done. 
 * @version 3.0 16-09-2022.
 * @author  Ananth K.
 */
public interface Dao {

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    boolean addEmployee(Employee employee);

    /**
     * Print emplyoee detail.
     * @return employee details.
     */
    List<Employee> displayEmployee();

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee details.
     * @return if employee updated returns true else it returns false .
     */
    boolean updateEmployee(Employee employee);

    /**
     * Delete employee details by employee name,
     * if name found it delets employee deatils else it doesn't.
     * @param employee name.
     * @return if employee deleted returns true else it returns false.
     */
    boolean deleteEmployee(Employee Employee);
}