package com.ideas2it.employee.model;

/**
 * Get Employeeaddress from the user.
 * Uses getter setter to get input from user.
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
public class Address {
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String type;

    public Address() {}

    public Address(String doorNumber, String street, String city, String state,
                   int pinCode, String type) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.type = type;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        StringBuilder stringBuilderAddress = new StringBuilder();
        stringBuilderAddress.append("\nDoorNumber: ").append(getDoorNumber())
                            .append("\nStreet: ").append(getStreet())
                            .append("\nCity: ").append(getCity())
                            .append("\nState: ").append(getState())
                            .append("\nPincode: ").append(getPinCode())
                            .append("\nAddressType: ").append(getType());
        return stringBuilderAddress.toString();
    }
}