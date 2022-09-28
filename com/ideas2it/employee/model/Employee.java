package com.ideas2it.employee.model;

import com.ideas2it.employee.model.Address;

import java.time.LocalDate;

/**
 * Get Employeedetails.
 * Uses getter setter methods get input from user.
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private long phoneNumber;
    private LocalDate dateOfJoining;
    private String email;
    private float salary;
    private String gender;
    private Address address;

    public Employee() {}

    public Employee(int id, String firstName, String lastName,LocalDate dateOfBirth, long phonenumber,
                    LocalDate dateOfJoining, String email, float salary, String gender, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.email = email;
        this.salary = salary;
        this.gender = gender;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder stringBuilderDetails = new StringBuilder();
        stringBuilderDetails.append("\nID :").append(getId())
                            .append("\nName:").append(getFirstName()).append(getLastName())
                            .append("\nDateofbirth:").append(getDateOfBirth())
                            .append("\nPhoneNumber:").append(getPhoneNumber())
                            .append("\nDate of joining:").append(getDateOfJoining())
                            .append("\nEmail:").append(getEmail())
                            .append("\nSalary:").append(getSalary())
                            .append("\nGender:").append(getGender())
                            .append(getAddress());
        return stringBuilderDetails.toString();
    }
}