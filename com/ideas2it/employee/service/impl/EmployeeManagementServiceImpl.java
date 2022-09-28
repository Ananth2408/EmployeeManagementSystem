package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.service.EmployeeManagementService;

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
    
    /**
     * {@inheritDoc}
     */
    public boolean addEmployee(EmployeeDTO employeeDto) {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        return employeeDao.addEmployee(employee); 
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> displayEmployee() {
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
    public boolean updateEmployee(EmployeeDTO employeeDto, int employeeId) {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        return employeeDao.updateEmployee(employee, employeeId); 
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO searchEmployee(String name) {
        EmployeeDTO employeeDto = EmployeeMapper.toEmployeeDTO(employeeDao.searchEmployee(name));
        return employeeDto;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployee(int employeeId) {
        return employeeDao.deleteEmployee(employeeId);
    }
}
