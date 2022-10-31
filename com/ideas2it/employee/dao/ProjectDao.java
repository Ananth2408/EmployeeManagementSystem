package com.ideas2it.employee.dao;

import com.ideas2it.employee.constant.Constant;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Project;
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
 * Manipulate Project details and return boolean values.
 * Save the Project details, read them, update them and delete them.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */ 
public class ProjectDao {
    ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
    Logger logger = LogManager.getLogger(ProjectDao.class);

    /**
     * Save the project details.
     * @param project details.
     * @return if project details added it returns true else it returns false.
     */
    public int  addProject(Project project) throws EMSException {
        int id;
        Session session = null;  

        try {
            session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(project);
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
     * Project details were retrived from the database.
     * @return project list were returned.
     */
    public List<Project> getAllProject() throws EMSException {
        List<Project> projects = new ArrayList();
        Session session = null;  

        try {
            session = connectionUtil.getSession();
            Query query = session.createQuery("from Project");
            projects = query.list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException 
            (Constant.DISPLAYING_EXCEPTION,
             Constant.ERROR_CODE102);
        }
        finally {
            session.close();
        }
        return projects;
    }

    /**
     * The project details were update by the given projectid.
     * @param project details from user
     * @return boolean value if update returns true else returns false.
     */
    public Project updateProject(Project project)
                                  throws EMSException {
        boolean isUpdated = false;
        Project projects = null;
        Session session = null;  

        try {
            session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            projects = (Project) session.merge(project);
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
        return projects;
    }

    /**
     * Find the project details from the database 
     * with the help of given name from the project.
     * @param project name from the user. 
     */
    public List<Project> searchProject(String name) 
                                   throws EMSException {
        List<Project> projects = new ArrayList();
        Session session = null;  
             
        try {
            session = connectionUtil.getSession();
            Criteria criteria = session.createCriteria(Project.class);
            projects = (List<Project>) criteria.add(Restrictions.like("projectName", (name + "%"))).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            (Constant.SEARCHING_EXCEPTION,
             Constant.ERROR_CODE104);
        }
        finally {
            session.close();
        }
        return projects;
    }

    /**
     * Used to delete the project details from the database.
     * Projectid from user used to delete the projectdetails.
     * @param projectid from the user.
     * @return boolean value if project deleted it returns true else false.
     */
    public void deleteProject(int projectId) 
                                  throws EMSException {
        Session session = null;  

        try {
            session = connectionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Project project = (Project) session.get(Project.class, projectId);
            session.remove(project);
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