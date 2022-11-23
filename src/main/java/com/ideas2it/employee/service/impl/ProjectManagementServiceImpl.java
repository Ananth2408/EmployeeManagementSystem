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
import org.springframework.stereotype.Component;

/**
 * This Application used to maintain the project details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
@Component
public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
    private ProjectDao projectDao;
	
	private EmployeeManagementService service;

	public EmployeeManagementService getService() {
		return service;
	}
    
	public void setService(EmployeeManagementServiceImpl service) {
		this.service = service;
	}

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

		for (Project project : projects) {
			projectDtos.add(ProjectMapper.toProjectDto(project));
		}
		return projectDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteProject(int projectId) {
		
		if (null != projectExists(projectId)) {
			projectDao.deleteById(projectId);
		} else {
			logger.error(Constant.PROJECT_NOT_FOUND + projectId);
			throw new EMSException(Constant.PROJECT_NOT_FOUND, Constant.ERROR_CODE107);
		}
		logger.info("Project deleted successfully ProjectID =" + projectId);
	}

	@Override
	public ProjectDTO assignEmployee(int employeeId, int projectId) {

		Employee employee = service.employeeExists(employeeId);
		Project project = projectExists(projectId);
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
	public Project projectExists(int projectId) {

		return projectDao.findById(projectId).orElse(null);
	}
}