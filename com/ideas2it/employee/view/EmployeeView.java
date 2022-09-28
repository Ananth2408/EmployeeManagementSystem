package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.util.EmployeeManagementUtil;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Get the details of the employee from the user
 * and save the details.
 * Perform create, read, update and exit operations.
 * @version 4.0 28-09-2022.
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
        EmployeeDTO employeeDto = new EmployeeDTO();
        boolean isValid = true;

        try {
            System.out.println(EmployeeManagementConstant.FIRST_NAME);
            employeeDto.setFirstName(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.LAST_NAME);
            employeeDto.setLastName(scanner.nextLine());

            do{
                System.out.println(EmployeeManagementConstant.DATE_OF_BIRTH);
                String dateOfBirth = scanner.nextLine();
                if(util.dateOfBirth(dateOfBirth) != null) {
                    isValid = false;
                    employeeDto.setDateOfBirth(util.dateOfBirth(dateOfBirth));
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
                }
            } while (isValid);

            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            employeeDto.setPhoneNumber(Long.parseLong(scanner.nextLine()));

            do{
                System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
                String dateOfJoining = scanner.nextLine();
                if(util.dateOfJoining(dateOfJoining) != null) {
                    isValid = false;
                    employeeDto.setDateOfJoining(util.dateOfJoining(dateOfJoining));
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
                }
            } while (isValid);

            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            employeeDto.setEmail(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.GENDER);
            employeeDto.setGender(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            String doorNumber = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            String street = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            String city = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STATE);
            String state = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.TYPE);
            String type = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.PINCODE);
            int pinCode = Integer.parseInt(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.SALARY);
            employeeDto.setSalary(Float.parseFloat(scanner.nextLine()));
            employeeDto.setAddress(new AddressDTO(doorNumber, 
                                                  street, city, state, pinCode,type));
        } catch (InputMismatchException e) {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
 
        if (employeeController.addEmployee(employeeDto)) {
            System.out.println("Employee Details Added");
        } else {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
    }

    /**
     * Print all the saved employee details.
     */
    public void displayEmployee() {
        List<EmployeeDTO> employees = employeeController.displayEmployee();

        if (!employees.isEmpty()) {
            Iterator<EmployeeDTO> iterator = employees.iterator();

            while (iterator.hasNext()) {
                EmployeeDTO employee = iterator.next();
                System.out.println(employee.toString());
            }
        } else {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_NOT_FOUND);
        }   
    }
        
    /**
     * Update the employee details by the name get from the user.
     * If it's not updated shows error.
     */
    public void updateEmployee() {
        EmployeeDTO employeeDto = new EmployeeDTO();
        boolean isValid = true;
        System.out.println(EmployeeManagementConstant.EMPLOYEE_ID);
        int employeeId = Integer.valueOf(scanner.nextLine());

        try {
            System.out.println(EmployeeManagementConstant.FIRST_NAME);
            employeeDto.setFirstName(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.LAST_NAME);
            employeeDto.setLastName(scanner.nextLine());

            do{
                System.out.println(EmployeeManagementConstant.DATE_OF_BIRTH);
                String dateOfBirth = scanner.nextLine();
                if(util.dateOfBirth(dateOfBirth) != null) {
                    isValid = false;
                    employeeDto.setDateOfBirth(util.dateOfBirth(dateOfBirth));
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
                }
            } while (isValid);

            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            employeeDto.setPhoneNumber(Long.parseLong(scanner.nextLine()));

            do{
                System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
                String dateOfJoining = scanner.nextLine();
                if(util.dateOfJoining(dateOfJoining) != null) {
                    isValid = false;
                    employeeDto.setDateOfJoining(util.dateOfJoining(dateOfJoining));
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
                }
            } while (isValid);

            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            employeeDto.setEmail(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.GENDER);
            employeeDto.setGender(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            String doorNumber = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            String street = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            String city = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.STATE);
            String state = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.TYPE);
            String type = scanner.nextLine();
            System.out.println(EmployeeManagementConstant.PINCODE);
            int pinCode = Integer.parseInt(scanner.nextLine());
            System.out.println(EmployeeManagementConstant.SALARY);
            employeeDto.setSalary(Float.parseFloat(scanner.nextLine()));
            scanner.nextLine();
            employeeDto.setAddress(new AddressDTO(doorNumber, 
                                                  street, city, state, pinCode,type));
        } catch (InputMismatchException e) {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
 
        if (employeeController.updateEmployee(employeeDto, employeeId)) {
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

        System.out.println(EmployeeManagementConstant.FIRST_NAME);
        String name = scanner.next();
        EmployeeDTO employeeDto = employeeController.searchEmployee(name);
        if (employeeDto != null) {
            System.out.println(employeeDto);
        } else {
            System.out.println("Not found the Employee");
        }
    }

    /**
     * Get employee name from user and delete the employee details by that name.
     * If name not found it shows error.
     */
    public void deleteEmployee() {
        System.out.println(EmployeeManagementConstant.EMPLOYEE_DELETE);
        int employeeId = Integer.valueOf(scanner.nextLine());
  
        if (employeeController.deleteEmployee(employeeId)) {
            System.out.println("Employee Details Deleted");
        } else {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
        }
    }
}