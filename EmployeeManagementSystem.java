import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.view.EmployeeView;
import com.ideas2it.employee.view.ProjectView;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;
/**
 * This Application used to maintain the employee details.
 * Create, read, update and delete operations were done in this application.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class EmployeeManagementSystem {
    static Logger logger = LogManager.getLogger(EmployeeManagementSystem.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operations = 0;

        do {

            try {
                System.out.println(Constant.EMPLOYEE_MENU);
                operations = Integer.valueOf(scanner.nextLine());

                switch (operations) {
                        case 1:
                            EmployeeView employeeView = new EmployeeView();
                            employeeView.employeeOperation();
                            break;

                        case 2:
                            ProjectView projectView = new ProjectView();
                            projectView.projectOperation();
                            break;

                        case 3:
                            System.out.println("Thank you");
                            break;

                        default:
                            System.out.println(Constant.OPERATION_ERROR);
                }
            } catch (NumberFormatException numberFormatError) {
                logger.error(Constant.OPERATION_ERROR);
                System.out.println(Constant.OPERATION_ERROR);
            }
        } while (3 != operations);
    }
}