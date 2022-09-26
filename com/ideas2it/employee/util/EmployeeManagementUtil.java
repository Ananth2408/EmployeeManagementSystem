package com.ideas2it.employee.util;

import com.ideas2it.employee.model.EmployeeDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Multiple times implemented values were placed here,
 * Get the values from the user and validate them.
 * @version  3.0 16-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementUtil {

    /**
     * Date of joining were validated and returned.
     * @param date from the user
     * @return Date of Birth
     */
    public Date dateOfBirth(String dateOfBirth) {
        Date dateOfBirth = null;

        try {
            dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
        } catch (ParseException exception) {
            System.out.println("please enter valid date");
        }
        return dateOfBirth;
    }

    /**
     * Date of joining were validated and returned.
     * @param date from the user
     * @return Date of joining
     */
    public Date dateOfJoining(String dateOfJoining) {
        Date dateOfJoining = null;

        try {
            dateOfJoining = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfJoining);
        } catch (ParseException exception) {
            System.out.println("please enter valid date");
        }
        return dateOfJoining;
    }
}