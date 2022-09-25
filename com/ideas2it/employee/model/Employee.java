package com.ideas2it.employee.model;

import com.ideas2it.employee.model.Address;

import java.util.Date;

/**
 * Get Employeedetails.
 * Uses getter setter methods get input from user.
 * @version 4.0 16-09-2022.
 * @author  Ananth K.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private Date dateOfJoining;
    private String email;
    private double salary;
    private String gender;
    private Address address;

    public Employee() {}

    public Employee(String firstName, String lastName,Date dateOfBirth, String phonenumber,
                    Date dateOfJoining, String email, double salary, String gender, Address address) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
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
        stringBuilderDetails.append("\nName:").append(getFirstName()).append(getLastName())
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