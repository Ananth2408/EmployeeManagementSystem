package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.ProjectDTO;
import java.util.List;

/**
 * @interface ProjectManagementService. Project details manipulation were done.
 * 
 * @version 4.1 10-10-2022.
 * @author Ananth K.
 */
public interface ProjectManagementService {

	/**
	 * Get the value from user and create project detail.
	 * 
	 * @return the boolean value.
	 * @param project object from the view class.
	 */
	public ProjectDTO addProject(ProjectDTO projectDto);

	/**
	 * Display the all project details from the saved dteails.
	 * 
	 * @return the projectDeatil from the service class.
	 */
	public List<ProjectDTO> getAllProjects();

	/**
	 * Update project details by projectid, If name found it update project details
	 * else it doesn't.
	 * 
	 * @param project object from the view.
	 * @return the boolean value if updated it returns true else false.
	 */
	public ProjectDTO updateProject(ProjectDTO ProjectDto);

	/**
	 * Search project details by project name, If name found it update project
	 * details else it doesn't.
	 * 
	 * @param project name from user.
	 * @return if project found returns project else it returns null .
	 */
	public List<ProjectDTO> searchProject(String name);

	/**
	 * Delete project details by project name, if name found it deletes project
	 * deatils else it doesn't.
	 * 
	 * @return the boolean value if deleted it returns true else false.
	 */
	public void deleteProject(int projectId);

	/**
	 * Used to validate the given project present in the dat or not.
	 * 
	 * @param project id from the user.
	 * @return if project id persents returns true else returns false.
	 */
	public ProjectDTO projectExists(int projectId);

	/**
	 * Used to assign a project to the employee.
	 * 
	 * @param employeeId from the user.
	 * @param projectId  from the user.
	 * @return project details.
	 */
	public ProjectDTO assignEmployee(int employeeId, int projectId);
}