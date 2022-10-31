package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.dao.ProjectDao;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.mapper.ProjectMapper;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.ProjectManagementService;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;
import com.ideas2it.employee.util.ValidateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Application used to maintain the project details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
public class ProjectManagementServiceImpl implements ProjectManagementService {
    ProjectDao projectDao = new ProjectDao();
    ValidateUtil util = new ValidateUtil();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int addProject(ProjectDTO projectDto)
                               throws EMSException {
        Project project = ProjectMapper.toProject(projectDto);
        return projectDao.addProject(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> getAllProject()
                                throws EMSException {
        List<Project> projects = projectDao.getAllProject();
        List<ProjectDTO> projectDtos = new ArrayList();

        for (Project project : projects) {
            projectDtos.add(ProjectMapper.toProjectDto(project));
        }
        return projectDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(ProjectDTO projectDto)
                                  throws EMSException {
        boolean isUpdated = false;
        Project project = ProjectMapper.toProject(projectDto);

        if (null != (projectDao.updateProject(project))) {
            isUpdated = true;
        }
        return isUpdated; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> searchProject(String name) 
                                      throws EMSException{
        List<Project> projects = projectDao.searchProject(name);
        List<ProjectDTO> projectDtos = new ArrayList();

        for (Project project : projects) {
            
            projectDtos.add(ProjectMapper.toProjectDto(project));
        }
        return projectDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProject(int projectId)
                                  throws EMSException {
        projectDao.deleteProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidData(String pattern, String field) {
        return util.isValidData(pattern, field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidStartDate(String startDate) throws EMSException{
        return util.isValidStartDate(startDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidDate(LocalDate startDate, String date)
                                      throws EMSException {
        return util.isValidDate(startDate, date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO projectExists(int projectId)
                                      throws EMSException {
        List<ProjectDTO> projectDtos = getAllProject();
        ProjectDTO projectDto = projectDtos.stream().filter(x -> x.getId() == (projectId))
                          .findFirst().orElse(null);
        return projectDto;
    }
}