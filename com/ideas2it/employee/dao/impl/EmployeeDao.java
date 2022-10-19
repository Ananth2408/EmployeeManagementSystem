package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.util.connectionutil.ConnectionUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class EmployeeDao implements Dao {
    ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
    Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true else it returns false.
     */
    @Override
    public int  addEmployee(Employee employee) throws EMSException {
        int id;

        try {
            Session session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException 
            (EmployeeManagementConstant.INSERTION_EXCEPTION,
             EmployeeManagementConstant.ERROR_CODE101); 
        }
        return id;
    }
      
    /**
     * Employee details were retrived from the database.
     * @return employee list were returned.
     */
    @Override 
    public List<Employee> displayEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();

        try {
            Session session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Employee");
            employees = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException 
            (EmployeeManagementConstant.DISPLAYING_EXCEPTION,
             EmployeeManagementConstant.ERROR_CODE102);
        }
        return employees;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @return boolean value if update returns true else returns false.
     */
    @Override
    public Employee updateEmployee(Employee employee)
                                  throws EMSException {
        boolean isUpdated = false;
        Employee employees = null;

        try {
            Session session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            employees = (Employee) session.merge(employee);
            transaction.commit();

        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (EmployeeManagementConstant.UPDATION_EXCEPTION,
             EmployeeManagementConstant.ERROR_CODE103);
        } 
        return employees;
    }


    /**
     * Find the employee details from the database 
     * with the help of given name from the employee.
     * @param employee name from the user. 
     */
    @Override 
    public List<Employee> searchEmployee(String name) 
                                   throws EMSException {
        List<Employee> employees = new ArrayList();
             
        try {
            Session session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            employees = (List<Employee>) criteria.add(Restrictions.like("firstName", (name + "%"))).list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (EmployeeManagementConstant.SEARCHING_EXCEPTION,
             EmployeeManagementConstant.ERROR_CODE104);
        }
        return employees;
    }

    
    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true else false.
     */
    @Override
    public void deleteEmployee(int employeeId) 
                                  throws EMSException {

        try {
            Session session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            session.remove(employee);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (EmployeeManagementConstant.DELETING_EXCEPTION,
             EmployeeManagementConstant.ERROR_CODE105);
        }
    }                
}  
