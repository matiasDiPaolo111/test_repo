/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import org.hibernate.Session;

import java.util.Collection;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.exception.*;

import java.util.List;
import org.hibernate.Query;



/**
 *
 * @author PC-MATT
 */
public class PersistentObject {
    
 public static final int ERR_PK_VIOLATION = 1;
	public static final int ERR_UNQ_VIOLATION = 1;

	protected boolean ignoreUnqKeyViolation = false;

	public void handleException(final ConstraintViolationException ex, final Session session) throws Exception {
		session.getTransaction().rollback();
		if 
			(
				(!this.ignoreUnqKeyViolation) || 
				((ex.getErrorCode() != ERR_PK_VIOLATION) && (ex.getErrorCode() != ERR_UNQ_VIOLATION))
			) {
			throw new Exception("\nSQL: " + ex.getSQL() + "\n" + "instance: " + this.toString() + "\n" + ex.getSQLException().getMessage(), ex);
		}
	}

	public void handleException(final HibernateException ex, final Session session) throws Exception {
		session.getTransaction().rollback();
		// session.close();
		throw new Exception(ex);
	}

	/*
	 * JDBCConnectionException - indicates an error with the underlying JDBC
	 * communication. SQLGrammarException - indicates a grammar or syntax problem
	 * with the issued SQL. ConstraintViolationException - indicates some form of
	 * integrity constraint violation. LockAcquisitionException - indicates an
	 * error acquiring a lock level necessary to perform the requested operation.
	 * GenericJDBCException - a generic exception which did not fall into any of
	 * the other categories.
	 */

	public void save() throws Exception {
		final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(this);
			session.getTransaction().commit();
		}
		catch (ConstraintViolationException ex) {
			this.handleException(ex, session);
		}
		catch (HibernateException ex) {
			this.handleException(ex, session);
		}
	}

	public void safeUpdate() {
		try {
			update();
		}
		catch (Exception ex) {
			//LoggerManager.logger.error("", ex);
		}
	}

	public synchronized void update() throws Exception {//NOPMD
		final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(this);
			session.getTransaction().commit();
		}
		catch (ConstraintViolationException ex) {
			this.handleException(ex, session);
		}
		catch (HibernateException ex) {
			this.handleException(ex, session);
		}
		notifyAll();
	}

	public static Object load(final String className, final Long id) {
		final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//Object result = session.load(className, id);
		final Object result = session.get(className, id);
		session.getTransaction().commit();
		return result;
	}

	public static Collection loadAll(final String query, final int maxResults) {
		final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		final Query qy = session.getNamedQuery(query);
		if (maxResults > 0) {
			qy.setMaxResults(maxResults);
		}
                Collection result =  null;
                try {
	         result = qy.list();
                
                }catch (Exception ex) {
			System.out.println(ex);
		}
		
		session.getTransaction().commit();
		return result;
	}

	public static Collection loadAll(final String query) {
		return loadAll(query, 0);
	}

	public static List loadList(final String table) {
		final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		final List result = session.createQuery("from " + table).list();
		session.getTransaction().commit();
		return result;
	}

	protected static int execUpdateSQL(final String query) {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final Transaction tx = session.beginTransaction();
		// String hqlUpdate = "update Customer c set c.name = :newName where c.name
		// = :oldName";
		// or String hqlUpdate = "update Customer set name = :newName where name =
		// :oldName";
		final int updatedEntities = session.createQuery(query).executeUpdate();
		tx.commit();
		session.close();
		return updatedEntities;
	}

	protected static int execUpdateSQL(final String query, final String parameterName, final Collection parameterValues) {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final Transaction tx = session.beginTransaction();
		final int updatedEntities = session.createQuery(query).setParameterList(parameterName, parameterValues).executeUpdate();
		tx.commit();
		session.close();
		return updatedEntities;
	}
}

