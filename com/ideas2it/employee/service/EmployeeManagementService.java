package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.EmployeeDTO;
import com.ideas2it.employee.mapper.ModelMapper;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;

import java.util.List;
import java.util.ArrayList;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version  2.1 15-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementService {
    Dao employeeDao = new EmployeeDao();
    
    /**
     * Get the value from user and create employee detail.
     * @return the boolean value.
     * @param employee object from the view class.
     */
    public boolean addEmployee(EmployeeDTO employeeDto) {
        Employee employee = ModelMapper.toEmployee(employeeDto);
        return employeeDao.addEmployee(employee); 
    }

    /**
     * Display the all employee details from the saved dteails.
     * @return the employeeDeatil from the service class.
     */
    public List<EmployeeDTO> displayEmployee() {
        List<Employee> employees = employeeDao.displayEmployee();
        List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeeDtos.add(ModelMapper.toEmployeeDTO(employee));
        }
        return employeeDtos;
    }

    /**
     * Update employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee object from the view.
     * @return the employeeDetail.
     */
    public boolean updateEmployee(EmployeeDTO employeeDto) {
        Employee employee = ModelMapper.toEmployee(employeeDto);
        return employeeDao.updateEmployee(employee); 
    }

    /**
     * Search employee details by employee name,
     * If name found it update employee details else it doesn't.
     * @param employee name from user.
     * @return if employee fount returns searchemployee else it returns null .
     */
    public EmployeeDTO searchEmployee(String employeeName) {
        EmployeeDTO searchEmployee = null;
        List<Employee> employeeDtos = employeeDao.displayEmployee();
        for(int i = 0; i < employeeDtos.size(); i++) {
            if(employeeDtos.get(i).getName().equals(employeeName)) {
                searchEmployee = employeeDtos.get(i);
            }
        }
        return searchEmployee;
    }

   /**
     * Delete employee details by employee name,
     * if name found it deletes emplyee deatils else it doesn't.
     * @return the employeeDeatil from the service class.
     */
   public boolean deleteEmployee(String employeeName) {
       EmployeeDTO searchEmployee;
       List<Employee> employeeDtos = employeeDao.displayEmployee();
       Employee employee = null; 
       for(int i = 0; i < employeeDtos.size(); i++) {
           searchEmployee = employeeDtos.get(i);
           if(searchEmployee.getName().equals(employeeName)) {
                employee = searchEmployee;
            }
       }
       return employeeDao.deleteEmployee(employee);
   }
}
