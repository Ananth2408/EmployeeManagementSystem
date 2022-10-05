package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EmployeeManagementSystemException;

import java.time.LocalDate;
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
        AddressDTO addressDto = new AddressDTO();

        employeeDto.setFirstName(getFirstName());
        employeeDto.setLastName(getLastName());
        employeeDto.setRole(getRole());
        employeeDto.setDateOfBirth(getBirthDate());
        employeeDto.setPhoneNumber(getPhoneNumber());
        employeeDto.setDateOfJoining(getJoiningDate());
        employeeDto.setEmail(getEmail());
        employeeDto.setGender(getGender());
        employeeDto.setSalary(getSalary());
        addressDto.setDoorNumber(getDoorNumber());
        addressDto.setStreet(getStreet());
        addressDto.setCity(getCity());
        addressDto.setState(getState());
        addressDto.setType(getType());
        addressDto.setPinCode(getPincode());
        employeeDto.setAddress(addressDto);

        try {
        if (employeeController.addEmployee(employeeDto)) {
            System.out.println("Employee Details Added");
        }
        } catch (EmployeeManagementSystemException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Print all the saved employee details.
     */
    public void displayEmployee() {

        try {
        List<EmployeeDTO> employees = employeeController.displayEmployee();

        if (!employees.isEmpty()) {
            Iterator<EmployeeDTO> iterator = employees.iterator();

            while (iterator.hasNext()) {
                EmployeeDTO employee = iterator.next();
                System.out.println(employee.toString());
            }
        }
        } catch (EmployeeManagementSystemException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }   
    }
        
    /**
     * Update the employee details by the name get from the user.
     * If it's not updated shows error.
     */
    public void updateEmployee() {
        EmployeeDTO employeeDto = new EmployeeDTO();
        AddressDTO addressDto = new AddressDTO();
        boolean isValid = true;
        System.out.println(EmployeeManagementConstant.EMPLOYEE_ID);
        int employeeId = Integer.valueOf(scanner.nextLine());

        employeeDto.setFirstName(getFirstName());
        employeeDto.setLastName(getLastName());
        employeeDto.setRole(getRole());
        employeeDto.setDateOfBirth(getBirthDate());
        employeeDto.setPhoneNumber(getPhoneNumber());
        employeeDto.setDateOfJoining(getJoiningDate());
        employeeDto.setEmail(getEmail());
        employeeDto.setGender(getGender());
        addressDto.setDoorNumber(getDoorNumber());
        addressDto.setStreet(getStreet());
        addressDto.setCity(getCity());
        addressDto.setState(getState());
        addressDto.setType(getType());
        addressDto.setPinCode(getPincode());
        employeeDto.setSalary(getSalary());
        employeeDto.setAddress(addressDto);     

        try {
        if (employeeController.updateEmployee(employeeDto, employeeId)) {
            System.out.println("Employee Details Updated");
        }
        } catch (EmployeeManagementSystemException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
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

        try{
        EmployeeDTO employeeDto = employeeController.searchEmployee(name);

        if (employeeDto != null) {
            System.out.println(employeeDto);
        } else {
            System.out.println("error");
        }
        } catch (EmployeeManagementSystemException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Get employee name from user and delete the employee details by that name.
     * If name not found it shows error.
     */
    public void deleteEmployee() {
        System.out.println(EmployeeManagementConstant.EMPLOYEE_DELETE);
        int employeeId = Integer.valueOf(scanner.nextLine());

        try {
        if (employeeController.deleteEmployee(employeeId)) {
            System.out.println("Employee Details Deleted");
        }
        } catch (EmployeeManagementSystemException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Get the firstname of the employee from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getFirstName() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.FIRST_NAME);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_FIRST_NAME, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

   /**
     * Get the lastname of the employee from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getLastName() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.LAST_NAME);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_LAST_NAME, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the role of the employee from the user.
     * @return if the given role is valid it returns the role else ask again.
     */
    public String getRole() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.ROLE);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_ROLE, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the phonenumber of the employee from the user.
     * @return if the given phonenumber is valid it returns the phonenumber else ask again.
     */
    public long getPhoneNumber() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_PHONE_NUMBER, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Long.parseLong(field);
    }

    /**
     * Get the email id of the employee from the user.
     * @return if the given email id is valid it returns the email id else ask again.
     */
    public String getEmail() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_EMAIL, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the birth date of the employee from the user.
     * @return if the given birth date is valid it returns the birth date else ask again.
     */
    public LocalDate getBirthDate() {
        boolean isValid = true;
        String date;

        do{
            System.out.println(EmployeeManagementConstant.DATE_OF_BIRTH);
            date = scanner.nextLine();

            if(employeeController.date(date) != null) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return employeeController.date(date);
    }

    /**
     * Get the birth date and joining date of the employee from the user.
     * @return if the given birth and joining date is valid 
     * it returns the birth and joining date else ask again.
     */
    public LocalDate getJoiningDate() {
        boolean isValid = true;
        String date;

        do{
            System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
            date = scanner.nextLine();

            if(employeeController.date(date) != null) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return employeeController.date(date);
    }

    /**
     * Get the salary of the employee from the user.
     * @return if the given salary is valid it returns the salary else ask again.
     */
    public float getSalary() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.SALARY);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_SALARY, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Float.parseFloat(field);
    }

    /**
     * Get the gender of the employee from the user.
     * @return if the given gender is valid it returns the gender else ask again.
     */
    public String getGender() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.GENDER);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_GENDER, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the door number of the employee from the user.
     * @return if the given door number is valid it returns the door number else ask again.
     */
    public String getDoorNumber() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_DOOR_NUMBER, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the street of the employee from the user.
     * @return if the given street is valid it returns the street else ask again.
     */
    public String getStreet() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_STREET, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the city of the employee from the user.
     * @return if the given city is valid it returns the city else ask again.
     */
    public String getCity() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_CITY, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the state of the employee from the user.
     * @return if the given state is valid it returns the state else ask again.
     */
    public String getState() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.STATE);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_STATE, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }

    /**
     * Get the pincode of the employee from the user.
     * @return if the given pincode is valid it returns the pincode else ask again.
     */
    public int getPincode() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.PINCODE);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_PINCODE, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Integer.parseInt(field);
    }

    /**
     * Get the address type of the employee from the user.
     * @return if the given address type is valid it returns the address type else ask again.
     */
    public String getType() {
        boolean isValid = true;
        String field;

        do{
            System.out.println(EmployeeManagementConstant.TYPE);
            field = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_TYPE, field)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return field;
    }
}