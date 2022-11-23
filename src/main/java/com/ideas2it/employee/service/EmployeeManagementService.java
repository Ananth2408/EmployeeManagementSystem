package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Employee;

import java.util.List;

/**
 * @interface EmployeeManagementService. Employee details manipulation were
 *            done.
 * 
 * @version 4.1 10-10-2022.
 * @author Ananth K.
 */
public interface EmployeeManagementService {

	/**
	 * Get the value from user and create employee detail.
	 * 
	 * @return the employeeDto details.
	 * @param user given employee details.
	 */
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto);

	/**
	 * Display the all employee details from the saved dteails.
	 * 
	 * @return the employeeDeatil .
	 */
	public List<EmployeeDTO> getAllEmployees();

	/**
	 * Update employee details by given employee details
	 * details else it doesn't.
	 * 
	 * @param employee detai;s from the user.
	 * @return the employeeDto details.
	 */
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto);

	/**
	 * Search employee details by employee name,
	 * 
	 * @param employee name from user.
	 * @return if employee found returns employee else it returns null .
	 */
	public List<EmployeeDTO> searchEmployee(String name);

	/**
	 * Delete employee details by employeeId, if name found it deletes empolyee
	 * deatils else it doesn't.
	 */
	public void deleteEmployee(int employeeId);

	/**
	 * Used to validate the given employee present in the data or not.
	 * 
	 * @param employee id from the user.
	 * @return if employee id persents returns employeeDetails.
	 */
	public Employee employeeExists(int employeeId);

	/**
	 * Used to assign a employee to the project.
	 * 
	 * @param employeeId from the user.
	 * @param projectId  from the user.
	 * @return Employee Details.
	 */
	public EmployeeDTO assignProject(int employeeId, int projectId);
}