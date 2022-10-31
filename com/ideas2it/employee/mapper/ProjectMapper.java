package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper convert the modelDto details to model details and
 *
 * @Version 4.1 10-10-2022
 * @author  Ananth k.
 */
public class ProjectMapper {

    public static Project toProject(ProjectDTO projectDto) {
    	Project project = new Project();

    	if (null != projectDto) {
    	    project.setId(projectDto.getId());
    	    project.setProjectName(projectDto.getProjectName());
    	    project.setTechnology(projectDto.getTechnology());
    	    project.setClientName(projectDto.getClientName());
    	    project.setClientMailId(projectDto.getClientMailId());
    	    project.setStartDate(projectDto.getStartDate());
            project.setDueDate(projectDto.getDueDate());
    	    project.setEndDate(projectDto.getEndDate());
    	    project.setEmployee(toEmployee(projectDto.getEmployee()));
    	}
    	return project;
    }

    public static ProjectDTO toProjectDto(Project project) {
    	ProjectDTO projectDto = null;

    	if (null != project) {
	    	projectDto = new ProjectDTO();

	    	projectDto.setId(project.getId());
	    	projectDto.setProjectName(project.getProjectName());
	    	projectDto.setTechnology(project.getTechnology());
	    	projectDto.setClientName(project.getClientName());
	    	projectDto.setClientMailId(project.getClientMailId());
	    	projectDto.setStartDate(project.getStartDate());
                projectDto.setDueDate(project.getDueDate());
	    	projectDto.setEndDate(project.getEndDate());
	    	projectDto.setEmployee(toEmployeeDto(project.getEmployee()));
	    }
    	return projectDto;
    }

    public static List<Employee> toEmployee(List<EmployeeDTO> employeesDto) {
    	List<Employee> employees = null;
    	Employee employee = null;

    	if (null != employeesDto) {
            employees = new ArrayList();

            for (EmployeeDTO employeeDto : employeesDto) {
                employee = new Employee();

                employee.setId(employeeDto.getId());
                employee.setFirstName(employeeDto.getFirstName());
                employee.setLastName(employeeDto.getLastName());
                employee.setGender(employeeDto.getGender());
                employee.setRole(employeeDto.getRole());
                employee.setPhoneNumber(employeeDto.getPhoneNumber());
                employee.setEmail(employeeDto.getEmail());
                employee.setDateOfBirth(employeeDto.getDateOfBirth());
                employee.setDateOfJoining(employeeDto.getDateOfJoining());
                employee.setSalary(employeeDto.getSalary());
                employees.add(employee);
             }
         }
	    return employees;
    }

    public static List<EmployeeDTO> toEmployeeDto(List<Employee> employees) {
    	List<EmployeeDTO> employeesDto = new ArrayList();

    	if (null != employees) {

    	    for (Employee employee : employees) {
                EmployeeDTO employeeDto = new EmployeeDTO();

                employeeDto.setId(employee.getId());
                employeeDto.setFirstName(employee.getFirstName());
                employeeDto.setLastName(employee.getLastName());
                employeeDto.setGender(employee.getGender());
                employeeDto.setRole(employee.getRole());
                employeeDto.setPhoneNumber(employee.getPhoneNumber());
	        employeeDto.setEmail(employee.getEmail());
                employeeDto.setDateOfBirth(employee.getDateOfBirth());
                employeeDto.setDateOfJoining(employee.getDateOfJoining());
                employeeDto.setSalary(employee.getSalary());
                employeesDto.add(employeeDto);
    	    }
    	}
        return employeesDto;
    }
}