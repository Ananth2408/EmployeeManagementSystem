package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.util.ValidateUtil;

import java.text.ParseException;
import java.time.LocalDate;
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
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class EmployeeView {
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    ValidateUtil util = new ValidateUtil();
    
    /**
     * Selecting operation to be done with employee details.
     * This have main menu to operation to be done.
     * If we couldn't select correct operation it shows error.
     */
    public void employeeOperation() { 
        int operations = 0;

        do {

            try {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_MENU);
                operations = Integer.valueOf(scanner.nextLine());

                switch (operations) {
                    case 1:
                        createEmployee();
                        break;

                    case 2:
                        displayEmployee();
                        break;

                    case 3:
                        updateEmployee();
                        break;

                    case 4:
                        searchEmployee();
                        break;

                    case 5:
                        deleteEmployee();
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
        } while (6 != operations);
    }
    
    /**
     * create and add employee details.
     * Get the employee details from the user.
     */
    public void createEmployee() {
        EmployeeDTO employeeDto = new EmployeeDTO();

        try {
            employeeDto.setFirstName(getFirstName());
            employeeDto.setLastName(getLastName());
            employeeDto.setRole(getRole());
            employeeDto.setDateOfBirth(getBirthDate());
            employeeDto.setPhoneNumber(getPhoneNumber());
            employeeDto.setDateOfJoining(getJoiningDate(employeeDto.getDateOfBirth()));
            employeeDto.setEmail(getEmail());
            employeeDto.setGender(getGender());
            employeeDto.setSalary(getSalary());
            employeeDto.setAddress(getAddress());

            if (employeeController.addEmployee(employeeDto)) {
                System.out.println("Employee Details Added");
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Print all the saved employee details.
     */
    public void displayEmployee() {

        try {
            List<EmployeeDTO> employeeDtos = employeeController.displayEmployee();

            if (!employeeDtos.isEmpty()) {
                for (EmployeeDTO employeeDto : employeeDtos) {
                    System.out.println(employeeDto.toString());
                }
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } catch (EMSException e) {
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
        int employeeId = getEmployeeID();

        try {

            if (employeeController.isEmployeeIDExists(employeeId)) {
                employeeDto.setFirstName(getFirstName());
                employeeDto.setLastName(getLastName());
                employeeDto.setRole(getRole());
                employeeDto.setDateOfBirth(getBirthDate());
                employeeDto.setPhoneNumber(getPhoneNumber());
                employeeDto.setDateOfJoining(getJoiningDate(employeeDto.getDateOfBirth()));
                employeeDto.setEmail(getEmail());
                employeeDto.setGender(getGender());
                employeeDto.setSalary(getSalary());
                employeeDto.setAddress(getAddress());  

            try {

                if (employeeController.updateEmployee(employeeDto, employeeId)) {
                    System.out.println("Employee Details Updated");
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
                }
            } catch (EMSException e) {
                System.out.println(e.getErrorCode() + " " + e.getMessage());
            } 
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_ID_NOT_EXISTS);
            }
        } catch (EMSException e) {
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
        String name = scanner.nextLine();

        try {
            List<EmployeeDTO> employeeDtos = employeeController.searchEmployee(name);

            if (!employeeDtos.isEmpty()) {
                for (EmployeeDTO employeeDto : employeeDtos) {
                    System.out.println(employeeDto.toString());
                }
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        } 
    }

    /**
     * Get employee name from user and delete the employee details by that name.
     * If name not found it shows error.
     */
    public void deleteEmployee() {
        System.out.println(EmployeeManagementConstant.EMPLOYEE_DELETE);
        int employeeId = getEmployeeID();

        try {

            if (employeeController.deleteEmployee(employeeId)) {
                System.out.println("Employee Details Deleted");
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_ID_NOT_EXISTS);
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Get the employee id of the employee from the user.
     * @return if the given id is valid it returns the id else ask again.
     */
    public int getEmployeeID() {
        boolean isValid = true;
        String employeeId;

        do{
            employeeId = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_EMPLOYEE_ID, employeeId)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Integer.valueOf(employeeId);
    }

    /**
     * Get the firstname of the employee from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getFirstName() {
        boolean isValid = true;
        String firstName;

        do{
            System.out.println(EmployeeManagementConstant.FIRST_NAME);
            firstName = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_FIRST_NAME, firstName)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return firstName;
    }

   /**
     * Get the lastname of the employee from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getLastName() {
        boolean isValid = true;
        String lastName;

        do{
            System.out.println(EmployeeManagementConstant.LAST_NAME);
            lastName = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_LAST_NAME, lastName)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return lastName;
    }

    /**
     * Get the role of the employee from the user.
     * @return if the given role is valid it returns the role else ask again.
     */
    public String getRole() {
        boolean isValid = true;
        String role;

        do{
            System.out.println(EmployeeManagementConstant.ROLE);
            role = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_ROLE, role)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return role;
    }

    /**
     * Get the phonenumber of the employee from the user.
     * @return if the given phonenumber is valid it returns the phonenumber else ask again.
     */
    public long getPhoneNumber() throws EMSException{
        boolean isValid = true;
        String phoneNumber;

        do{
            System.out.println(EmployeeManagementConstant.PHONENUMBER);
            phoneNumber = scanner.nextLine();
            
            if(employeeController.isValidData(EmployeeManagementConstant.VALID_PHONE_NUMBER, phoneNumber)) {

                if(!(employeeController.isValidPhoneNumber(phoneNumber))) {
                    isValid = false;
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_DUPLICATE);
                }
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return Long.parseLong(phoneNumber);
    }

    /**
     * Get the email id of the employee from the user.
     * @return if the given email id is valid it returns the email id else ask again.
     */
    public String getEmail() throws EMSException{
        boolean isValid = true;
        String email;

        do{
            System.out.println(EmployeeManagementConstant.EMAIL_ID);
            email = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_EMAIL, email)) {

                if(!(employeeController.isValidEmail(email))) {
                    isValid = false;
                } else {
                    System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_DUPLICATE);
                }
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return email;
    }

    /**
     * Get the birth date of the employee from the user.
     * @return if the given birth date is valid it returns the birth date else ask again.
     */
    public LocalDate getBirthDate() {
        boolean isValid = true;
        String dateOfBirth;
        LocalDate localDate = null;

        do{
            System.out.println(EmployeeManagementConstant.DATE_OF_BIRTH);
            dateOfBirth = scanner.nextLine();

            if(employeeController.isValidBirthDate(dateOfBirth)) {
                localDate = LocalDate.parse(dateOfBirth);
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return localDate;
    }

    /**
     * Get the birth date and joining date of the employee from the user.
     * @return if the given birth and joining date is valid 
     * it returns the birth and joining date else ask again.
     */
    public LocalDate getJoiningDate(LocalDate dateOfBirth) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        boolean isValid = true;
        String dateOfJoining;
        LocalDate localDate = null;

        do{
            System.out.println(EmployeeManagementConstant.DATE_OF_JOINING);
            dateOfJoining = scanner.nextLine();

            if(employeeController.isValidJoiningDate(dateOfBirth ,dateOfJoining)) {
                localDate = LocalDate.parse(dateOfJoining); 
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);
            }
        } while (isValid);
        return localDate;
    }

    /**
     * Get the salary of the employee from the user.
     * @return if the given salary is valid it returns the salary else ask again.
     */
    public float getSalary() {
        boolean isValid = true;
        String salary;

        do{
            System.out.println(EmployeeManagementConstant.SALARY);
            salary = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_SALARY, salary)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Float.parseFloat(salary);
    }

    /**
     * Get the gender of the employee from the user.
     * @return if the given gender is valid it returns the gender else ask again.
     */
    public String getGender() {
        boolean isValid = true;
        String gender;

        do{
            System.out.println(EmployeeManagementConstant.GENDER);
            gender = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_GENDER, gender)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return gender;
    }

    /**
     * Get the door number of the employee from the user.
     * @return if the given door number is valid it returns the door number else ask again.
     */
    public String getDoorNumber() {
        boolean isValid = true;
        String doorNumber;

        do{
            System.out.println(EmployeeManagementConstant.DOOR_NUMBER);
            doorNumber = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_DOOR_NUMBER, doorNumber)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return doorNumber;
    }

    /**
     * Get the street of the employee from the user.
     * @return if the given street is valid it returns the street else ask again.
     */
    public String getStreet() {
        boolean isValid = true;
        String street;

        do{
            System.out.println(EmployeeManagementConstant.STREET_NAME);
            street = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_STREET, street)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return street;
    }

    /**
     * Get the city of the employee from the user.
     * @return if the given city is valid it returns the city else ask again.
     */
    public String getCity() {
        boolean isValid = true;
        String city;

        do{
            System.out.println(EmployeeManagementConstant.CITY_NAME);
            city = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_CITY, city)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return city;
    }

    /**
     * Get the state of the employee from the user.
     * @return if the given state is valid it returns the state else ask again.
     */
    public String getState() {
        boolean isValid = true;
        String state;

        do{
            System.out.println(EmployeeManagementConstant.STATE);
            state = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_STATE, state)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return state;
    }

    /**
     * Get the pincode of the employee from the user.
     * @return if the given pincode is valid it returns the pincode else ask again.
     */
    public int getPincode() {
        boolean isValid = true;
        String pincode;

        do{
            System.out.println(EmployeeManagementConstant.PINCODE);
            pincode = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_PINCODE, pincode)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return Integer.parseInt(pincode);
    }

    /**
     * Get the address type of the employee from the user.
     * @return if the given address type is valid it returns the address type else ask again.
     */
    public String getType() {
        boolean isValid = true;
        String type;

        do{
            System.out.println(EmployeeManagementConstant.TYPE);
            type = scanner.nextLine();

            if(employeeController.isValidData(EmployeeManagementConstant.VALID_TYPE, type)) {
                isValid = false;
            } else {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_MANAGEMENT_ERROR);   
            }
        } while (isValid);
        return type;
    }

    public List<AddressDTO> getAddress() {
        List<AddressDTO> addressDtos= new ArrayList();
        
        do {  
            AddressDTO addressDto = new AddressDTO();
            addressDto.setType(getType());
            addressDto.setDoorNumber(getDoorNumber());
            addressDto.setStreet(getStreet());
            addressDto.setCity(getCity());
            addressDto.setState(getState());
            addressDto.setPinCode(getPincode());
            addressDtos.add(addressDto);
        } while (getResponse());
        return addressDtos;
    }

     public boolean getResponse() {
         boolean isCheck = false;
         boolean isValid;
         System.out.println("You want to add address again press y for yes/ press n for no");
             do {
                isValid = true;
  
                switch (scanner.nextLine()) {
                    case "y":
                        isCheck = true;
                        break;

                    case "n":
                        isCheck = false;
                        break;

                    default:
                        isValid = false;
                        System.out.println("error"); 
                 }
             } while (!isValid);
         return isCheck;
    } 
}