package de.mannheimer.imd.avw.impl.persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.commons.Assert;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.model.MimeType;
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

	@Override
	@Transactional
	public List<Document> findAll() {

		return super.findAll(Document.class);
	}

	@Override
	@Transactional
	public List<Document> findBy(DocumentContainer container) {

		Assert.notNull(container);

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

		Assert.notNull(id);

		return super.findById(id, Document.class);
	}

	@Override
	public Document getNewInstance(MimeType mimetype, String containerName) {

		Assert.notNull(mimetype);

		DocumentImpl doc = new DocumentImpl(mimetype);
		doc.setId(getGenerator().createUniqueId());
		doc.setCreationDate(new Date());
		doc.setLastChangeDate(new Date());
		doc.setVersion(-1);

		DocumentContainer container = getContainerInstance(containerName);
		doc.setContainer(container);

		return doc;
	}

	@Override
	public Document getNewInstance() {

		DocumentImpl doc = new DocumentImpl();
		doc.setId(getGenerator().createUniqueId());
		doc.setCreationDate(new Date());
		doc.setLastChangeDate(new Date());
		doc.setVersion(-1);

		return doc;
	}

	/**
	 * @return
	 */
	@Override
	public DocumentContainer getContainerInstance(String name) {

		DocumentContainer container = findSingleContainerByName(name);
		if (container != null) {
			return container;
		}
		DocumentContainerImpl newContainer = new DocumentContainerImpl();
		newContainer.setId(getGenerator().createUniqueId());
		newContainer.setName(name);
		return newContainer;
	}

	protected void deletePhysical(Document doc) {

		Assert.notNull(doc);

		File target = getDocFile(doc);
		logger.debug("Deleting physical document " + target.getAbsolutePath());
		target.delete();

	}

	protected void persistPhysical(Document doc, InputStream input)
			throws IOException {

		Assert.notNull(doc);
		Assert.notNull(input);

		File target = getDocFile(doc);
		logger.info("Save document to " + target.getAbsolutePath());
		if (target.exists()) {
			deletePhysical(doc);
		}
		FileUtils.copyInputStreamToFile(input, target);

	}

	protected File getDocFile(Document doc) {

		Assert.notNull(doc);

		String ext = MimeTypes.APPLICATION_PDF.getExtension();
		File target = new File("temp/" + doc.getId() + "." + ext);
		return target;
	}

	@Override
	public InputStream findStream(Document doc) throws IOException {

		Assert.notNull(doc);

		logger.debug("Find stream for document " + doc);
		File file = getDocFile(doc);
		return FileUtils.openInputStream(file);
	}

	@Override
	@Transactional
	public void persist(Document doc, InputStream inputStream)
			throws IOException {

		Assert.notNull(doc);
		Assert.notNull(inputStream);

		logger.info("Start persisting new document " + doc);

		DocumentContainer container = doc.getContainer();
		Assert.notNull(container);
		doc.setVersion(getNextDocumentVersion(container));

		super.persist(doc);

		this.persistPhysical(doc, inputStream);

	}

	@Transactional
	public int getNextDocumentVersion(DocumentContainer container) {

		Assert.notNull(container);
		int nextVersion = -1;
		DocumentContainer found = findSingleContainerByName(container.getName());
		logger.debug("Found container for container id {}: {}",
				container.getId(), found);
		if (found == null) {
			return 1;
		}

		List<Document> documents = findBy(found);
		logger.debug("Found {} documents with container {}", documents.size(),
				found);
		nextVersion = documents.size() + 1;
		logger.debug("Next document version is {}", nextVersion);
		return nextVersion;

	}

	@Override
	public void persist(Document obj) {

		throw new RuntimeException(
				"This method should not be used directly outside this class!");
	}

	@Override
	@Transactional
	public void delete(Document doc) {

		Assert.notNull(doc);

		logger.debug("Start deleting document " + doc);
		super.delete(doc);

		this.deletePhysical(doc);
	}

	@Override
	@javax.transaction.Transactional
	public void doLazyInitialize(Document doc) {

		if (doc != null) {
			doc.getContainer().getId();
			doc.getMimeType().getExtension();
		}
	}

	@Override
	@javax.transaction.Transactional
	public List<DocumentContainer> findContainersByName(String name) {

		List<DocumentContainer> containers = super.findByProperty("name", name,
				DocumentContainer.class);
		return containers;

	}

	@Override
	@javax.transaction.Transactional
	public DocumentContainer findSingleContainerByName(String name) {

		List<DocumentContainer> containers = findContainersByName(name);
		if (!containers.isEmpty()) {
			return containers.iterator().next();
		}
		return null;
	}

}
