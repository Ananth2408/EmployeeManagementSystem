package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.EmployeeDTO;

import java.util.List;

/**
 * @interface EmployeeManagementService.
 * Employee details manipulation were done. 
 * @version 3.0 16-09-2022.
 * @author  Ananth K.
 */
public interface EmployeeManagementService {

    /**
     * Get the value from user and create employee detail.
     * @return the boolean value.
     * @param employee object from the view class.
     */
    public boolean addEmployee(EmployeeDTO employeeDto);

    /**
     * Display the all employee details from the saved dteails.
     * @return the employeeDeatil from the service class.
     */
    public List<EmployeeDTO> displayEmployee();

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee object from the view.
     * @return the boolean value if updated it returns true else false.
     */
    public boolean updateEmployee(EmployeeDTO employeeDto);

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee found returns employee else it returns null .
     */
    public EmployeeDTO searchEmployee(String employeeName);

    /**
     * Delete employee details by employee name,
     * if name found it deletes emplyee deatils else it doesn't.
     * @return the boolean value if deleted it returns true else false.
     */
    public boolean deleteEmployee(String employeeName);
}