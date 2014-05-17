package de.mannheimer.imd.avw.impl.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import de.mannheimer.imd.avw.api.IdGenerator;
import de.mannheimer.imd.avw.api.commons.Assert;
import de.mannheimer.imd.avw.api.persistence.CrudDao;

/**
 * @author Dennis Ahaus
 * 
 * @param <T>
 */
@Repository
public abstract class AbstractDao<T> implements CrudDao<T>,
		ApplicationContextAware {

	Logger logger = LoggerFactory.getLogger(AbstractDao.class);

	@Inject
	private ApplicationContext context;

	@Inject
	SessionFactory sessionfactory;

	@Inject
	IdGenerator generator;

	@Override
	@javax.transaction.Transactional
	public void delete(T obj) {

		Session session = getSessionfactory().getCurrentSession();
		session.delete(obj);
		session.flush();
		session.clear();
	}

	/**
	 * @param clazz
	 * @return
	 */
	@javax.transaction.Transactional
	protected <X> List<X> findAll(Class<? extends X> clazz) {

		Session session = getSessionfactory().getCurrentSession();
		Criteria crit = session.createCriteria(clazz);
		@SuppressWarnings("unchecked")
		List<X> result = crit.list();
		return new ArrayList<X>(result);
	}

	/**
	 * @param id
	 * @param clazz
	 * @return
	 */
	@javax.transaction.Transactional
	protected T findById(String id, Class<? extends T> clazz) {

		if (id == null) {
			throw new IllegalArgumentException(
					"Execption while try to find an entity: Identifier is null");
		}
		Session session = getSessionfactory().getCurrentSession();
		Criteria crit = session.createCriteria(clazz);
		@SuppressWarnings("unchecked")
		T result = (T) crit.add(Restrictions.eq("id", id)).uniqueResult();
		doLazyInitialize(result);
		return result;
	}

	@javax.transaction.Transactional
	protected <X> List<X> findByProperty(String propertyName, Object value,
			Class<? extends X> clazz) {

		Assert.notNull(propertyName);
		Assert.notNull(value);

		Session session = getSessionfactory().getCurrentSession();
		Criteria crit = session.createCriteria(clazz);
		@SuppressWarnings("unchecked")
		List<X> result = crit.add(Restrictions.eq(propertyName, value)).list();
		return new ArrayList<X>(result);
	}

	protected SessionFactory getSessionfactory() {

		if (this.sessionfactory == null) {
			this.sessionfactory = context.getBean(SessionFactory.class);
		}
		return sessionfactory;
	}

	@Override
	@javax.transaction.Transactional
	public void persist(T obj) {

		if (obj == null) {
			throw new IllegalArgumentException("Can not persist a NULL object!");
		}

		logger.debug("Persisting " + obj);

		Session session = getSessionfactory().getCurrentSession();
		session.save(obj);
		session.flush();

	}

	protected void setSessionfactory(SessionFactory sessionfactory) {

		Assert.notNull(sessionfactory);

		this.sessionfactory = sessionfactory;
	}

	@Override
	@javax.transaction.Transactional
	public void update(T obj) {

		Session session = getSessionfactory().getCurrentSession();
		session.update(obj);
		session.flush();
	}

	protected IdGenerator getGenerator() {

		return generator;
	}

	protected void setGenerator(IdGenerator generator) {

		Assert.notNull(generator);

		this.generator = generator;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {

		this.context = ctx;

	}

	@Transient
	public ApplicationContext getContext() {

		return context;
	}

	public abstract void doLazyInitialize(T object);

	public void doLazyInitialize(List<T> objects) {

		for (T obj : objects) {
			doLazyInitialize(obj);
		}
	}

}
