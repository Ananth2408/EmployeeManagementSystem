package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.mapper.EmployeeMapper;
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
 * This Application used to maintain the employee details. Create, read, update
 * and delete operations were done in this application.
 * 
 * @version 4.1 10-10-2022.
 * @author Ananth K.
 */
@Component
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
	
	@Autowired
	private EmployeeDao employeeDao;

	private ProjectManagementService service;

	public ProjectManagementService getService() {
		return service;
	}

	public void setService(ProjectManagementServiceImpl service) {
		this.service = service;
	}

	private Logger logger = LogManager.getLogger(EmployeeManagementServiceImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto) {
		EmployeeDTO employeeDTO = null;
		int employeeId = 0;

		if (null == employeeDao.checkForDuplicates(employeeDto.getPhoneNumber(),
				employeeDto.getEmail(), employeeId)) {
			Employee employee = EmployeeMapper.toEmployee(employeeDto);
			employeeDTO = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));
		} else {
			logger.error(Constant.DUPLICATE, Constant.ERROR_CODE101);
			throw new EMSException(Constant.DUPLICATE, Constant.ERROR_CODE101);
		}

		logger.info("Employee created EmployeeID =" + employeeDto.getId());
		return employeeDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();

		for (Employee employee : employees) {
			employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
		}
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto) {
		EmployeeDTO employeeDTO = null;

		try {
			if (null == employeeDao.checkForDuplicates(employeeDto.getPhoneNumber(), 
					employeeDto.getEmail(), employeeDto.getId())) {
				Employee employee = EmployeeMapper.toEmployee(employeeDto);
				employeeDTO = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));
			} else {
				logger.error(Constant.DUPLICATE, Constant.ERROR_CODE101);
				throw new EMSException(Constant.DUPLICATE, Constant.ERROR_CODE101);
			}
		} catch (ConstraintViolationException e) {
			logger.error(e.getMessage());
			throw new EMSException(Constant.UPDATION_EXCEPTION, Constant.ERROR_CODE105);
		}
		return employeeDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDTO> searchEmployee(String name) {
		List<Employee> employees = employeeDao.findByName(name);
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();

		for (Employee employee : employees) {

			employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
		}
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(int employeeId) {
		
		if (null != employeeExists(employeeId)) {
			employeeDao.deleteById(employeeId);
		} else {
			logger.error(Constant.EMPLOYEE_NOT_FOUND + employeeId);
			throw new EMSException(Constant.EMPLOYEE_NOT_FOUND, Constant.ERROR_CODE106);
		}
		logger.info("Employee deleted successfully EmploeeID =" + employeeId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO assignProject(int employeeId, int projectId) {

		Employee employee = employeeExists(employeeId);
		Project project = service.projectExists(projectId);
		EmployeeDTO employeeDto = null;

		if (employee != null & project.getId() != 0) {
			employee.getProject().add(project);
			employeeDto = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));

		} else {
			logger.info(Constant.DETALILS_NOTEXIST);
			throw new EMSException(Constant.DETALILS_NOTEXIST, Constant.ERROR_CODE103);
		}
		return employeeDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee employeeExists(int employeeId) {

		return employeeDao.findById(employeeId).orElse(null);
	}
}
