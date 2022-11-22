package com.ideas2it.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.service.ProjectManagementService;

/**
 * This Application used to maintain the project details. Create, read, update
 * and delete operations were done in this application.
 * 
 * @version 4.1 10-10-2022.
 * @author Ananth K.
 */
@RestController
@RequestMapping("/api.com.ideas2it/employee-management-system/v1.0/project")
public class ProjectController {
	@Autowired
	private ProjectManagementService projectService;

	/**
	 * Get the value from user and create project detail.
	 * 
	 * @return the boolean value of added project details.
	 * @param projectdto details.
	 */
	@PostMapping
	private ResponseEntity<ProjectDTO> addProject(ProjectDTO projectDto) {

		return new ResponseEntity<ProjectDTO>
				(projectService.addProject(projectDto), HttpStatus.CREATED);
	}

	/**
	 * Display the all employee details from the saved details.
	 * 
	 * @return the employeeDeatil.
	 */
	@GetMapping
	private ResponseEntity<List<ProjectDTO>> getAllProjects() {

		return new ResponseEntity<List<ProjectDTO>>
				(projectService.getAllProjects(), HttpStatus.OK);
	}

	/**
	 * Update employee details by employee name, If name found it update employee
	 * details else it doesn't.
	 * 
	 * @param employeedto details.
	 * @return the boolean value if updated returns true else false.
	 */
	@PatchMapping
	private ResponseEntity<ProjectDTO> updateProject(ProjectDTO projectDto) {

		return new ResponseEntity<ProjectDTO>
				(projectService.updateProject(projectDto), HttpStatus.ACCEPTED);

	}

	/**
	 * Search employee details by employee name, If name found it update employee
	 * details else it doesn't.
	 * 
	 * @param employee name from user.
	 * @return
	 */
	@GetMapping("/searchproject")
	private ResponseEntity<List<ProjectDTO>> searchProject(String name) {

		return new ResponseEntity<List<ProjectDTO>>
				(projectService.searchProject(name), HttpStatus.OK);
	}

	/**
	 * Delete employee details by employee id, if name found it deletes employee
	 * deatils else it doesn't.
	 * 
	 * @param employee id from user.
	 * @return the boolean value if deletes return true else false.
	 */
	@DeleteMapping
	private ResponseEntity<String> deleteProject(int employeeId) {

		projectService.deleteProject(employeeId);
		return new ResponseEntity<>("Project details deleted successfully", HttpStatus.NO_CONTENT);
	}

	/**
	 * This is used to assigning a employee into the project.
	 * 
	 * @param employeeId    from the user.
	 * @param projectIdfrom the user.
	 * @return project values.
	 */
	@PatchMapping("/assignemployee")
	private ResponseEntity<ProjectDTO> assignEmployee
	(int employeeId, int projectId) {
		
		return new ResponseEntity<ProjectDTO>
				(projectService.assignEmployee(employeeId, projectId),
						HttpStatus.CREATED);
	}
}