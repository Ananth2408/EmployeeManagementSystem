package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeManagementService;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Application used to maintain the employee details. Create, read, update
 * and delete operations were done in this application.
 * 
 * @version 4.1 10-10-2022.
 * @author Ananth K.
 */
@RestController
@RequestMapping("/api.com.ideas2it/employee-management-system/v1.0/employee")
public class EmployeeController {
	@Autowired
	EmployeeManagementService employeeService;

	/**
	 * Get the value from user and create employee detail.
	 * 
	 * @return the boolean value of added employee details.
	 * @param employeedto details.
	 */
	@PostMapping
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDto)
			throws EMSException {
		return employeeService.addEmployee(employeeDto);
	}

	/**
	 * Display the all employee details from the saved details.
	 * 
	 * @return the employeeDeatil.
	 */
	@GetMapping
	public List<EmployeeDTO> getAllEmployees() throws EMSException {
		return employeeService.getAllEmployees();
	}

	/**
	 * Update employee details by employee name, If name found it update 
	 * employee details else it doesn't.
	 * 
	 * @param employeedto details.
	 * @return the boolean value if updated returns true else false.
	 */
	@PatchMapping
	public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDto)
			throws EMSException {
		return employeeService.updateEmployee(employeeDto);
	}

	/**
	 * Search employee details by employee name, If name found it update 
	 * employee details else it doesn't.
	 * 
	 * @param employee name from user.
	 * @return
	 */
	@GetMapping("/searchemployee")
	public List<EmployeeDTO> searchEmployee( String name)
			throws EMSException {
		return employeeService.searchEmployee(name);
	}

	/**
	 * Delete employee details by employee id, if name found it deletes employee
	 * deatils else it doesn't.
	 * 
	 * @param employee id from user.
	 * @return the message.
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteEmployee(int employeeId) 
			throws EMSException {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>
		("Employee details deleted successfully", HttpStatus.OK);
	}

	/**
	 * This is used to assigning project to the employee.
	 * 
	 * @param employeeId
	 * @param projectId
	 * @return Employee Details.
	 * @throws EMSException
	 */
	@PatchMapping("/assignproject")
	public EmployeeDTO assignProject(int employeeId, int projectId) throws EMSException {
		EmployeeDTO employeeDto = null;
		employeeDto = employeeService.assignProject(employeeId, projectId);
		return employeeDto;
	}
    
    /**
     * Used to validate the given input is valid or not.
     * @param pattern is regex pattern.
     * @param field is values, input from the users.
     * @return if it is valid it returns true else returns false.
     */
    public boolean isValidData(String pattern, String field) {
        return employeeService.isValidData(pattern, field);
    }

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns true else false.
     */
    public boolean isValidBirthDate(String birthDate) throws EMSException {
        return employeeService.isValidBirthDate(birthDate);
    }

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns true else false.
     */
    public boolean isValidJoiningDate(LocalDate birthDate, String joiningDate)
                                                           throws EMSException {
        return employeeService.isValidJoiningDate(birthDate, joiningDate);
    }

    /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
   public EmployeeDTO employeeExists(int employeeId)
                                    throws EMSException {
        return employeeService.employeeExists(employeeId);
    }

    /**
     * Used to validate the given phone number is duplicate or not.
     * @param phonenumber from the user.
     * @return if valid returns true else false.
     */
    public boolean isValidPhoneNumber(String phoneNumber) throws EMSException{
        return employeeService.isValidPhoneNumber(phoneNumber);
    }

    /**
     * Used to validate the given email is duplicate or not.
     * @param email from the user.
     * @return if valid returns true else false.
     */
    public boolean isValidEmail(String email) throws EMSException{
        return employeeService.isValidEmail(email);
    }

    /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
   /*public ProjectDTO getProject(int projectId)
                                    throws EMSException {
        ProjectManagementService projectService = new ProjectManagementServiceImpl();
        return projectService.projectExists(projectId);
    }*/
}