/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author PC-MATT
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
	static {
		try {
                    
                     sessionFactory =
        new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                
                
        		}
		catch (Throwable ex) {//NOPMD
			//LoggerManager.logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
