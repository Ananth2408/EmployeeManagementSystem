package com.ideas2it.employee.util;

import com.ideas2it.employee.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Multiple times implemented values were under the same place,
 * Get the values from the user and save them.
 */
public class EmployeeManagementUtil {

    public Date dateOfJoining(String date) {
        Date dateOfJoining = null;
        boolean isDate = true;

        do{
            try {
                dateOfJoining = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                isDate = false;
            } catch (ParseException exception) {
                System.out.println("please enter valid date");
            }
        } while (isDate);
        return dateOfJoining;
    }
}