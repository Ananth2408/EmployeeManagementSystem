package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.util.EmployeeManagementUtil;

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

        employeeDto.setFirstName(getFirstName());
        employeeDto.setLastName(getLastName());
        employeeDto.setRole(getRole());
        employeeDto.setDateOfBirth(getBirthDate());
        employeeDto.setPhoneNumber(getPhoneNumber());
        employeeDto.setDateOfJoining(getJoiningDate());
        employeeDto.setEmail(getEmail());
        employeeDto.setGender(getGender());
        employeeDto.setSalary(getSalary());
        employeeDto.setAddress(new AddressDTO(getDoorNumber(), getStreet(), 
                                              getCity(), getState(), getPincode(),
                                              getType()));
 
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

    public String getFirstName() {
        boolean isValid = true;
        String firstName;

        do{
            System.out.println(EmployeeManagementConstant.FIRST_NAME);
            firstName = scanner.nextLine();

            if(util.isValidFirstName(EmployeeManagementConstant.VALID_FIRST_NAME, firstName)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return firstName;
    }

    public String getLastName() {
        boolean isValid = true;
        String lastName;

        do{
            System.out.println(EmployeeManagementConstant.LAST_NAME);
            lastName = scanner.nextLine();

            if(util.isValidLastName(EmployeeManagementConstant.VALID_LAST_NAME, lastName)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return lastName;
    }

    public String getRole() {
        boolean isValid = true;
        String role;

        do{
            System.out.println(EmployeeManagementConstant.ROLE);
            role = scanner.nextLine();

            if(util.isValidRole(EmployeeManagementConstant.VALID_ROLE, role)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return role;
    }

    public long getPhoneNumber() {
        boolean isValid = true;
        String phoneNumber;

        do{
            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            phoneNumber = scanner.nextLine();

            if(util.isValidPhoneNumber(EmployeeManagementConstant.VALID_PHONE_NUMBER, phoneNumber)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Long.parseLong(phoneNumber);
    }

    public String getEmail() {
        boolean isValid = true;
        String email;

        do{
            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            email = scanner.nextLine();

            if(util.isValidFirstName(EmployeeManagementConstant.VALID_EMAIL, email)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return email;
    }

    public LocalDate getBirthDate() {
        boolean isValid = true;
        String dateOfBirth;

        do{
            System.out.println(EmployeeManagementConstant.DATE_OF_BIRTH);
            dateOfBirth = scanner.nextLine();

            if(util.dateOfBirth(dateOfBirth) != null) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return util.dateOfBirth(dateOfBirth);
    }

    public LocalDate getJoiningDate() {
        boolean isValid = true;
        String dateOfJoining;

        do{
            System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
            dateOfJoining = scanner.nextLine();

            if(util.dateOfJoining(dateOfJoining) != null) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return util.dateOfJoining(dateOfJoining);
    }

    public float getSalary() {
        boolean isValid = true;
        String salary;

        do{
            System.out.println(EmployeeManagementConstant.SALARY);
            salary = scanner.nextLine();

            if(util.isValidSalary(EmployeeManagementConstant.VALID_SALARY, salary)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Float.parseFloat(salary);
    }

    public String getGender() {
        boolean isValid = true;
        String gender;

        do{
            System.out.println(EmployeeManagementConstant.GENDER);
            gender = scanner.nextLine();

            if(util.isValidGender(EmployeeManagementConstant.VALID_GENDER, gender)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return gender;
    }

    public String getDoorNumber() {
        boolean isValid = true;
        String doorNumber;

        do{
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            doorNumber = scanner.nextLine();

            if(util.isValidDoorNumber(EmployeeManagementConstant.VALID_DOOR_NUMBER, doorNumber)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return doorNumber;
    }

    public String getStreet() {
        boolean isValid = true;
        String street;

        do{
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            street = scanner.nextLine();

            if(util.isValidStreet(EmployeeManagementConstant.VALID_STREET, street)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return street;
    }

    public String getCity() {
        boolean isValid = true;
        String city;

        do{
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            city = scanner.nextLine();

            if(util.isValidCity(EmployeeManagementConstant.VALID_CITY, city)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return city;
    }

    public String getState() {
        boolean isValid = true;
        String state;

        do{
            System.out.println(EmployeeManagementConstant.STATE);
            state = scanner.nextLine();

            if(util.isValidState(EmployeeManagementConstant.VALID_STATE, state)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return state;
    }

    public int getPincode() {
        boolean isValid = true;
        String pincode;

        do{
            System.out.println(EmployeeManagementConstant.PINCODE);
            pincode = scanner.nextLine();

            if(util.isValidPincode(EmployeeManagementConstant.VALID_PINCODE, pincode)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Integer.parseInt(pincode);
    }

    public String getType() {
        boolean isValid = true;
        String type;

        do{
            System.out.println(EmployeeManagementConstant.TYPE);
            type = scanner.nextLine();

            if(util.isValidType(EmployeeManagementConstant.VALID_TYPE, type)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return type;
    }
}