package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.dao.ProjectDao;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.mapper.ProjectMapper;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.ProjectManagementService;
import com.ideas2it.employee.util.ValidateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * This Application used to maintain the project details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
@Component("projectService")
@Configuration
public class ProjectManagementServiceImpl implements ProjectManagementService {
    
	@Autowired
    ProjectDao projectDao;
	@Autowired
	ApplicationContext context;

	ValidateUtil util = new ValidateUtil();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO addProject(ProjectDTO projectDto)
                               throws EMSException {
        Project project = ProjectMapper.toProject(projectDto);
        ProjectDTO projectDTO = ProjectMapper.toProjectDto(projectDao.save(project));
        return projectDTO;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProjectDTO> getAllProjects() throws EMSException {
		List<Project> projects = projectDao.findAll();
		List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();

		if (projects != null) {
			for (Project project : projects) {
				projectDtos.add(ProjectMapper.toProjectDto(project));
			}
		} else {
			throw new EMSException(Constant.PROJECT_NOT_FOUND , Constant.ERROR_CODE104);
		}
		return projectDtos;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO updateProject(ProjectDTO projectDto)
                                  throws EMSException {
        Project project = ProjectMapper.toProject(projectDto);
        ProjectDTO projectDTO = ProjectMapper.toProjectDto(projectDao.save(project));
        return projectDTO; 
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProjectDTO> searchProject(String name) throws EMSException {
		List<Project> projects = projectDao.findByName(name);
		List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();

		if (projects != null) {
			for (Project project : projects) {

				projectDtos.add(ProjectMapper.toProjectDto(project));
			}
		} else {
			throw new EMSException(Constant.PROJECT_NOT_FOUND , Constant.ERROR_CODE104);
		}
		return projectDtos;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProject(int projectId)
                                  throws EMSException {
        projectDao.deleteById(projectId);
    }
    
	@Override
	public ProjectDTO assignEmployee(int employeeId, int projectId) throws EMSException {

		EmployeeManagementService service = (EmployeeManagementServiceImpl) context.getBean("employeeService");
		Employee employee = ProjectMapper.toEmployee(service.employeeExists(employeeId));
		Project project = ProjectMapper.toProject(projectExists(projectId));
		ProjectDTO projectDto = null;

		if (employee != null & project != null) {
			project.getEmployee().add(employee);
			projectDto = ProjectMapper.toProjectDto(projectDao.save(project));
		} else {
			throw new EMSException(Constant.DETALILS_NOTEXIST , Constant.ERROR_CODE103);
		}
		return projectDto;
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
        List<ProjectDTO> projectDtos = getAllProjects();
        ProjectDTO projectDto = projectDtos.stream().filter(x -> x.getId() == (projectId))
                          .findFirst().orElse(null);
        return projectDto;
    }
}