package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.util.ValidateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
    Dao employeeDao = new EmployeeDao();
    ValidateUtil util = new ValidateUtil();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int addEmployee(EmployeeDTO employeeDto)
                               throws EMSException {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        return employeeDao.addEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> displayEmployee()
                                throws EMSException {
        List<Employee> employees = employeeDao.displayEmployee();
        List<EmployeeDTO> employeeDtos = new ArrayList();

        for (Employee employee : employees) {
            employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
        }
        return employeeDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateEmployee(EmployeeDTO employeeDto)
                                  throws EMSException {
        boolean isUpdated = false;
        Employee employee = EmployeeMapper.toEmployee(employeeDto);

        if (null != (employeeDao.updateEmployee(employee))) {
            isUpdated = true;
        }
        return isUpdated; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> searchEmployee(String name) 
                                      throws EMSException{
        List<Employee> employees = employeeDao.searchEmployee(name);
        List<EmployeeDTO> employeeDtos = new ArrayList();

        for (Employee employee : employees) {
            
            employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
        }
        return employeeDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEmployee(int employeeId)
                                  throws EMSException {
        employeeDao.deleteEmployee(employeeId);
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
    public EmployeeDTO isEmployeeIDExists(int employeeId)
                                      throws EMSException {
        List<EmployeeDTO> employeeDtos = displayEmployee();
        EmployeeDTO employeeDto = employeeDtos.stream().filter(x -> x.getId() == (employeeId))
                                  .findFirst().orElse(null);;
        return employeeDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidPhoneNumber(String phoneNumber) throws EMSException{
        List<EmployeeDTO> employeeDtos = displayEmployee();
        List<Long> duplicateList = employeeDtos.stream()
                                          .map(employeeDto -> Long.valueOf(employeeDto.getPhoneNumber()))
                                          .collect(Collectors.toList());

    return duplicateList.contains(Long.parseLong(phoneNumber));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEmail(String email) throws EMSException{
        List<EmployeeDTO> employeeDtos = displayEmployee();
        List<String> duplicateList = employeeDtos.stream()
                                          .map(employeeDto -> employeeDto.getEmail())
                                          .collect(Collectors.toList());

    return duplicateList.contains(email);
    }
}
