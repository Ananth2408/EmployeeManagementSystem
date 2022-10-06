package com.ideas2it.employee.constant;

/**
 * Multiple time used names were declared in same constant
 * and utilised whenever we need.
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementConstant {

    public static final String EMPLOYEE_MANAGEMENT_MENU = "Enter the opertaion to done \n1. Add \n2. Display" 
                                                           + "\n3. Update \n4. Search \n5. Delete \n6. Exit";
    public static final String EMPLOYEE_OPERATION_ERROR = "Enter Valid Operation.";
    public static final String EMPLOYEE_MANAGEMENT_ERROR = "Enter The Valid Data.";
    public static final String EMPLOYEE_NOT_FOUND = "Employee Not Inserted";
    public static final String FIRST_NAME = "Enter The FirstName Without Sapce (eg.Ananthkarnan):";
    public static final String LAST_NAME = "Enter The LastName:";
    public static final String ROLE = "Enter The Role Of Emoployee";
    public static final String EMPLOYEE_ID = "Enter The EmployeeId To Change";
    public static final String EMPLOYEE_DELETE = "Enter The EmployeeId To Delete";
    public static final String EMPLOYEE_ID_NOT_EXISTS = "Employee ID Doesn't Exists";
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
    public static final String VALID_EMPLOYEE_ID = "([0-9]{1,})";
    public static final String VALID_FIRST_NAME = "(([A-Za-z]{2,})+([ ]?[A-Za-z]{2,}?[ ]?[A-Za-z]{2,}?))";
    public static final String VALID_LAST_NAME = "(([A-Za-z]{2,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_ROLE = "(([A-Za-z]{2,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_PHONE_NUMBER = "([6-9][0-9]{9})";
    public static final String VALID_EMAIL = "(([a-z0-9]([.][a-z0-9]+)*){6,}[@]{1}[a-z]{2,8}[\\.]([a-z]{3,}([a-z\\.][a-z]{2})*))";
    public static final String VALID_SALARY = "([0-9]*[\\.]?[0-9]{2})";
    public static final String VALID_GENDER = "([A-Za-z]{2,})";
    public static final String VALID_DOOR_NUMBER = "([no|No|NO]?[#]?[A-Za-z]*[-/:]?[0-9]+[-/:]?[0-9]*)";
    public static final String VALID_STREET =  "([0-9]*([A-Za-z]{4,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_CITY = "(([A-Za-z]{4,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_STATE = "(([A-Za-z]{4,})+([ ]?[A-Za-z]{2,}?))";
    public static final String VALID_PINCODE = "([0-9]{6})";
    public static final String VALID_TYPE = "([A-Za-z]{6,})";
    public static final String ERROR_CODE101 = "Error code 101 :";
    public static final String ERROR_CODE102 = "Error code 102 :";
    public static final String ERROR_CODE103 = "Error code 103 :";
    public static final String ERROR_CODE104 = "Error code 104 :";
    public static final String ERROR_CODE105 = "Error code 105 :";
    public static final String ERROR_CODE106 = "Error code 106 :";
    public static final String INSERTION_EXCEPTION = "Oops! error occured in inserting data, please try again";
    public static final String DISPLAYING_EXCEPTION = "Oops! error occured in displaying , please try again";
    public static final String UPDATION_EXCEPTION = "Oops! error occured in updating data, please try again";
    public static final String SEARCHING_EXCEPTION = "Oops! error occured in searching data, please try again";
    public static final String DELETING_EXCEPTION = "Oops! error occured in deleting data, please try again";
    public static final String IDNOTEXISTS_EXCEPTION = "Oops! error occured id doesn't exists, please try again";
}    