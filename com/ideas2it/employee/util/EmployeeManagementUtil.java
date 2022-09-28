package com.ideas2it.employee.util;

import com.ideas2it.employee.dto.EmployeeDTO;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

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
}