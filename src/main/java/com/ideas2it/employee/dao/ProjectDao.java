package com.ideas2it.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.employee.model.Project;

public interface ProjectDao extends JpaRepository<Project, Integer>{

	/**
	 * It is used to get the project from the user given name.
	 * 
	 * @param name
	 * @return
	 */
	@Query("FROM Project p WHERE p.projectName LIKE '%:name%'")
	List<Project> findByName(String name);
}
