package com.ideas2it.employee.controller;

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
@RequestMapping("/api/v1/ems/project")
public class ProjectController {
	
	@Autowired
	private ProjectManagementService projectService;

	/**
	 * Get the value from user and create project detail.
	 * 
	 * @return the value of added project details.
	 * @param projectdto details.
	 */
	@PostMapping
	private ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDto) {

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
	 * Update project details by given project details name
	 * else it doesn't.
	 * 
	 * @param projectdto details.
	 * @return the updated project details.
	 */
	@PatchMapping
	private ResponseEntity<ProjectDTO> updateProject(ProjectDTO projectDto) {

		return new ResponseEntity<ProjectDTO>
				(projectService.updateProject(projectDto), HttpStatus.OK);

	}

	/**
	 * Search project details by project name, If name found it update project
	 * details else it doesn't.
	 * 
	 * @param project name from user.
	 * @return searched project details
	 */
	@GetMapping("/search")
	private ResponseEntity<List<ProjectDTO>> searchProject(String name) {

		return new ResponseEntity<List<ProjectDTO>>
		        (projectService.searchProject(name), HttpStatus.OK);
	}

	/**
	 * Delete project details by project id, if name found it deletes project
	 * deatils else it doesn't.
	 * 
	 * @param project id from user.
	 * @return the message.
	 */
	@DeleteMapping
	private ResponseEntity<String> deleteProject(int employeeId) {

		projectService.deleteProject(employeeId);
		return new ResponseEntity<>
		        ("Project details deleted successfully", HttpStatus.NO_CONTENT);
	}

	/**
	 * This is used to assigning a employee into the project.
	 * 
	 * @param employeeId    from the user.
	 * @param projectIdfrom the user.
	 * @return project values.
	 */
	@PatchMapping("/assign")
	private ResponseEntity<ProjectDTO> assignEmployee
	(int employeeId, int projectId) {
		
		return new ResponseEntity<ProjectDTO>
		        (projectService.assignEmployee(employeeId, projectId), HttpStatus.OK);
	}
}