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
    boolean addEmployee(Employee employee)
                        throws EMSException;

    /**
     * Save the address details.
     * @param address details.
     * @param address details.
     */
    boolean addAddress(List<Address> address, int employeeId)
                       throws EMSException;

    /**
     * Print emplyoee detail.
     */
    List<Employee> displayEmployee()
                       throws EMSException;

    /**
     * Print emplyoee address detail.
     * @param employee id from the user
     */
    List<Address> displayAddress(int id)
                       throws EMSException;

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee details.
     * @return if employee updated returns true else it returns false .
     */
    boolean updateEmployee(Employee employee, int employeeId)
                           throws EMSException;

    /**
     * Update address details by employee name,
     * If name found it update address details else it doesn't.
     * @param address details.
     * @param employee id.
     */
    boolean updateAddress(List<Address> address, int employeeId)
                          throws EMSException;

    /**
     * Print emplyoee detail searching by their name.
     * @param employee name from the user
     */
    public List<Employee> searchEmployee(String name)
                           throws EMSException;

    /**
     * Print emplyoee address detail.
     * @param employee id from the user
     */
    public List<Address> searchAddress(int id)
                           throws EMSException;

    /**
     * Delete employee details by employee name,
     * if name found it deletes employee deatils else it doesn't.
     * @param employee name.
     */
    boolean deleteEmployee(int employeeId)
                           throws EMSException;

     /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
    public boolean isEmployeeIDExists(int employeeId)
                                      throws EMSException;
}