package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.exception.EMSException;

import java.util.List;

/**
 * @interface Dao.
 * Employee details manipulation were done. 
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public interface Dao {

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
   public int addEmployee(Employee employee)
                        throws EMSException;

    /**
     * Print emplyoee detail.
     */
    public List<Employee> displayEmployee()
                       throws EMSException;

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee details.
     * @return if employee updated returns true else it returns false .
     */
    public Employee updateEmployee(Employee employee)
                           throws EMSException;

    /**
     * Print emplyoee detail searching by their name.
     * @param employee name from the user
     */
    public List<Employee> searchEmployee(String name)
                           throws EMSException;


    /**
     * Delete employee details by employee name,
     * if name found it deletes employee deatils else it doesn't.
     * @param employee name.
     */
    public void deleteEmployee(int employeeId)
                           throws EMSException;

}