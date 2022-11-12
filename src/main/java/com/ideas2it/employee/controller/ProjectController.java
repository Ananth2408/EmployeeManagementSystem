package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;
import com.ideas2it.employee.service.ProjectManagementService;
import com.ideas2it.employee.service.impl.ProjectManagementServiceImpl;
import com.ideas2it.employee.view.ProjectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This Application used to maintain the project details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
public class ProjectController {
    ProjectManagementService projectService = new ProjectManagementServiceImpl();

    /**
     * Get the value from user and create project detail.
     * @return the boolean value of added project details.
     * @param  projectdto details.
     */
    public int addProject(ProjectDTO projectDto) throws EMSException {
        return projectService.addProject(projectDto);
    }

    /**
     * Display the all employee details from the saved details.
     * @return the employeeDeatil.
     */
    public List<ProjectDTO> getAllProject() throws EMSException {
        return projectService.getAllProject();
    }

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employeedto details.
     * @return the boolean value if updated returns true else false.
     */
    public boolean updateProject(ProjectDTO projectDto)
                                              throws EMSException {
        return projectService.updateProject(projectDto);
    }

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee found returns employeedetails else it returns null.
     */
    public List<ProjectDTO> searchProject(String name) 
                                  throws EMSException {
        return projectService.searchProject(name);
    }

    /**
     * Delete employee details by employee name,
     * if name found it deletes employee deatils else it doesn't.
     * @param employee name from user.
     * @return the boolean value if deletes return true else false.
     */
    public void deleteProject(int projectId)
                                  throws EMSException {
        projectService.deleteProject(projectId);
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
   public EmployeeDTO getEmployee(int employeeId)
                                    throws EMSException {
        EmployeeManagementService employeeService = new EmployeeManagementServiceImpl();
        return employeeService.employeeExists(employeeId);
    }

    
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