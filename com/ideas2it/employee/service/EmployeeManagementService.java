package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EmployeeManagementSystemException;

import java.time.LocalDate;
import java.util.List;

/**
 * @interface EmployeeManagementService.
 * Employee details manipulation were done. 
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
public interface EmployeeManagementService {

    /**
     * Get the value from user and create employee detail.
     * @return the boolean value.
     * @param employee object from the view class.
     */
    public boolean addEmployee(EmployeeDTO employeeDto) 
                               throws EmployeeManagementSystemException;

    /**
     * Display the all employee details from the saved dteails.
     * @return the employeeDeatil from the service class.
     */
    public List<EmployeeDTO> displayEmployee()
                               throws EmployeeManagementSystemException;

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee object from the view.
     * @return the boolean value if updated it returns true else false.
     */
    public boolean updateEmployee(EmployeeDTO employeeDto, int employeeId)
                                  throws EmployeeManagementSystemException;

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee found returns employee else it returns null .
     */
    public EmployeeDTO searchEmployee(String name)
                                      throws EmployeeManagementSystemException;

    /**
     * Delete employee details by employee name,
     * if name found it deletes emplyee deatils else it doesn't.
     * @return the boolean value if deleted it returns true else false.
     */
    public boolean deleteEmployee(int employeeId)
                                  throws EmployeeManagementSystemException;

    /**
     * Used to validate the given input is valid or not.
     * @param pattern is regex pattern.
     * @param field is values, input from the users.
     * @return if it is valid it returns true else rturns false.
     */
    public boolean isValidData(String pattern, String field);

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns localdate else ask again.
     */
    public LocalDate date(String date);
}