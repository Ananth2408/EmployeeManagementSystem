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
    	List<Employee> employees = new ArrayList<Employee>();

    	if (null != projectDto) {
    	    project.setId(projectDto.getId());
    	    project.setProjectName(projectDto.getProjectName());
    	    project.setTechnology(projectDto.getTechnology());
    	    project.setClientName(projectDto.getClientName());
    	    project.setClientMailId(projectDto.getClientMailId());
    	    project.setStartDate(projectDto.getStartDate());
            project.setDueDate(projectDto.getDueDate());
    	    project.setEndDate(projectDto.getEndDate());
    	    
    	    if (null != projectDto.getEmployee()) {

                for (EmployeeDTO employeeDto: projectDto.getEmployee()) {
                    employees.add(toEmployee(employeeDto));
                }
            project.setEmployee(employees);
            }
    	}
    	return project;
    }

	public static ProjectDTO toProjectDto(Project project) {
		ProjectDTO projectDto = null;

		if (null != project) {
			projectDto = new ProjectDTO();
			List<EmployeeDTO> employeeDto = new ArrayList<EmployeeDTO>();

			projectDto.setId(project.getId());
			projectDto.setProjectName(project.getProjectName());
			projectDto.setTechnology(project.getTechnology());
			projectDto.setClientName(project.getClientName());
			projectDto.setClientMailId(project.getClientMailId());
			projectDto.setStartDate(project.getStartDate());
			projectDto.setDueDate(project.getDueDate());
			projectDto.setEndDate(project.getEndDate());

			if (null != project.getEmployee()) {

				for (Employee employee : project.getEmployee()) {
					employeeDto.add(toEmployeeDto(employee));
				}
				projectDto.setEmployee(employeeDto);
			}
		}
		return projectDto;
	}

	public static Employee toEmployee(EmployeeDTO employeeDto) {
		Employee employee = new Employee();

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

		return employee;
	}

	public static EmployeeDTO toEmployeeDto(Employee employee) {
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

		return employeeDto;
	}
}