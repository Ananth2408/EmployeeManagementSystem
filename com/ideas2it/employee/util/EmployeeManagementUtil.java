package com.ideas2it.employee.util;

import com.ideas2it.employee.dto.EmployeeDTO;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Multiple times implemented values were placed here,
 * Get the values from the user and validate them.
 * @version  4.0 28-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementUtil {

    /**
     * Used to validate the given input is valid or not.
     * @param date from the user.
     * @return if it is valid it returns localdate else ask again.
     */
    public LocalDate dates(String date) {
        LocalDate localDate = null;

        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            System.out.println("please enter valid date");
        }
        return localDate;
    }

    /**
     * Used to validate the given input is valid or not.
     * @param pattern is regex pattern.
     * @param field is values, input from the users.
     * @return if it is valid it returns true else rturns false.
     */
    public boolean isValidData(String pattern, String field) {
        return Pattern.matches(pattern, field);
    }
}