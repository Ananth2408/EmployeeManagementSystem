package com.ideas2it.employee.util;

import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.exception.EMSException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

/**
 * Multiple times implemented values were placed here,
 * Get the values from the user and validate them.
 * @version  4.1 10-10-2022.
 * @author  Ananth K.
 */
public class ValidateUtil {
    Logger logger = LogManager.getLogger(ValidateUtil.class);

    /**
     * Used validate the given date of birth is valid or not.
     * @param birthdate from the user.
     * @return is vaid return true else false.
     */
    public boolean isValidBirthDate(String birthDate) throws EMSException {
       boolean isValid = false;

       try {
           LocalDate dateOfBirth = LocalDate.parse(birthDate);
           LocalDate currentDate = LocalDate.now();
           int age = Period.between(dateOfBirth, currentDate).getYears();

           if ((age > 18) && (age < 60)) {
               isValid = true;
           }    
        } catch (DateTimeParseException exception) {
            logger.info(exception.getMessage());
            throw new EMSException
            (Constant.DATE_EXCEPTION, 
             Constant.ERROR_CODE109);
        }
       return isValid;
    }

    /**
     * Used validate the given date of joining is valid or not.
     * @param joiningdate from the user.
     * @return is vaid return true else false.
     */            
    public boolean isValidJoiningDate(LocalDate birthDate,
                     String joiningDate) throws EMSException {
        boolean isValid = false;
    
        try {
            LocalDate dateOfJoining = LocalDate.parse(joiningDate);
            LocalDate currentDate = LocalDate.now();

            if ((currentDate.compareTo(dateOfJoining)) >= 0) {

                if (18 < Period.between(birthDate, dateOfJoining).getYears()) {
                    isValid = true;
                }
            }
        } catch (DateTimeParseException exception) {
            logger.info(exception.getMessage());
            throw new EMSException
            (Constant.DATE_EXCEPTION, 
             Constant.ERROR_CODE109);
        }
        return isValid;
    }      

    /**
     * Used validate the given date of joining is valid or not.
     * @param joiningdate from the user.
     * @return is vaid return true else false.
     */            
    public boolean isValidStartDate(String startDate)
                                        throws EMSException {
        boolean isValid = false;
    
        try {
            LocalDate startingDate = LocalDate.parse(startDate);
            LocalDate currentDate = LocalDate.now();

            if ((currentDate.compareTo(startingDate)) >= 0) {
                isValid = true;
            }
        } catch (DateTimeParseException exception) {
            logger.info(exception.getMessage());
            throw new EMSException
            (Constant.DATE_EXCEPTION, 
             Constant.ERROR_CODE109);
        }
        return isValid;
    }

    /**
     * Used validate the given date of joining is valid or not.
     * @param joiningdate from the user.
     * @return is vaid return true else false.
     */            
    public boolean isValidDate(LocalDate startDate,
                     String date) throws EMSException {
        boolean isValid = false;
    
        try {
            LocalDate localDate = LocalDate.parse(date);

            if ((startDate.compareTo(localDate)) < 0) {
                isValid = true;
            }
        } catch (DateTimeParseException exception) {
            logger.info(exception.getMessage());
            throw new EMSException
            (Constant.DATE_EXCEPTION, 
             Constant.ERROR_CODE109);
        }
        return isValid;
    }

    /**
     * Used to validate the given input is valid or not.
     * @param pattern is regex pattern.
     * @param field is values, input from the users.
     * @return if it is valid it returns true else returns false.
     */
    public boolean isValidData(String pattern, String field) {
        return Pattern.matches(pattern, field);
    }
}