package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.service.EmployeeManagementService;

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
@RequestMapping("/api/v1/ems/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeManagementService employeeService;

	/**
	 * Get the value from user and create employee detail.
	 * 
	 * @return the added employee details.
	 * @param employeedto details.
	 */
	@PostMapping
	private ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDto) {
		
		return new ResponseEntity<EmployeeDTO>
		        (employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
	}

	/**
	 * Display the all employee details from the saved details.
	 * 
	 * @return the employeeDeatil.
	 */
	@GetMapping
	private ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		
		return new ResponseEntity<List<EmployeeDTO>>
		        (employeeService.getAllEmployees(), HttpStatus.OK);
	}

	/**
	 * Update employee details by the given employee details
	 * else it doesn't.
	 * 
	 * @param employeedto details.
	 * @return the  updated employee details .
	 */
	@PatchMapping
	private ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDto) {
		
		return new ResponseEntity<EmployeeDTO>
		        (employeeService.updateEmployee(employeeDto), HttpStatus.OK);
	}

	/**
	 * Search employee details by employee name
	 * 
	 * @param employee name from user.
	 * @return the employee details
	 */
	@GetMapping("/search")
	private ResponseEntity<List<EmployeeDTO>> searchEmployee(String name) {
		
		return new ResponseEntity<List<EmployeeDTO>>
		        (employeeService.searchEmployee(name), HttpStatus.OK);
	}

	/**
	 * Delete employee details by employee id, if name found it deletes employee
	 * deatils else it doesn't.
	 * 
	 * @param employee id from user.
	 * @return the message.
	 */
	@DeleteMapping
	private ResponseEntity<String> deleteEmployee(int employeeId) {
		
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>
	   	        ("Employee details deleted successfully", HttpStatus.NO_CONTENT);
	}

	/**
	 * This is used to assigning project to the employee.
	 * 
	 * @param employeeId from the user
	 * @param projectId  from the user
	 * @return the assigned Employee Details
	 */
	@PatchMapping("/assign")
	private ResponseEntity<EmployeeDTO> assignProject(int employeeId, int projectId) {

		return new ResponseEntity<EmployeeDTO>
		        (employeeService.assignProject(employeeId, projectId), HttpStatus.OK);

	}
}