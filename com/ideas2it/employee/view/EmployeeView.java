package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.impl.EmployeeManagementServiceImpl;
import com.ideas2it.employee.util.EmployeeManagementUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Get the details of the employee from the user
 * and save the details.
 * Perform create, read, update and exit operations.
 * @version 2.0 13-09-2022.
 * @author  Ananth K.
 */
public class EmployeeView {
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    EmployeeManagementUtil util = new EmployeeManagementUtil();

    /**
     * Selecting operation to be done with employee details.
     * This have main menu to operation to be done.
     * If we couldn't select correct operation it shows error.
     */
    public void employeeOperation() { 
        int loopValue = 1;
        EmployeeView  view = new EmployeeView();

        while (loopValue != 0) {
            try {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_MENU);
                int operations = Integer.valueOf(scanner.nextLine());
                switch (operations) {
                    case 1:
                        view.createEmployee();
                        break;

                    case 2:
                        view.displayEmployee();
                        break;

                    case 3:
                        view.updateEmployee();
                        break;

                    case 4:
                        view.searchEmployee();
                        break;

                    case 5:
                        view.deleteEmployee();
                        break;

                    case 6:
                        System.out.println("Thank you");
                        System.exit(0);

                    default:
                        System.out.println(EmployeeManagementConstant.EMPLOYEE_OPERATION_ERROR);
                }
            } catch (NumberFormatException numberFormatError) {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_OPERATION_ERROR);
            }
        }
    }
    
    /**
     * create and add employee details.
     * Get the employee details from the user.
     */
    public void createEmployee() {
        Employee employee = new Employee();

        try {
            System.out.println(EmployeeManagementConstant.NAME);
            employee.setName(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.ID);
            employee.setId(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            employee.setPhoneNumber(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
            String date = scanner.nextLine();
            employee.setDateOfJoining(util.dateOfJoining(date));

            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            employee.setEmail(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.BLOOD_GROUP);
            employee.setBloodGroup(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            String doorNumber = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            String street = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            String city = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STATE);
            String state = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.PINCODE);
            int pinCode = Integer.parseInt(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.SALARY);
            employee.setSalary(Double.parseDouble(scanner.nextLine()));
            employee.setAddress(new Address(doorNumber, 
                                                street, city, state, pinCode));
        } catch (InputMismatchException e) {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
 
        if (employeeController.addEmployee(employee)) {
            System.out.println("Employee Details Added");
        } else {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
    }

    /**
     * Print all the saved employee details.
     */
    public void displayEmployee() {
        List<Employee> employees = employeeController.displayEmployee();
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println(employee.toString());
        }
    }
        
    /**
     * Update the employee details by the name get from the user.
     * If it's not updated shows error.
     */
    public void updateEmployee() {
        Employee employee = new Employee();

        try {
            System.out.println(EmployeeManagementConstant.NAME);
            employee.setName(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.ID);
            employee.setId(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            employee.setPhoneNumber(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
            employee.setDateOfJoining(util.dateOfJoining(scanner.nextLine()));
            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            employee.setEmail(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.BLOOD_GROUP);
            employee.setBloodGroup(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            String doorNumber = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            String street = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            String city = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STATE);
            String state = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.PINCODE);
            int pinCode = Integer.parseInt(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.SALARY);
            employee.setSalary(Double.parseDouble(scanner.nextLine()));
            employee.setAddress(new Address(doorNumber, 
                                                street, city, state, pinCode));
        } catch (InputMismatchException e) {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
 
        if (employeeController.updateEmployee(employee)) {
            System.out.println("Employee Details Updated");
        } else {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
    }

    /**
     * Search employee details by the employee name given by the user,
     * If name found it prints employee details
     * else it shows error.
     */
    public void searchEmployee() {

        System.out.println(EmployeeManagementConstant.NAME);
        String employeeName = scanner.next();
        Employee selectEmployee = employeeController.searchEmployee(employeeName);
        if (selectEmployee != null) {
            System.out.println(selectEmployee);
        } else {
            System.out.println("Not found the Employee");
        }
    }

    /**
     * Get employee name from user and delete the employee details by that name.
     * If name not found it shows error.
     */
    public void deleteEmployee() {
        System.out.println(EmployeeManagementConstant.NAME);
        String employeeName = scanner.nextLine();
  
        if (employeeController.deleteEmployee(employeeName)) {
            System.out.println("Employee Details Deleted");
        } else {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
    }

    public void validateEmployee() {
         System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
    }
}