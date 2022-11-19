package com.ideas2it.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.employee.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>  {

	/**
	 * It is used to get the employee from the user given name.
	 * 
	 * @param name
	 * @return
	 */
	@Query("FROM Employee e WHERE e.firstName LIKE %:name%")
	List<Employee> findByName(String name);

}
