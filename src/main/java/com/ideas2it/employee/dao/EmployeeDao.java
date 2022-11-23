package com.ideas2it.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ideas2it.employee.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>  {

	/**
	 * It is used to get the employee by given name.
	 * 
	 * @param name
	 * @return saved Employee deatils
	 */
	@Query("FROM Employee e WHERE e.firstName LIKE %:name%")
	public List<Employee> findByName(String name);

	/**
	 * Used to verify the given phonenumber and email id already exist or not.
	 * 
	 * @param phoneNumber from user
	 * @param email       from user
	 * @param id          from user
	 * @return duplicate exists employee details
	 */
	@Query("From Employee Where phoneNumber = :phoneNumber or email = :email and id != :id")
	public Employee checkForDuplicates(Long phoneNumber, String email, int id);
}
