package com.ideas2it.employee.util.connectionutil;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.exception.EMSException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

/**
 * This makes the connection between the database and our application.
 * From this connection we can manipulate the data in database.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class ConnectionUtil {
    private static ConnectionUtil connectionUtil = null;
    private static SessionFactory sessionFactory = null;
    Logger logger = LogManager.getLogger(ConnectionUtil.class);

    private ConnectionUtil() {}

    /**
     * This used to call the connection between the database and application
     * @return connection util
     */
    public static ConnectionUtil getConnectionUtil() {

        if (connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }

    public Session getSession() throws EMSException{    

        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration()
                    .configure("configure.cfg.xml").buildSessionFactory();
            }
        } catch (HibernateException e) {
             throw new EMSException(EmployeeManagementConstant.CONNECTION_EXCEPTION,
                                   EmployeeManagementConstant.ERROR_CODE107);
        }
        return sessionFactory.getCurrentSession();
    }
}