package com.ideas2it.employee.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Get Employeedetails.
 * Uses getter setter get input from user.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private long phoneNumber;
    private LocalDate dateOfJoining;
    private String email;
    private float salary;
    private String gender;
    private List<AddressDTO> address;
    private String role;
    private List<ProjectDTO> project;

    public EmployeeDTO() {}

    public EmployeeDTO(int id, String firstName, String lastName,String role,
                       LocalDate dateOfBirth, long phoneNumber, 
                       LocalDate dateOfJoining,String email, float salary, 
                       String gender, List<AddressDTO> address, List<ProjectDTO> project) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.email = email;
        this.salary = salary;
        this.gender = gender;
        this.address = address;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AddressDTO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }

    public List<ProjectDTO> getProject() {
        return project;
    }

    public void setProject(List<ProjectDTO> project) {
        this.project = project;
    }

    /*public String toString() {
        StringBuilder stringBuilderDetails = new StringBuilder();
        stringBuilderDetails.append("\n\n\nEmployeeDetails")
                            .append("\nEmployeeId:").append(getId())
                            .append("\nName:").append(getFirstName())
                            .append(" ").append(getLastName())
                            .append("\nRole:").append(getRole())
                            .append("\nDateofbirth:").append(getDateOfBirth())
                            .append("\nPhoneNumber:").append("+91")
                            .append(getPhoneNumber())
                            .append("\nDate of joining:").append(getDateOfJoining())
                            .append("\nEmail:").append(getEmail())
                            .append("\nSalary:").append(getSalary())
                            .append("\nGender:").append(getGender())
                            .append(getAddress()).append(getProject());
        return stringBuilderDetails.toString();
    }*/
}
