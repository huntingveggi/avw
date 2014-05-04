package de.mannheimer.imd.avw.test.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.api.IdGenerator;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.model.MimeType;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.impl.IdGeneratorImpl;
import de.mannheimer.imd.avw.impl.persistence.MimeTypeFactory;
import de.mannheimer.imd.avw.test.mockup.DocumentDaoImplMock;

/**
 * Standard execution test for {@link DocumentDao} implementation.
 * 
 * This class tests the standard execution of available public methods in
 * {@link DocumentDao} implementation.
 * 
 * @author Dennis Ahaus
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/*-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class DocumentDaoImplExecutionTest {

	Logger logger = LoggerFactory.getLogger(DocumentDaoImplExecutionTest.class);

	@Inject
	DocumentDao documentDao = null;

	@Inject
	SessionFactory factory;

	Document currentDocument;
	static InputStream currentDocumentInputStream;
	static DocumentDao staticDocumentDao = null;
	static List<Document> createdDocuments = new LinkedList<Document>();

	@AfterClass
	public static void tearDownAfterClass() {

		Iterator<Document> it = createdDocuments.iterator();
		while (it.hasNext()) {
			Document document = (Document) it.next();
			staticDocumentDao.delete(document);
			it.remove();

		}

	}

	@Before
	public void setUp() throws Exception {

		if (staticDocumentDao == null) {
			staticDocumentDao = documentDao;
		}

		currentDocumentInputStream = new ClassPathResource("test.pdf")
				.getInputStream();

		assertNotNull(currentDocumentInputStream);

		MimeType mimeType = MimeTypeFactory.getByExtension("pdf");
		currentDocument = documentDao.getNewInstance(mimeType);

		assertNotNull(currentDocument);

		documentDao.persist(currentDocument, currentDocumentInputStream);
		createdDocuments.add(currentDocument);

	}

	@Test
	public void testFindAll() {

		List<Document> documents = documentDao.findAll();
		assertTrue(documents.size() > 0);

	}

	@Test
	public void testFindByValidDocumentContainer() {

		DocumentContainer container = currentDocument.getContainer();

		List<Document> documents = documentDao.findBy(container);
		assertTrue(documents.size() > 0);

	}

	@Test
	public void testFindByValidAndExistingIdString() {

		Document doc = documentDao.findById(currentDocument.getId());
		assertNotNull(doc);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByNullIdString() {

		documentDao.findById(null);

	}

	@Test
	public void testSetGeneratorWithValidGenerator()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		IdGenerator generator = new IdGeneratorImpl();
		Assert.assertNotNull(generator);

		DocumentDaoImplMock mockup = new DocumentDaoImplMock();
		mockup.setGenerator(generator);

		IdGenerator generator2 = mockup.getGenerator();
		Assert.assertTrue(generator == generator2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetGeneratorWithNullGenerator()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		DocumentDaoImplMock mockup = new DocumentDaoImplMock();
		mockup.setGenerator(null);

	}

	@Test
	public void testSetSessionFactoryWithValidSessionFactory()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Assert.assertNotNull(factory);

		DocumentDaoImplMock mockup = new DocumentDaoImplMock();
		mockup.setSessionfactory(factory);

		SessionFactory factory2 = mockup.getSessionfactory();
		Assert.assertTrue(factory == factory2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSessionFactoryWithNullSessionFactory()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		DocumentDaoImplMock mockup = new DocumentDaoImplMock();
		mockup.setSessionfactory(null);
	}

	@Test
	public void testGetNewValidInstance() throws IOException {

		MimeType mimeType = MimeTypeFactory.getByExtension("pdf");
		Document doc = documentDao.getNewInstance(mimeType);
		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertNotNull(doc.getContainer());

	}

	@Test
	public void testUpdateValidDocumentProperties() {

		Document persistedDoc = documentDao.findById(currentDocument.getId());

		String updatedProp = "Max Mustermann";
		persistedDoc.setLastChangeBy(updatedProp);
		documentDao.update(persistedDoc);

		Document updatedDoc = documentDao.findById(currentDocument.getId());

		assertTrue(updatedDoc.getLastChangeBy().equals(updatedProp));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNullDocument() throws Exception {

		documentDao.update(null);

	}

	@Test(expected = RuntimeException.class)
	public void testPersistMethodWithValidDocument() throws Exception {

		documentDao.persist(currentDocument);
	}

	@Test(expected = RuntimeException.class)
	public void testPersistMethodWithNullDocument() throws Exception {

		documentDao.persist(null);
	}

	@Test
	public void testFindStreamForValidDocument() throws IOException {

		InputStream stream = null;
		try {
			// currentDocument was already persisted before --> valid document
			stream = documentDao.findStream(currentDocument);
		} finally {
			if (stream != null) {
				IOUtils.closeQuietly(stream);
			}
		}
		assertNotNull(stream);
	}

}
