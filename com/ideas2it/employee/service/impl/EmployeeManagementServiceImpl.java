package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.exception.EmployeeManagementSystemException;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.util.EmployeeManagementUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version  4.0 28-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
    Dao employeeDao = new EmployeeDao();
    EmployeeManagementUtil util = new EmployeeManagementUtil();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEmployee(EmployeeDTO employeeDto)
                               throws EmployeeManagementSystemException {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        return employeeDao.addEmployee(employee); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> displayEmployee()
                                throws EmployeeManagementSystemException {
        List<Employee> employees = employeeDao.displayEmployee();
        List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
        }
        return employeeDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateEmployee(EmployeeDTO employeeDto, int employeeId)
                                  throws EmployeeManagementSystemException {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        return employeeDao.updateEmployee(employee, employeeId); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO searchEmployee(String name) 
                                      throws EmployeeManagementSystemException{
        EmployeeDTO employeeDto = EmployeeMapper.toEmployeeDTO(employeeDao.searchEmployee(name));
        return employeeDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int employeeId)
                                  throws EmployeeManagementSystemException {
        return employeeDao.deleteEmployee(employeeId);
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
    public LocalDate date(String date) {
        return util.dates(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeIDExists(int employeeId)
                                      throws EmployeeManagementSystemException {
        return employeeDao.isEmployeeIDExists(employeeId);
    }
}
