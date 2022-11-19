package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import java.time.LocalDate;
import java.util.List;

/**
 * @interface ProjectManagementService. 
 * Project details manipulation were done.
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
	public ProjectDTO addProject(ProjectDTO projectDto) throws EMSException;

	/**
	 * Display the all project details from the saved dteails.
	 * 
	 * @return the projectDeatil from the service class.
	 */
	public List<ProjectDTO> getAllProjects() throws EMSException;

	/**
	 * Update project details by projectid, If name found it update project 
	 * details else it doesn't.
	 * 
	 * @param project object from the view.
	 * @return the boolean value if updated it returns true else false.
	 */
	public ProjectDTO updateProject(ProjectDTO ProjectDto) throws EMSException;

	/**
	 * Search project details by project name, If name found it update project
	 * details else it doesn't.
	 * 
	 * @param project name from user.
	 * @return if project found returns project else it returns null .
	 */
	public List<ProjectDTO> searchProject(String name) throws EMSException;

	/**
	 * Delete project details by project name, if name found it deletes project
	 * deatils else it doesn't.
	 * 
	 * @return the boolean value if deleted it returns true else false.
	 */
	public void deleteProject(int projectId) throws EMSException;

	/**
	 * Used to validate the given input is valid or not.
	 * 
	 * @param pattern is regex pattern.
	 * @param field   is values, input from the users.
	 * @return if it is valid it returns true else rturns false.
	 */
	public boolean isValidData(String pattern, String field);

	/**
	 * Used to validate the given input is valid or not.
	 * 
	 * @param date from the user.
	 * @return if it is valid it returns localdate else ask again.
	 */
	public boolean isValidStartDate(String startDate) throws EMSException;

	/**
	 * Used to validate the given input is valid or not.
	 * 
	 * @param date from the user.
	 * @return if it is valid it returns localdate else ask again.
	 */
	public boolean isValidDate(LocalDate startDate, String date)
			throws EMSException;

	/**
	 * Used to validate the given project present in the dat or not.
	 * 
	 * @param project id from the user.
	 * @return if project id persents returns true else returns false.
	 */
	public ProjectDTO projectExists(int projectId) throws EMSException;

	/**
     * Used to assign a project to the employee.
     * 
     * @param employeeId from the user.
     * @param projectId from the user.
     * @return project details.
     * @throws EMSException
     */
	public ProjectDTO assignEmployee(int employeeId, int projectId) throws EMSException;
}