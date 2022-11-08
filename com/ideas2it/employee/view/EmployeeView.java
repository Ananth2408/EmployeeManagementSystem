package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.util.ValidateUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    Logger logger = LogManager.getLogger(EmployeeView.class);
    
    /**
     * Selecting operation to be done with employee details.
     * This have main menu to operation to be done.
     * If we couldn't select correct operation it shows error.
     */
    public void employeeOperation() { 
        int operations = 0;

        do {

            try {
                System.out.println(Constant.EMPLOYEE_MANAGEMENT_MENU);
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
                        break;

                    default:
                        System.out.println(Constant.OPERATION_ERROR);
                }
            } catch (NumberFormatException numberFormatError) {
                logger.error(Constant.OPERATION_ERROR);
                System.out.println(Constant.OPERATION_ERROR);
            }
        } while (6 != operations);
    }
    
    /**
     * create and add employee details.
     * Get the employee details from the user.
     */
    public void createEmployee() {
        EmployeeDTO employeeDto = new EmployeeDTO();
        boolean isResponse;
        List<ProjectDTO> projectDtos = new ArrayList();

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
            isResponse = getResponse("project");

            if (isResponse) {
                employeeDto.setProject(getProject(projectDtos));
            }
            int employeeId = employeeController.addEmployee(employeeDto);
            logger.info("Employee Details Created" + "ID=" + employeeId);
            System.out.println("Employee Details Added" + "ID=" + employeeId);
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Print all the saved employee details.
     */
    public void displayEmployee() {

        try {
            List<EmployeeDTO> employeeDtos = employeeController.getAllEmployee();

            if (!employeeDtos.isEmpty()) {

                for (EmployeeDTO employeeDto : employeeDtos) {
                    System.out.println(employeeDto.toString());
                }
            } else {
                System.out.println(Constant.INVALID_DATA);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }   
    }
        
    /**
     * Update the employee details by the id get from the user.
     * If it's not updated shows error.
     */
    public void updateEmployee() {
        System.out.println(Constant.EMPLOYEE_ID);
        int employeeId = getEmployeeID();
        int operations = 0;
        EmployeeDTO employeeDto = null;
        boolean isUpdated = false;

        try {
            employeeDto = employeeController.employeeExists(employeeId);

            if (null != employeeDto ) {

                do {
                    try {
                        System.out.println(Constant.EMPLOYEE_UPDATE_MENU);
                        operations = Integer.valueOf(scanner.nextLine());

                        switch (operations) {
                            case 1:
                                employeeDto.setFirstName(getFirstName());
                                break;

                            case 2:
                                employeeDto.setLastName(getLastName());
                                break;

                            case 3:
                                employeeDto.setRole(getRole());
                                break;

                            case 4:
                                employeeDto.setDateOfBirth(getBirthDate());
                                break;

                            case 5:
                                employeeDto.setDateOfJoining(getJoiningDate(employeeDto.getDateOfBirth()));
                                break;

                            case 6:
                                employeeDto.setPhoneNumber(getPhoneNumber());
                                break;

                            case 7:
                                employeeDto.setEmail(getEmail());
                                break;

                            case 8:
                                employeeDto.setGender(getGender());
                                break;

                            case 9:
                                employeeDto.setSalary(getSalary());
                                break;

                            case 10:
                                employeeDto.setAddress(updateAddress(employeeDto));
                                break;

                            case 11:
                                employeeDto.setProject(getProject(employeeDto.getProject()));
                                break;

                            case 12:
                                isUpdated = true;
                                break;

                            default:
                                System.out.println(Constant.OPERATION_ERROR);
                        }
                    } catch (NumberFormatException numberFormatError) {
                        logger.error(Constant.OPERATION_ERROR);
                        System.out.println(Constant.OPERATION_ERROR);
                    }
                } while (!(isUpdated));

                if (employeeController.updateEmployee(employeeDto)) {
                    logger.info("Employee Details Updated" + employeeId);
                    System.out.println("Employee Details Updated" + employeeId);
                } else {
                    System.out.println(Constant.INVALID_DATA);
                }
            } else {
                logger.info(Constant.EMPLOYEE_ID_NOT_EXISTS + employeeId);
                System.out.println(Constant.EMPLOYEE_ID_NOT_EXISTS + employeeId);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }  
    }

    /**
     * Used to get the type of the adrees from the address
     * from that we get the address data and upate them individulay
     * @param employeeDto employee details.
     */
    public List<AddressDTO> updateAddress(EmployeeDTO employeeDto) {
        List<AddressDTO> addressDtos = employeeDto.getAddress();
        AddressDTO addressDto = addressDtos.stream()
                                .filter(x -> x.getType().equals(getType()))
                                .findFirst().orElse(null);
        addressDtos.add(setAddress(addressDto));
        return addressDtos;
    }

    /**
     * Update the employee address details by the id get from the user.
     * If it's not updated shows error.
     * @param addressDto address details from employee details.
     */
    public AddressDTO setAddress(AddressDTO addressDto) {
        int operations = 0;
        boolean isUpdated = false;

        if (null != addressDto) {
  
            do {

                try {
                    System.out.println(Constant.EMPLOYEE_ADDRESSUPDATE_MENU);
                    operations = Integer.valueOf(scanner.nextLine());

                    switch (operations) {
                        case 1:
                            addressDto.setDoorNumber(getDoorNumber());
                            break;

                        case 2:
                            addressDto.setStreet(getStreet());
                            break;

                        case 3:
                            addressDto.setCity(getCity());
                            break;

                        case 4:
                            addressDto.setState(getState());
                            break;

                        case 5:
                            addressDto.setPinCode(getPincode());
                            break;

                        case 6:
                            isUpdated = true;
                            break;

                        default:
                            System.out.println(Constant.OPERATION_ERROR);
                        }
                    } catch (NumberFormatException numberFormatError) {
                        logger.error(Constant.OPERATION_ERROR);
                        System.out.println(Constant.OPERATION_ERROR);
                    }
            } while(!(isUpdated));
        } else {
            System.out.println("Enter valid address type");
        }
        return addressDto;
    }
        
    /**
     * Search employee details by the employee name given by the user,
     * If name found it prints employee details
     * else it shows error.
     */
    public void searchEmployee() {

        System.out.println(Constant.FIRST_NAME);
        String name = scanner.nextLine();

        try {
            List<EmployeeDTO> employeeDtos = employeeController.searchEmployee(name);

            if (!employeeDtos.isEmpty()) {
                for (EmployeeDTO employeeDto : employeeDtos) {
                    System.out.println(employeeDto.toString());
                }
            } else {
                logger.info(Constant.EMPLOYEE_NOT_FOUND + name);
                System.out.println(Constant.EMPLOYEE_NOT_FOUND + name);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        } 
    }

    /**
     * Get employee name from user and delete the employee details by that name.
     * If name not found it shows error.
     */
    public void deleteEmployee() {
        System.out.println(Constant.EMPLOYEE_DELETE);
        int employeeId = getEmployeeID();

        try {

            if (null != employeeController.employeeExists(employeeId)) {
                employeeController.deleteEmployee(employeeId);
                logger.info("Employee" + employeeId + "has been deleted");
                System.out.println("Employee" + employeeId + "has been deleted");
            } else {
                logger.info(Constant.EMPLOYEE_ID_NOT_EXISTS + employeeId);
                System.out.println(Constant.EMPLOYEE_ID_NOT_EXISTS + employeeId);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
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

            if(employeeController.isValidData(Constant.VALID_ID, employeeId)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.FIRST_NAME);
            firstName = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_FIRST_NAME, firstName)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.LAST_NAME);
            lastName = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_LAST_NAME, lastName)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.ROLE);
            role = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_ROLE, role)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.PHONENUMBER);
            phoneNumber = scanner.nextLine();
            
            if(employeeController.isValidData(Constant.VALID_PHONE_NUMBER, phoneNumber)) {

                if(!(employeeController.isValidPhoneNumber(phoneNumber))) {
                    isValid = false;
                } else {
                    System.out.println(Constant.DUPLICATE);
                }
            } else {
                System.out.println(Constant.INVALID_DATA);
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
            System.out.println(Constant.EMAIL_ID);
            email = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_EMAIL, email)) {

                if(!(employeeController.isValidEmail(email))) {
                    isValid = false;
                } else {
                    System.out.println(Constant.DUPLICATE);
                }
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            try {
                System.out.println(Constant.DATE_OF_BIRTH);
                dateOfBirth = scanner.nextLine();

                if(employeeController.isValidBirthDate(dateOfBirth)) {
                    localDate = LocalDate.parse(dateOfBirth);
                    isValid = false;
                } else {
                    System.out.println(Constant.INVALID_DATA);
                }
            } catch (EMSException e) {
                logger.error(e.getErrorCode() + " " + e.getMessage());
                System.out.println(e.getErrorCode() + " " + e.getMessage());
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
            try {
                System.out.println(Constant.DATE_OF_JOINING);
                dateOfJoining = scanner.nextLine();

                if(employeeController.isValidJoiningDate(dateOfBirth ,dateOfJoining)) {
                    localDate = LocalDate.parse(dateOfJoining); 
                    isValid = false;
                } else {
                    System.out.println(Constant.INVALID_DATA);
                }
            } catch (EMSException e) {
                logger.error(e.getErrorCode() + " " + e.getMessage());
                System.out.println(e.getErrorCode() + " " + e.getMessage());
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
            System.out.println(Constant.SALARY);
            salary = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_SALARY, salary)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return Float.parseFloat(salary);
    }

    /**
     * Get the gender of the employee from the user.
     * @return if the given gender is valid it returns the gender else ask again.
     */
    public String getGender() {
        boolean isValid = false;
        String gender = null;
        int operations = 0;
        do{
            try {
                System.out.println(Constant.EMPLOYEE_GENDER_MENU);
                operations = Integer.valueOf(scanner.nextLine());
            
                switch (operations) {
                    case 1:
                        gender = "male";
                        isValid = true;
                        break;

                     case 2:
                         gender = "female";
                         isValid = true;
                         break;

                     case 3:
                         gender = "others";
                         isValid = true;
                         break;

                     default:
                         System.out.println(Constant.OPERATION_ERROR);
                 }
             } catch (NumberFormatException numberFormatError) {
                logger.error(Constant.OPERATION_ERROR);
                System.out.println(Constant.OPERATION_ERROR);
            }
        } while (!(isValid));
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
            System.out.println(Constant.DOOR_NUMBER);
            doorNumber = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_DOOR_NUMBER, doorNumber)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.STREET_NAME);
            street = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_STREET, street)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.CITY_NAME);
            city = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_CITY, city)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.STATE);
            state = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_STATE, state)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
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
            System.out.println(Constant.PINCODE);
            pincode = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_PINCODE, pincode)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return Integer.parseInt(pincode);
    }

    /**
     * Get the address type of the employee from the user.
     * @return if the given address type is valid it returns the address type else ask again.
     */
    public String getType() {
        boolean isValid = false;
        String type = null;
        int operations = 0;

        do{
            try {
                System.out.println(Constant.EMPLOYEE_TYPE_MENU);
                operations = Integer.valueOf(scanner.nextLine());
            
                switch (operations) {
                    case 1:
                        type = "permanent";
                        isValid = true;
                        break;

                     case 2:
                         type = "temporary";
                         isValid = true;
                         break;

                     default:
                         System.out.println(Constant.OPERATION_ERROR);
                 }
             } catch (NumberFormatException numberFormatError) {
                logger.error(Constant.OPERATION_ERROR);
                System.out.println(Constant.OPERATION_ERROR);
            }
        } while (!(isValid));
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
        } while (getResponse("address"));
        return addressDtos;
    }

    public List<ProjectDTO> getProject(List<ProjectDTO> projectList) {
       ProjectDTO projectDto = null;
       boolean isResponse;
       List<ProjectDTO> projectDtos = projectList;

       try {

           do {
               projectDto = employeeController.getProject(getProjectID());

               if (null != projectDto) {
                   projectDtos.add(projectDto);
               } else {
                   System.out.println(Constant.PROJECT_ID_NOT_EXISTS);
               }
               isResponse = getResponse("project");
           }while(isResponse);
       }catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
       return projectDtos;
    }

    /**
     * Get the employee id of the employee from the user.
     * @return if the given id is valid it returns the id else ask again.
     */
    public int getProjectID() {
        boolean isValid = true;
        String projectId;
        System.out.println("enter project id");
        do{
            projectId = scanner.nextLine();

            if(employeeController.isValidData(Constant.VALID_ID, projectId)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return Integer.valueOf(projectId);
    }

     public boolean getResponse(String attribute) {
         boolean isResponse = false;
         boolean isValid;
         System.out.println("You want to add" + attribute + "press y for yes/ press n for no");
             do {
                isValid = true;
  
                switch (scanner.nextLine()) {
                    case "y":
                        isResponse = true;
                        break;

                    case "n":
                        isResponse = false;
                        break;

                    default:
                        isValid = false;
                        System.out.println(Constant.INVALID_DATA); 
                 }
             } while (!isValid);
         return isResponse;
    } 
}