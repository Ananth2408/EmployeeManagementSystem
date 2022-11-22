package com.ideas2it.employee.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Get Project details from the user.
 * Uses getter setter to get input from user.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
@Component
public class ProjectDTO {
    private int id;
    private String projectName;
    private String technology;
    private String clientName;
    private String clientMailId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate endDate;
    private List<EmployeeDTO> employee;

    public ProjectDTO() {}

    public ProjectDTO(int id, String projectName, String technology,
                   String clientName, String clientMailId,
                   LocalDate startDate, LocalDate dueDate,
                   LocalDate endDate, List<EmployeeDTO> employee) {
        this.id = id;
        this.projectName = projectName;
        this.technology = technology;
        this.clientName = clientName;
        this.clientMailId = clientMailId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMailId() {
        return clientMailId;
    }

    public void setClientMailId(String clientMailId) {
        this.clientMailId = clientMailId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
    	this.endDate = endDate;
    }

    public List<EmployeeDTO> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeDTO> employee) {
        this.employee = employee;
    }
}