package de.mannheimer.imd.avw.impl.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import de.ahaus.dennis.javautils.impl.helper.Assert;
import de.mannheimer.imd.avw.api.IdGenerator;
import de.mannheimer.imd.avw.api.persistence.CrudDao;

/**
 * @author Dennis Ahaus
 * 
 * @param <T>
 */
@Repository
public abstract class AbstractDao<T> implements CrudDao<T> {

	Logger logger = LoggerFactory.getLogger(AbstractDao.class);

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
	protected List<T> findAll(Class<? extends T> clazz) {

		Session session = getSessionfactory().getCurrentSession();
		Criteria crit = session.createCriteria(clazz);
		@SuppressWarnings("unchecked")
		List<T> result = crit.list();
		return new ArrayList<>(result);
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
		return result;
	}

	protected SessionFactory getSessionfactory() {

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

}
