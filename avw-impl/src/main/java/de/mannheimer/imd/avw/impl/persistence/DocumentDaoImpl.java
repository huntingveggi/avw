package de.mannheimer.imd.avw.impl.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import de.mannheimer.imd.avw.api.IdGenerator;
import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.impl.persistence.model.DocumentContainerImpl;
import de.mannheimer.imd.avw.impl.persistence.model.DocumentImpl;

/**
 * @author Dennis Ahaus
 * 
 */
@Repository
@Scope("prototype")
public class DocumentDaoImpl extends AbstractDao<Document> implements
		DocumentDao {

	final Logger logger = LoggerFactory.getLogger(DocumentDaoImpl.class);

	@Inject
	IdGenerator generator;

	@Override
	@Transactional
	public List<Document> findAll() {

		return super.findAll(Document.class);
	}

	@Override
	@Transactional
	public List<Document> findBy(DocumentContainer container) {

		Session session = getSessionfactory().getCurrentSession();
		Criteria crit = session.createCriteria(Document.class);
		crit.add(Restrictions.eq("container", container));
		@SuppressWarnings("unchecked")
		List<Document> list = crit.list();
		return new ArrayList<Document>(list);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.mannheimer.imd.avw.api.persistence.CrudDao#findById(java.lang.String)
	 */
	@Override
	@Transactional
	public Document findById(String id) {

		return super.findById(id, Document.class);
	}

	/**
	 * @return
	 */
	public IdGenerator getGenerator() {

		return generator;
	}

	@Override
	public Document getNewInstance(MimeTypes mimetype) throws IOException {

		if (mimetype == null) {
			throw new NullPointerException("MimeType is null");
		}

		DocumentImpl doc = new DocumentImpl(mimetype);
		DocumentContainerImpl containerImpl = new DocumentContainerImpl();
		containerImpl.setId(generator.createUniqueId());
		doc.setContainer(containerImpl);
		doc.setId(generator.createUniqueId());
		return doc;
	}

	protected void deletePhysical(Document doc) {

		File target = getDocFile(doc);
		logger.debug("Deleting physical document " + target.getAbsolutePath());
		target.delete();

	}

	protected void persistPhysical(Document doc, InputStream input)
			throws IOException {

		File target = getDocFile(doc);
		logger.debug("Save document to " + target.getAbsolutePath());
		if (target.exists()) {
			deletePhysical(doc);
		}
		FileUtils.copyInputStreamToFile(input, target);

	}

	protected File getDocFile(Document doc) {

		String ext = MimeTypes.APPLICATION_PDF.getExtension();
		File target = new File("temp/" + doc.getId() + "." + ext);
		return target;
	}

	@Override
	public InputStream findStream(Document doc) throws IOException {

		logger.debug("Find stream for document " + doc);
		File file = getDocFile(doc);
		return new FileInputStream(file);
	}

	/**
	 * @param generator
	 */
	public void setGenerator(IdGenerator generator) {

		this.generator = generator;
	}

	@Override
	@Transactional
	public void persist(Document doc, InputStream inputStream)
			throws IOException {

		logger.info("Start persisting new document " + doc);

		super.persist(doc);

		this.persistPhysical(doc, inputStream);

	}

	@Override
	public void persist(Document obj) {

		throw new RuntimeException(
				"This method should not be used directly outside this class!");
	}

	@Override
	@Transactional
	public void delete(Document doc) {

		logger.debug("Start deleting document " + doc);
		super.delete(doc);

		this.deletePhysical(doc);
	}

}
