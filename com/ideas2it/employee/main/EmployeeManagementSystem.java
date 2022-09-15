package com.ideas2it.employee.main;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.view.EmployeeView;

/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version 2.1 15-09-2022.
 * @author  Ananth Karnan.
 */
public class EmployeeManagementSystem {

    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        employeeView.employeeOperation();
    }
}