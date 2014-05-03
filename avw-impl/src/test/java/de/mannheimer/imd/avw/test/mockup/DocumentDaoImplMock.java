package de.mannheimer.imd.avw.test.mockup;

import org.hibernate.SessionFactory;

import de.mannheimer.imd.avw.api.IdGenerator;
import de.mannheimer.imd.avw.impl.persistence.DocumentDaoImpl;

/**
 * Mockup object for {@link DocumentDaoImpl}.
 * 
 * This class gives public delegated access to the non public methods inside
 * {@link DocumentDaoImpl}.
 * 
 * @author Dennis Ahaus
 * 
 */
public class DocumentDaoImplMock extends DocumentDaoImpl {

	@Override
	public void setGenerator(IdGenerator generator) {

		super.setGenerator(generator);
	}

	@Override
	public IdGenerator getGenerator() {

		return super.getGenerator();
	}

	@Override
	public void setSessionfactory(SessionFactory sessionfactory) {

		super.setSessionfactory(sessionfactory);
	}

	@Override
	public SessionFactory getSessionfactory() {

		return super.getSessionfactory();
	}

}
