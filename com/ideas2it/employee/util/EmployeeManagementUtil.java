package com.ideas2it.employee.util;

import com.ideas2it.employee.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Multiple times implemented values were under the same place,
 * Get the values from the user and save them.
 * @version  2.1 15-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementUtil {

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