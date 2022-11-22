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
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This Application used to maintain the project details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
@Component("projectService")
public class ProjectManagementServiceImpl implements ProjectManagementService {
    
	@Autowired
    private ProjectDao projectDao;
	@Autowired
	private ApplicationContext context;
	
	private Logger logger = LogManager.getLogger(ProjectManagementServiceImpl.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO addProject(ProjectDTO projectDto) {
        Project project = ProjectMapper.toProject(projectDto);
        ProjectDTO projectDTO = ProjectMapper.toProjectDto(projectDao.save(project));
        logger.info("Project created succuessfully ProjectID =" + projectDto.getId());
        return projectDTO;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProjectDTO> getAllProjects() {
		List<Project> projects = projectDao.findAll();
		List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();

		for (Project project : projects) {
			projectDtos.add(ProjectMapper.toProjectDto(project));
		}
		return projectDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectDTO updateProject(ProjectDTO projectDto) {
		Project project = ProjectMapper.toProject(projectDto);
		ProjectDTO projectDTO = ProjectMapper.toProjectDto(projectDao.save(project));
		return projectDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProjectDTO> searchProject(String name) {
		List<Project> projects = projectDao.findByName(name);
		List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();

		if (!projects.isEmpty()) {
			for (Project project : projects) {

				projectDtos.add(ProjectMapper.toProjectDto(project));
			}
		} else {
			logger.error(Constant.PROJECT_NOT_FOUND);
			throw new EMSException(Constant.PROJECT_NOT_FOUND, Constant.ERROR_CODE104);
		}
		return projectDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteProject(int projectId) {
		projectDao.deleteById(projectId);
		logger.info("Project deleted successfully ProjectID =" + projectId);
	}

	@Override
	public ProjectDTO assignEmployee(int employeeId, int projectId) {

		EmployeeManagementService service = (EmployeeManagementServiceImpl) 
				context.getBean("employeeService");
		Employee employee = ProjectMapper.toEmployee(service.employeeExists(employeeId));
		Project project = ProjectMapper.toProject(projectExists(projectId));
		ProjectDTO projectDto = null;

		try {
			if (employee != null & project != null) {
				project.getEmployee().add(employee);
				projectDto = ProjectMapper.toProjectDto(projectDao.save(project));
			} else {
				logger.info(Constant.DETALILS_NOTEXIST);
				throw new EMSException(Constant.DETALILS_NOTEXIST, Constant.ERROR_CODE103);
			}
		} catch (ConstraintViolationException e) {
			logger.error(e.getMessage());
			throw new EMSException(Constant.UPDATION_EXCEPTION, Constant.ERROR_CODE105);
		}
		return projectDto;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO projectExists(int projectId) {
        List<ProjectDTO> projectDtos = getAllProjects();
        ProjectDTO projectDto = projectDtos.stream().filter(x -> x.getId() == (projectId))
                          .findFirst().orElse(null);
        return projectDto;
    }
}