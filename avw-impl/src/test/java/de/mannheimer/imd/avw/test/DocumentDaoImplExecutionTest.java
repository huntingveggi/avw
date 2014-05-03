package de.mannheimer.imd.avw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
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

import de.ahaus.dennis.javautils.impl.junit.annotations.ClassUnderTest;
import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.impl.persistence.DocumentDaoImpl;

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
@ClassUnderTest(value = DocumentDaoImpl.class)
public class DocumentDaoImplExecutionTest {

	Logger logger = LoggerFactory.getLogger(DocumentDaoImplExecutionTest.class);

	@Inject
	DocumentDao documentDao = null;
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

		currentDocument = documentDao.getNewInstance(MimeTypes.APPLICATION_PDF);

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

		Document doc = documentDao.findById(null);
		Assert.assertNull(doc);

	}

	public void testGetNewValidInstance() throws IOException {

		Document doc = documentDao.getNewInstance(MimeTypes.APPLICATION_PDF);
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
	public void testUpdateNullDocument() {

		documentDao.update(null);

	}

	@Test(expected = RuntimeException.class)
	public void testPersistMethodWithValidDocument() {

		documentDao.persist(currentDocument);
	}

	@Test(expected = RuntimeException.class)
	public void testPersistMethodWithNullDocument() {

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
