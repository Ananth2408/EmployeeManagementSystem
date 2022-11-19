package com.ideas2it.employee.controller;

import java.time.LocalDate;
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
import com.ideas2it.employee.exception.EMSException;
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
	ProjectManagementService projectService;

	/**
	 * Get the value from user and create project detail.
	 * 
	 * @return the boolean value of added project details.
	 * @param projectdto details.
	 */
	@PostMapping
	public ProjectDTO addProject(ProjectDTO projectDto)
			throws EMSException {

		return projectService.addProject(projectDto);
	}

	/**
	 * Display the all employee details from the saved details.
	 * 
	 * @return the employeeDeatil.
	 */
	@GetMapping
	public List<ProjectDTO> getAllProjects() throws EMSException {
		
		return projectService.getAllProjects();
	}

	/**
	 * Update employee details by employee name, If name found it update employee
	 * details else it doesn't.
	 * 
	 * @param employeedto details.
	 * @return the boolean value if updated returns true else false.
	 */
	@PatchMapping
	public ProjectDTO updateProject(ProjectDTO projectDto) throws EMSException {

		return projectService.updateProject(projectDto);

	}

	/**
	 * Search employee details by employee name, If name found it update employee
	 * details else it doesn't.
	 * 
	 * @param employee name from user.
	 * @return
	 */
	@GetMapping("/searchproject")
	public List<ProjectDTO> searchProject(String name)
			throws EMSException {
		
		return projectService.searchProject(name);
	}
	
	/**
	 * Delete employee details by employee id, if name found it deletes employee
	 * deatils else it doesn't.
	 * 
	 * @param employee id from user.
	 * @return the boolean value if deletes return true else false.
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteProject(int employeeId)
			throws EMSException {
		
		projectService.deleteProject(employeeId);
		return new ResponseEntity<>
		("Project details deleted successfully", HttpStatus.OK);
	}
    
	/**
	 * This is used to assigning a employee into the project.
	 * 
	 * @param employeeId from the user.
	 * @param projectIdfrom the user.
	 * @return project values.
	 * @throws EMSException.
	 */
	@PatchMapping("/assignemployee")
	public ProjectDTO assignEmployee(int employeeId,
			int projectId) throws EMSException {
		return projectService.assignEmployee(employeeId, projectId);
	}
    
    /**
     * Used to validate the given input is valid or not.
     * @param pattern is regex pattern.
     * @param field is values, input from the users.
     * @return if it is valid it returns true else returns false.
     */
    public boolean isValidData(String pattern, String field) {
        return projectService.isValidData(pattern, field);
    }

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns true else false.
     */
    public boolean isValidStartDate(String startDate) throws EMSException {
        return projectService.isValidStartDate(startDate);
    }

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns true else false.
     */
    public boolean isValidDate(LocalDate startDate, String date)
                                                           throws EMSException {
        return projectService.isValidDate(startDate, date);
    }

    /**
     * Used to validate the given employee present in the dat or not.
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
   /*public EmployeeDTO getEmployee(int employeeId)
                                    throws EMSException {
        EmployeeManagementService employeeService = new EmployeeManagementServiceImpl();
        return employeeService.employeeExists(employeeId);
    }*/

    
    /**
     * Used to validate the given employee present in the dat or not.O
     * @param employee id from the user.
     * @return if employee id persents returns true else returns false.
     */
    public ProjectDTO projectExists(int projectId)
                                    throws EMSException {
        return projectService.projectExists(projectId);
    }
}