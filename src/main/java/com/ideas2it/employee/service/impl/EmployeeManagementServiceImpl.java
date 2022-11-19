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
import com.ideas2it.employee.util.ValidateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	EmployeeDao employeeDao;
	
	@Autowired
	ApplicationContext context;

	ValidateUtil util = new ValidateUtil();
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto) throws EMSException {
		EmployeeDTO employeeDTO = null;
		
		if (!isValidPhoneNumber(String.valueOf(employeeDto.getPhoneNumber())) & 
				!isValidEmail(employeeDto.getEmail())) {
			Employee employee = EmployeeMapper.toEmployee(employeeDto);
			employeeDTO = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));
		} else {
			throw new EMSException(Constant.DUPLICATE , Constant.ERROR_CODE101);
		}
		
		return employeeDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDTO> getAllEmployees() throws EMSException {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();

		if (employees != null) {
			for (Employee employee : employees) {
				employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
			}
		} else {
			throw new EMSException(Constant.EMPLOYEE_NOT_FOUND , Constant.ERROR_CODE102);
		}
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto) throws EMSException {
		EmployeeDTO employeeDTO = null;
		
		if (!isValidPhoneNumber(String.valueOf(employeeDto.getPhoneNumber())) & 
				!isValidEmail(employeeDto.getEmail())) {
			Employee employee = EmployeeMapper.toEmployee(employeeDto);
			employeeDTO = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));
		} else {
			throw new EMSException(Constant.DUPLICATE , Constant.ERROR_CODE101);
		}
		return employeeDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDTO> searchEmployee(String name) throws EMSException {
		List<Employee> employees = employeeDao.findByName(name);
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();

		if (employees != null) {
			for (Employee employee : employees) {

				employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
			}
		} else {
			throw new EMSException(Constant.EMPLOYEE_NOT_FOUND , Constant.ERROR_CODE102 );
		}
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(int employeeId) throws EMSException {
		employeeDao.deleteById(employeeId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO assignProject(int employeeId, int projectId) 
			throws EMSException {

		ProjectManagementService service = (ProjectManagementServiceImpl) context
				.getBean("projectService");
		Employee employee = EmployeeMapper.toEmployee(employeeExists(employeeId));
		Project project = EmployeeMapper.toProject(service.projectExists(projectId));
		EmployeeDTO employeeDto = null;
		
		if (employee != null & project != null) {
			employee.getProject().add(project);
			employeeDto = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));

		} else {
			throw new EMSException(Constant.DETALILS_NOTEXIST , Constant.ERROR_CODE103); 
		}
		return employeeDto;
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
    public boolean isValidBirthDate(String birthDate) throws EMSException{
        return util.isValidBirthDate(birthDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidJoiningDate(LocalDate birthDate, String joiningDate)
                                      throws EMSException {
        return util.isValidJoiningDate(birthDate, joiningDate);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDTO employeeExists(int employeeId) throws EMSException {
		List<EmployeeDTO> employeeDtos = getAllEmployees();
		EmployeeDTO employeeDto = employeeDtos.stream()
				.filter(x -> x.getId() == (employeeId)).findFirst().orElse(null);
		return employeeDto;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isValidPhoneNumber(String phoneNumber) throws EMSException {
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
	public boolean isValidEmail(String email) throws EMSException {
		List<EmployeeDTO> employeeDtos = getAllEmployees();
		List<String> duplicateList = employeeDtos.stream()
				.map(employeeDto -> employeeDto.getEmail())
				.collect(Collectors.toList());

		return duplicateList.contains(email);
	}
}
