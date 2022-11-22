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
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * This Application used to maintain the employee details. Create, read, update
 * and delete operations were done in this application.
 * 
 * @version 4.1 10-10-2022.
 * @author Ananth K.
 */
@Component("employeeService")
@Configuration
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ApplicationContext context;
	
	private Logger logger = LogManager.getLogger(EmployeeManagementServiceImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto) {
		EmployeeDTO employeeDTO = null;

		if (!isValidPhoneNumber(String.valueOf(employeeDto.getPhoneNumber())) 
				& !isValidEmail(employeeDto.getEmail())) {
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
			if (!isValidPhoneNumber(String.valueOf(employeeDto.getPhoneNumber()))
					& !isValidEmail(employeeDto.getEmail())) {
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

		if (!employees.isEmpty()) {
			for (Employee employee : employees) {

				employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
			}
		} else {
			logger.error(Constant.EMPLOYEE_NOT_FOUND);
			throw new EMSException(Constant.EMPLOYEE_NOT_FOUND, Constant.ERROR_CODE102);
		}
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(int employeeId) {
		employeeDao.deleteById(employeeId);
		logger.info("Employee deleted successfully EmploeeID =" + employeeId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO assignProject(int employeeId, int projectId) {

		ProjectManagementService service = (ProjectManagementServiceImpl) 
				context.getBean("projectService");
		Employee employee = EmployeeMapper.toEmployee(employeeExists(employeeId));
		Project project = EmployeeMapper.toProject(service.projectExists(projectId));
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
	public EmployeeDTO employeeExists(int employeeId) {
		List<EmployeeDTO> employeeDtos = getAllEmployees();
		EmployeeDTO employeeDto = employeeDtos.stream().
				filter(x -> x.getId() == (employeeId)).findFirst().orElse(null);
		return employeeDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidPhoneNumber(String phoneNumber) {
		List<EmployeeDTO> employeeDtos = getAllEmployees();
		List<Long> duplicateList = employeeDtos.stream()
				.map(employeeDto -> Long.valueOf(employeeDto.getPhoneNumber()))
				.collect(Collectors.toList());

		return duplicateList.contains(Long.parseLong(phoneNumber));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidEmail(String email) {
		List<EmployeeDTO> employeeDtos = getAllEmployees();
		List<String> duplicateList = employeeDtos.stream()
				.map(employeeDto -> employeeDto.getEmail())
				.collect(Collectors.toList());

		return duplicateList.contains(email);
	}
}
