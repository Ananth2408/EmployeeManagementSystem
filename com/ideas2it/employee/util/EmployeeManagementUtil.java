package com.ideas2it.employee.util;

import com.ideas2it.employee.model.Employee;

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
     * @return Date of joining
     */
    public Date dateOfJoining(String date) {
        Date dateOfJoining = null;

        try {
            dateOfJoining = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException exception) {
            System.out.println("please enter valid date");
        }
        return dateOfJoining;
    }
}