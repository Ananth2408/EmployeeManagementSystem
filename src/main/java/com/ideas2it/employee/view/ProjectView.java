package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.controller.ProjectController;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.util.ValidateUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Get the details of the project from the user
 * and save the details.
 * Perform create, read, update and exit operations.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class ProjectView {
    Scanner scanner = new Scanner(System.in);
    ProjectController projectController = new ProjectController();
    ValidateUtil util = new ValidateUtil();
    Logger logger = LogManager.getLogger(EmployeeView.class);

    /**
     * Selecting operation to be done with project details.
     * This have main menu to operation to be done.
     * If we couldn't select correct operation it shows error.
     */
    /*public void projectOperation() { 
        int operations = 0;

        do {

            try {
                System.out.println(Constant.PROJECT_MANAGEMENT_MENU);
                operations = Integer.valueOf(scanner.nextLine());

                switch (operations) {
                    case 1:
                        createProject();
                        break;

                    case 2:
                        displayProject();
                        break;

                    case 3:
                        updateProject();
                        break;

                    case 4:
                        searchProject();
                        break;

                    case 5:
                        deleteProject();
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
     * create and add project details.
     * Get the project details from the user.
     */
    /*public void createProject() {
        ProjectDTO projectDto = new ProjectDTO();
        boolean isResponse; 
        List<EmployeeDTO> employeeDtos = new ArrayList();

        try {
            projectDto.setProjectName(getProjectName());
            projectDto.setTechnology(getTechnology());
            projectDto.setClientName(getClientName());
            projectDto.setClientMailId(getClientMailId());
            projectDto.setStartDate(getStartDate());
            projectDto.setDueDate(getDueDate(projectDto.getStartDate()));
            projectDto.setEndDate(getEndDate(projectDto.getStartDate()));
            isResponse = getResponse();

            if (isResponse) {
                projectDto.setEmployee(getEmployee(employeeDtos));
            }
            Project projectId = projectController.addProject(projectDto);
            logger.info("Project Details Created " + "ID= " + projectId);
            System.out.println("Project Details Added " + "ID= " + projectId);
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }*/

    /**
     * Print all the saved project details.
     */
    public void displayProject() {

        try {
            List<ProjectDTO> projectDtos = projectController.getAllProjects();

            if (!projectDtos.isEmpty()) {

                for (ProjectDTO projectDto : projectDtos) {
                    System.out.println(projectDto.toString());
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
     * Update the Project details by the id get from the user.
     * If it's not updated shows error.
     */
   /* public void updateProject() {
        System.out.println(Constant.PROJECT_ID);
        int operations = 0;
        int projectId = getProjectID();
        ProjectDTO projectDto = null;
        boolean isUpdated = false;

        try {
            projectDto = projectController.projectExists(projectId);

            if (null != projectDto ) {

                do {

                    try {
                        System.out.println(Constant.PROJECT_UPDATE_MENU);
                        operations = Integer.valueOf(scanner.nextLine());

                        switch (operations) {
                            case 1:
                                projectDto.setProjectName(getProjectName());
                                break;

                            case 2:
                                projectDto.setTechnology(getTechnology());
                                break;

                            case 3:
                                projectDto.setClientName(getClientName());
                                break;

                            case 4:
                                projectDto.setClientMailId(getClientMailId());
                                break;

                            case 5:
                                projectDto.setStartDate(getStartDate());;
                                break;

                            case 6:
                                projectDto.setDueDate(getDueDate(projectDto.getStartDate()));
                                break;

                            case 7:
                                projectDto.setEndDate(getEndDate(projectDto.getStartDate()));
                                break;

                            case 8:
                                projectDto.setEmployee(getEmployee(projectDto.getEmployee()));
                                break;

                            case 9:
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

                if (projectController.updateProject(projectDto) != null) {
                    logger.info("Employee Details Updated" + projectId);
                    System.out.println("Employee Details Updated" + projectId);
                } else {
                    System.out.println(Constant.INVALID_DATA);
                }
            } else {
                logger.info(Constant.PROJECT_ID_NOT_EXISTS + " " +projectId);
                System.out.println(Constant.PROJECT_ID_NOT_EXISTS + " " + projectId);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }  
    }

    /**
     * Search Project details by the project name given by the user,
     * If name found it prints project details
     * else it shows error.
     */
    /*public void searchProject() {

        System.out.println(Constant.PROJECT_NAME);
        String name = scanner.nextLine();

        try {
            List<ProjectDTO> projects = projectController.searchProject(name);

            if (!projects.isEmpty()) {

                for (ProjectDTO projectDto : projects) {
                    System.out.println(projectDto.toString());
                }
            } else {
                logger.info(Constant.PROJECT_NOT_FOUND + name);
                System.out.println(Constant.PROJECT_NOT_FOUND + name);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        } 
    }*/

    /**
     * Get project name from user and delete the project details by that name.
     * If name not found it shows error.
     */
    public void deleteProject() {
        System.out.println(Constant.PROJECT_DELETE);
        int projectId = getProjectID();

        try {

            if (null != projectController.projectExists(projectId)) {
                projectController.deleteProject(projectId);
                logger.info("Project" + projectId + "has been deleted");
                System.out.println("Project" + projectId + "has been deleted");
            } else {
                logger.info(Constant.PROJECT_ID_NOT_EXISTS + projectId);
                System.out.println(Constant.PROJECT_ID_NOT_EXISTS + projectId);
            }
        } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Get the Project id of the project from the user.
     * @return if the given id is valid it returns the id else ask again.
     */
    public int getProjectID() {
        boolean isValid = true;
        String projectId;

        do{
            projectId = scanner.nextLine();

            if(projectController.isValidData(Constant.VALID_ID, projectId)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return Integer.valueOf(projectId);
    }

    /**
     * Get the projectname from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getProjectName() {
        boolean isValid = true;
        String projectName;

        do{
            System.out.println(Constant.PROJECT_NAME);
            projectName = scanner.nextLine();

            if(projectController.isValidData(Constant.VALID_FIRST_NAME, projectName)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return projectName;
    }

    /**
     * Get the lastname of the project from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getTechnology() {
        System.out.println(Constant.TECHNOLOGY);
        String technology = scanner.nextLine();
        return technology;
    }

    /**
     * Get the projectname from the user.
     * @return if the given name is valid it returns the name else ask again.
     */
    public String getClientName() {
        boolean isValid = true;
        String clientName;

        do{
            System.out.println(Constant.CLIENT_NAME);
            clientName = scanner.nextLine();

            if(projectController.isValidData(Constant.VALID_FIRST_NAME, clientName)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return clientName;
    }

    /**
     * Get the email id of the project from the user.
     * @return if the given email id is valid it returns the email id else ask again.
     */
    public String getClientMailId() throws EMSException{
        boolean isValid = true;
        String clientEmail;

        do{
            System.out.println(Constant.EMAIL_ID);
            clientEmail = scanner.nextLine();

            if(projectController.isValidData(Constant.VALID_EMAIL, clientEmail)) {
                    isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return clientEmail;
    }

    /**
     * Get the start date of the project from the user.
     * @return if the given start date is valid it returns the start date else ask again.
     */
    public LocalDate getStartDate() {
        boolean isValid = true;
        String startDate;
        LocalDate localDate = null;

        do{

            try {
                System.out.println(Constant.START_DATE);
                startDate = scanner.nextLine();

                if(projectController.isValidStartDate(startDate)) {
                    localDate = LocalDate.parse(startDate);
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
     * Get the  duedate of the project from the user.
     * @param startdate project starting date.
     * @return if the given duedate is valid 
     * it returns the duedate else ask again.
     */
    public LocalDate getDueDate(LocalDate startDate) {
        boolean isValid = true;
        String dueDate;
        LocalDate localDate = null;

        do{
            try {
                System.out.println(Constant.DUE_DATE);
                dueDate = scanner.nextLine();

                if(projectController.isValidDate(startDate ,dueDate)) {
                    localDate = LocalDate.parse(dueDate); 
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
     * Get the end date of the project from the user.
     * @param startdate starting state of the project.
     * @return if the given end date is valid 
     * it returns the end date else ask again.
     */
    public LocalDate getEndDate(LocalDate startDate) {
        boolean isValid = true;
        String endDate;
        LocalDate localDate = null;

        do{
            try {
                System.out.println(Constant.END_DATE);
                endDate = scanner.nextLine();

                if(projectController.isValidDate(startDate ,endDate)) {
                    localDate = LocalDate.parse(endDate); 
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
     * Get employee details from the dtabase and return it.
     * @param employee list from the database.
     * @return employeelist.
     */
    /*public List<EmployeeDTO> getEmployee(List<EmployeeDTO> employeeList) {
       EmployeeDTO employeeDto = null;
       boolean isResponse;
       List<EmployeeDTO> employees = employeeList;
       try {

           do {
               employeeDto = projectController.getEmployee(getEmployeeID());

               if (null != employeeDto) {
               employees.add(employeeDto);
               } else {
                    System.out.println(Constant.EMPLOYEE_ID_NOT_EXISTS);
               }
           isResponse = getResponse();
           }while(isResponse);
       }catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
       return employees;
    }*/

    /**
     * Get the employee id of the employee from the user.
     * @return if the given id is valid it returns the id else ask again.
     */
    public int getEmployeeID() {
        boolean isValid = true;
        String employeeId;
        System.out.println(Constant.EMPLOYEE_ID);
        do{
            employeeId = scanner.nextLine();

            if(projectController.isValidData(Constant.VALID_ID, employeeId)) {
                isValid = false;
            } else {
                System.out.println(Constant.INVALID_DATA);   
            }
        } while (isValid);
        return Integer.valueOf(employeeId);
    }

    /**Used to get response of the user for adding employee to the project.
     * If use wants to add it returnd true or else false.
     * @return boolean value true or false from the user choosed option.
     */ 
    public boolean getResponse() {
         boolean isResponse = false;
         boolean isValid;
         System.out.println("You want to add employee press y for yes/ press n for no");
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