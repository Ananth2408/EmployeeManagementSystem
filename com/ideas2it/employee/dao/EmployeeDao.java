package com.ideas2it.employee.dao;

import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.util.connectionutil.ConnectionUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


/**
 * Manipulate employeedetails and return boolean values.
 * Save the Employee details, read them, update them and delete them.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */ 
public class EmployeeDao {
    ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
    Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    public int  addEmployee(Employee employee) throws EMSException {
        int id;
        Session session = null;
  
        try {
            session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException 
            (Constant.INSERTION_EXCEPTION,
             Constant.ERROR_CODE101); 
        }
        finally {
            session.close();
        }
        return id;
    }
      
    /**
     * Employee details were retrived from the database.
     * @return employee list were returned.
     */
    public List<Employee> getAllEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();
        Session session = null;

        try {
            session = connectionUtil.getSession();
            Query query = session.createQuery("from Employee");
            employees = query.list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException 
            (Constant.DISPLAYING_EXCEPTION,
             Constant.ERROR_CODE102);
        }
        finally {
            session.close();
        }
        return employees;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @return boolean value if update returns true else returns false.
     */
    public Employee updateEmployee(Employee employee)
                                  throws EMSException {
        boolean isUpdated = false;
        Employee employees = null;
        Session session = null;  

        try {
            session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            employees = (Employee) session.merge(employee);
            transaction.commit();

        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (Constant.UPDATION_EXCEPTION,
             Constant.ERROR_CODE103);
        }
        finally {
            session.close();
        }
        return employees;
    }


    /**
     * Find the employee details from the database 
     * with the help of given name from the employee.
     * @param employee name from the user. 
     */
    public List<Employee> searchEmployee(String name) 
                                   throws EMSException {
        List<Employee> employees = new ArrayList();
        Session session = null;  
             
        try {
            session = connectionUtil.getSession();
            Criteria criteria = session.createCriteria(Employee.class);
            employees = (List<Employee>) criteria.add(Restrictions.like("firstName", (name + "%"))).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (Constant.SEARCHING_EXCEPTION,
             Constant.ERROR_CODE104);
        }
        finally {
            session.close();
        }
        return employees;
    }

    
    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true else false.
     */
    public void deleteEmployee(int employeeId) 
                                  throws EMSException {
        Session session = null;  

        try {
            session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            session.remove(employee);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (Constant.DELETING_EXCEPTION,
             Constant.ERROR_CODE105);
        }
        finally {
            session.close();
        }
    }          
}  
