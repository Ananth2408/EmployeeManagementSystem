package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.exception.EmployeeManagementSystemException;

import java.util.List;

/**
 * @interface Dao.
 * Employee details manipulation were done. 
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
public interface Dao {

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    boolean addEmployee(Employee employee)
                        throws EmployeeManagementSystemException;

    /**
     * Save the address details.
     * @param address details.
     * @param address details.
     */
    boolean addAddress(Address address, int employeeId)
                       throws EmployeeManagementSystemException;

    /**
     * Print emplyoee detail.
     */
    List<Employee> displayEmployee()
                       throws EmployeeManagementSystemException;

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee details.
     * @return if employee updated returns true else it returns false .
     */
    boolean updateEmployee(Employee employee, int employeeId)
                           throws EmployeeManagementSystemException;

    /**
     * Update address details by employee name,
     * If name found it update address details else it doesn't.
     * @param address details.
     */
    boolean updateAddress(Address address, int employeeId)
                          throws EmployeeManagementSystemException;

    public Employee searchEmployee(String name)
                           throws EmployeeManagementSystemException;

    /**
     * Delete employee details by employee name,
     * if name found it delets employee deatils else it doesn't.
     * @param employee name.
     */
    boolean deleteEmployee(int employeeId)
                           throws EmployeeManagementSystemException;

     /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
    public boolean isEmployeeIDExists(int employeeId)
                                      throws EmployeeManagementSystemException;
}