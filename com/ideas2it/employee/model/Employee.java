package com.ideas2it.employee.model;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.view.EmployeeView;

import java.util.Date;

/**
 * Get Employeedetails.
 * Class uses getter setter methods get input from user.
 * @version 2.0 02-09-2022.
 * @author  Ananth K.
 */
public class Employee {
    private String name;
    private String id;
    private String phoneNumber;
    private Date dateOfJoining;
    private String email;
    private double salary;
    private String bloodGroup;
    private Address address;

    public Employee() {}

    public Employee(String name, String id, String phonenumber, Date dateOfJoining,
                   String email, double salary, String bloodGroup, Address address) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.email = email;
        this.salary = salary;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder stringBuilderDetails = new StringBuilder();
        stringBuilderDetails.append("\nName:").append(getName())
                            .append("\nId:").append(getId())
                            .append("\nPhoneNumber:").append(getPhoneNumber())
                            .append("\nDate of joining:").append(getDateOfJoining())
                            .append("\nEmail:").append(getEmail())
                            .append("\nSalary:").append(getSalary())
                            .append("\nBlood Group:").append(getBloodGroup())
                            .append(getAddress());
        return stringBuilderDetails.toString();
    }
}