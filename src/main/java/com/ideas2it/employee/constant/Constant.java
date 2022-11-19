package com.ideas2it.employee.constant;

/**
 * Multiple time used names were declared in same constant
 * and utilised whenever we need.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class Constant {

    public static final String EMPLOYEE_MENU = "Enter the opertaion to done \n1. EmployeeManagement \n2. ProjectManagement" 
                                                           + "\n3. Exit"; 
    public static final String EMPLOYEE_MANAGEMENT_MENU = "Enter the Employee opertaion to done \n1. Add \n2. Display" 
                                                           + "\n3. Update \n4. Search \n5. Delete \n6. Exit";
    public static final String EMPLOYEE_UPDATE_MENU = "Enter the update opertaion to done \n1. Firstname" 
                                                               + "\n2. Lastname \n3. Role \n4. Dateofbirth"
                                                               + "\n5. Dateofjoining" + "\n6. Phonenumber"
                                                               + "\n7. EmailID \n8. Gender \n9. Salary"
                                                               + "\n10. Address \n11. Add employee to project"
                                                               + "\n12. Save and Update";
    public static final String EMPLOYEE_ADDRESSUPDATE_MENU = "Enter the update opertaion to done \n1. DoorNumber" 
                                                              + "\n2. Street \n3. City \n4.State"
                                                              + "\n5. Pincode" + "\n6. Save and Update";
    public static final String EMPLOYEE_GENDER_MENU = "Enter the gender \n1. Male \n2. Female \n3. Others";
    public static final String EMPLOYEE_TYPE_MENU = "Enter the Adddress Type \n1. Permanent \n2. Temporary";
    public static final String PROJECT_MANAGEMENT_MENU = "Enter the Project opertaion to done \n1. Add \n2. Display" 
                                                           + "\n3. Update \n4. Search \n5. Delete \n6. Exit";
    public static final String PROJECT_UPDATE_MENU = "Enter the update opertaion to done \n1. ProjectName" 
                                                               + "\n2. Technology \n3. ClientName \n4. ClientMailId"
                                                               + "\n5. StartDate" + "\n6. DueDate"
                                                               + "\n7. EndDate \n8. Add employee to the project"
                                                               + "\n9. Save and Update";
    public static final String PROJECT_ID = "Enter The ProjectId To Change";
    public static final String PROJECT_NAME = "Enter The ProjectName (eg.Employee Manangement):";
    public static final String TECHNOLOGY = "Enter The Technology of the project:";
    public static final String CLIENT_NAME = "Enter The ClientName (eg.Employee Manangement):";
    public static final String START_DATE = "Enter The Start Date(yyyy-mm-dd):";
    public static final String DUE_DATE = "Enter The Due Date(yyyy-mm-dd):";
    public static final String END_DATE = "Enter The End Date(yyyy-mm-dd):";
    public static final String OPERATION_ERROR = "Enter Valid Operation.";
    public static final String INVALID_DATA = "Enter The Valid Data.";
    public static final String DUPLICATE = "Duplicate Value, Reenter The Value";
    public static final String EMPLOYEE_NOT_FOUND = "Employee Not Found :";
    public static final String FIRST_NAME = "Enter The FirstName (eg.Ananth karnan):";
    public static final String LAST_NAME = "Enter The LastName:";
    public static final String ROLE = "Enter The Role Of Emoployee";
    public static final String EMPLOYEE_ID = "Enter The EmployeeId To Change or Add";
    public static final String EMPLOYEE_DELETE = "Enter The EmployeeId To Delete";
    public static final String EMPLOYEE_ID_NOT_EXISTS = "Employee Doesn't Exists ";
    public static final String PHONENUMBER = "Enter The Employee PhoneNumber(10numbers):";
    public static final String DATE_OF_BIRTH = "Enter The Date Of Birth(yyyy-mm-dd):";
    public static final String DATE_OF_JOINING = "Enter The Date Of Joining(yyyy-mm-dd):";
    public static final String EMAIL_ID = "Enter The Email ID:";
    public static final String GENDER = "Enter The Gender:";
    public static final String DOOR_NUMBER = "Enter The Door Number:";
    public static final String STREET_NAME = "Enter The Street Name:";
    public static final String CITY_NAME = "Enter The City Name:";
    public static final String STATE = "Enter The State:";
    public static final String TYPE = "Enter The Address Type (Permanent/Temporary):";
    public static final String PINCODE = "Enter The Pincode (eg.600001):";
    public static final String SALARY = "Enter The Salary (eg.25000.00):";
    public static final String VALID_ID = "([0-9]{1,})";
    public static final String VALID_FIRST_NAME = "(([A-Za-z]{2,})+([ ]?[A-Za-z]{2,}?[ ]?[A-Za-z]{2,}?))";
    public static final String VALID_LAST_NAME = "(([A-Za-z])+([ ]?[A-Za-z]?))";
    public static final String VALID_ROLE = "(([A-Za-z]{2,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_PHONE_NUMBER = "([6-9][0-9]{9})";
    public static final String VALID_EMAIL = "(([a-z0-9]([.][a-z0-9]+)*){6,}[@]{1}[a-z]{2,8}[\\.]([a-z]{3,}([a-z\\.][a-z]{2})*))";
    public static final String VALID_SALARY = "([0-9]*[\\.]?[0-9]{2})";
    public static final String VALID_DOOR_NUMBER = "([a-zA-z0-9:-]{1,10})";
    public static final String VALID_STREET =  "([0-9]*([A-Za-z]{4,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_CITY = "(([A-Za-z]{4,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_STATE = "(([A-Za-z]{4,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_PINCODE = "([0-9]{6})";
    public static final String ERROR_CODE101 = "Error code 101 :";
    public static final String ERROR_CODE102 = "Error code 102 :";
    public static final String ERROR_CODE103 = "Error code 103 :";
    public static final String ERROR_CODE104 = "Error code 104 :";
    public static final String ERROR_CODE105 = "Error code 105 :";
    public static final String ERROR_CODE106 = "Error code 106 :";
    public static final String ERROR_CODE107 = "Error code 107 :";
    public static final String ERROR_CODE108 = "Error code 108 :";
    public static final String ERROR_CODE109 = "Error code 109 :";
    public static final String INSERTION_EXCEPTION = "Oops! error occured in inserting data, please try again";
    public static final String DISPLAYING_EXCEPTION = "Oops! error occured in displaying , please try again";
    public static final String UPDATION_EXCEPTION = "Oops! error occured in updating data, please try again";
    public static final String SEARCHING_EXCEPTION = "Oops! error occured in searching data, please try again";
    public static final String DELETING_EXCEPTION = "Oops! error occured in deleting data, please try again";
    public static final String IDNOTEXISTS_EXCEPTION = "Oops! internal error occured id not found, please try again";
    public static final String CONNECTION_EXCEPTION = "Oops! error occured connection not created, please try again";
    public static final String CONNECTION_CLOSE_EXCEPTION = "Oops! error occured connection not closed, please try again";
    public static final String DATE_EXCEPTION = "Enter Valid Date";
    public static final String PROJECT_ID_NOT_EXISTS = "Project ID Doesn't Exists ";
    public static final String PROJECT_NOT_FOUND = "Project Not Found :";
    public static final String PROJECT_DELETE = "Enter The ProjectId To Delete";
    public static final String DETALILS_NOTEXIST = "Employee or Project details are not found";
}    