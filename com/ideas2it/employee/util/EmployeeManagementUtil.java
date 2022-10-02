package com.ideas2it.employee.util;

import com.ideas2it.employee.dto.EmployeeDTO;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.regex.*;
import java.util.regex.Pattern;

/**
 * Multiple times implemented values were placed here,
 * Get the values from the user and validate them.
 * @version  4.0 28-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementUtil {

    /**
     * Date of joining were validated and returned.
     * @param date from the user
     * @return Date of Birth
     */
    public LocalDate dateOfBirth(String dateOfBirth) {
        LocalDate birthDate = null;

        try {
            birthDate = LocalDate.parse(dateOfBirth);
        } catch (DateTimeParseException exception) {
            System.out.println("please enter valid date");
        }
        return birthDate;
    }

    /**
     * Date of joining were validated and returned.
     * @param date from the user
     * @return Date of joining
     */
    public LocalDate dateOfJoining(String dateOfJoining) {
        LocalDate joinDate = null;

        try {
            joinDate = LocalDate.parse(dateOfJoining);
        } catch (DateTimeParseException exception) {
            System.out.println("please enter valid date");
        }
        return joinDate;
    }

    public boolean isValidFirstName(String pattern, String firstName) {
        return Pattern.matches(pattern, firstName);
    }

    public boolean isValidLastName(String pattern, String lastName) {
        return Pattern.matches(pattern, lastName);
    }

    public boolean isValidRole(String pattern, String role) {
        return Pattern.matches(pattern, role);
    }

    public boolean isValidPhoneNumber(String pattern, String phoneNumber) {
        return Pattern.matches(pattern, phoneNumber);
    }

    public boolean isValidGender(String pattern, String gender) {
        return Pattern.matches(pattern, gender);
    }

    public boolean isValidSalary(String pattern, String Salary) {
        return Pattern.matches(pattern, Salary);
    }

    public boolean isValidDoorNumber(String pattern, String doorNumber) {
        return Pattern.matches(pattern, doorNumber);
    }

    public boolean isValidStreet(String pattern, String street) {
        return Pattern.matches(pattern, street);
    }

    public boolean isValidCity(String pattern, String city) {
        return Pattern.matches(pattern, city);
    }

    public boolean isValidState(String pattern, String state) {
        return Pattern.matches(pattern, state);
    }

    public boolean isValidPincode(String pattern, String pincode) {
        return Pattern.matches(pattern, pincode);
    }

    public boolean isValidType(String pattern, String type) {
        return Pattern.matches(pattern, type);
    }
}