package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.EmployeeDTO;
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
	 * @return the boolean value.
	 * @param employee object from the view class.
	 */
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto);

	/**
	 * Display the all employee details from the saved dteails.
	 * 
	 * @return the employeeDeatil from the service class.
	 */
	public List<EmployeeDTO> getAllEmployees();

	/**
	 * Update employee details by employee name, If name found it update employee
	 * details else it doesn't.
	 * 
	 * @param employee object from the view.
	 * @return the boolean value if updated it returns true else false.
	 */
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto);

	/**
	 * Search employee details by employee name, If name found it update employee
	 * details else it doesn't.
	 * 
	 * @param employee name from user.
	 * @return if employee found returns employee else it returns null .
	 */
	public List<EmployeeDTO> searchEmployee(String name);

	/**
	 * Delete employee details by employee name, if name found it deletes emplyee
	 * deatils else it doesn't.
	 * 
	 * @return the boolean value if deleted it returns true else false.
	 */
	public void deleteEmployee(int employeeId);

	/**
	 * Used to validate the given employee present in the dat or not.
	 * 
	 * @param employee id from the user.
	 * @return if employee id persents returns true else returns false.
	 */
	public EmployeeDTO employeeExists(int employeeId);

	/**
	 * Used to assign a employee to the project.
	 * 
	 * @param employeeId from the user.
	 * @param projectId  from the user.
	 * @return Employee Details.
	 */
	public EmployeeDTO assignProject(int employeeId, int projectId);

	/**
	 * Used to validate the given phone number is duplicate or not.
	 * 
	 * @param phonenumber from the user.
	 * @return if valid returns true else false.
	 */
	public boolean isValidPhoneNumber(String phoneNumber);

	/**
	 * Used to validate the given phone number is duplicate or not.
	 * 
	 * @param phonenumber from the user.
	 * @return if valid returns true else false.
	 */
	public boolean isValidEmail(String email);
}